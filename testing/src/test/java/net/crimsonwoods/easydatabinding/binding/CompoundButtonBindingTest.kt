package net.crimsonwoods.easydatabinding.binding

import android.content.res.ColorStateList
import android.graphics.drawable.NinePatchDrawable
import android.view.View
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.core.widget.CompoundButtonCompat
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.matcher.ViewMatchers.withDrawableTypeOf
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Tint
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CompoundButtonBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setChecked() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<CompoundButton>(R.id.toggle)
                .setChecked(Bool.TRUE)
        }
        onView(withId(R.id.toggle))
            .check(matches(isChecked()))
    }

    @Test
    fun testBinding_setButtonDrawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<CompoundButton>(R.id.toggle)
                .setButtonDrawable(Drawable.of(R.drawable.test_drawable_9patch))
        }
        onView(withId(R.id.toggle))
            .check(matches(withButtonDrawableTypeOf<NinePatchDrawable>()))
    }

    @Test
    fun testBinding_setButtonTint() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<CompoundButton>(R.id.toggle)
                .setButtonTint(Tint.of(R.color.test_tint))
        }
        val expected = ContextCompat.getColorStateList(
            ApplicationProvider.getApplicationContext(),
            R.color.test_tint
        )
        onView(withId(R.id.toggle))
            .check(matches(withTint(expected)))
    }

    private fun isChecked(): Matcher<View> {
        return object : BoundedMatcher<View, CompoundButton>(CompoundButton::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("is checked")
            }

            override fun matchesSafely(item: CompoundButton): Boolean {
                return item.isChecked
            }
        }
    }

    private inline fun <reified T : android.graphics.drawable.Drawable> withButtonDrawableTypeOf(): Matcher<View> {
        return withDrawableTypeOf<CompoundButton, T>(CompoundButtonCompat::getButtonDrawable)
    }

    private fun withTint(value: ColorStateList?): Matcher<View> {
        return object : BoundedMatcher<View, CompoundButton>(CompoundButton::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with Tint $value")
            }

            override fun matchesSafely(item: CompoundButton): Boolean {
                return CompoundButtonCompat.getButtonTintList(item)?.toString() == value?.toString()
            }
        }
    }
}
