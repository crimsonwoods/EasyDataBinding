package net.crimsonwoods.easydatabinding.binding

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import kotlin.test.Test
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.models.Background
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setBackground_Color_Int() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Color.Int(0xff))
        }
        onView(withId(android.R.id.text1)).check(matches(hasBackgroundColor(0xff)))
    }

    @Test
    fun testBinding_setBackground_Color_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Color.Res(android.R.color.white))
        }
        onView(withId(android.R.id.text1)).check(matches(hasBackgroundColor(Color.WHITE)))
    }

    @Test
    fun testBinding_setBackground_Color_String() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Color.String("blue"))
        }
        onView(withId(android.R.id.text1)).check(matches(hasBackgroundColor(Color.BLUE)))
    }

    @Test
    fun testBinding_setBackground_Color_Drawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Color.Drawable(ColorDrawable(Color.YELLOW)))
        }
        onView(withId(android.R.id.text1)).check(matches(hasBackgroundColor(Color.YELLOW)))
    }

    @Test
    fun testBinding_setBackground_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Res(android.R.drawable.screen_background_dark))
        }
        onView(withId(android.R.id.text1)).check(matches(hasBackgroundColor(Color.parseColor("#ff000000"))))
    }

    @Test
    fun testBinding_setBackground_Drawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Drawable(ShapeDrawable(RectShape())))
        }
        onView(withId(android.R.id.text1)).check(matches(isDrawableClassOf<ShapeDrawable>()))
    }

    @Test
    fun testBinding_setBackground_None() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .apply {
                    setBackgroundColor(Color.RED)
                    setBackground(Background.None)
                }
        }
        onView(withId(android.R.id.text1)).check(matches(noBackground()))
    }

    private fun hasBackgroundColor(@ColorInt color: Int): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has background color with ${color.toString(16)}")
            }

            override fun matchesSafely(item: View): Boolean {
                return when (val background = item.background) {
                    is ColorDrawable -> background.color == color
                    else -> false
                }
            }
        }
    }

    private inline fun <reified T : Drawable> isDrawableClassOf(): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has background drawable type of ${T::class.java.simpleName}")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.background?.javaClass == T::class.java
            }
        }
    }

    private fun noBackground(): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has background")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.background == null
            }
        }
    }
}
