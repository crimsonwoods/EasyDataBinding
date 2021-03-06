package net.crimsonwoods.easydatabinding.binding

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ListView
import androidx.annotation.Px
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.matcher.ViewMatchers.withDrawableTypeOf
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListViewBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<ListView>(R.id.list)
            }
    }

    @Test
    fun testBinding_setDivider() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ListView>(R.id.list)
                .setDivider(Drawable.ofColor(Color.RED))
        }
        onView(withId(R.id.list))
            .check(matches(withDivider<ColorDrawable>()))
    }

    @Test
    fun testBinding_setDividerHeight() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ListView>(R.id.list)
                .setDividerHeight(Dimension.px(10.0f))
        }
        onView(withId(R.id.list))
            .check(matches(withDividerHeight(10)))
    }

    @Test
    fun testBinding_setFooterDividersEnabled() {
        scenario.onFragment { fragment ->
            // footer dividers are enabled by default.
            fragment.requireView().findViewById<ListView>(R.id.list)
                .setFooterDividersEnabled(Bool.FALSE)
        }
        onView(withId(R.id.list))
            .check(matches(not(areFooterDividersEnabled())))
    }

    @Test
    fun testBinding_setHeaderDividersEnabled() {
        scenario.onFragment { fragment ->
            // header dividers are enabled by default.
            fragment.requireView().findViewById<ListView>(R.id.list)
                .setHeaderDividersEnabled(Bool.FALSE)
        }
        onView(withId(R.id.list))
            .check(matches(not(areHeaderDividersEnabled())))
    }

    private inline fun <reified T : android.graphics.drawable.Drawable> withDivider(): Matcher<View> {
        return withDrawableTypeOf<ListView, T>(ListView::getDivider)
    }

    private fun withDividerHeight(@Px value: Int): Matcher<View> {
        return object : BoundedMatcher<View, ListView>(ListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with divider-height $value")
            }

            override fun matchesSafely(item: ListView): Boolean {
                return item.dividerHeight == value
            }
        }
    }

    private fun areFooterDividersEnabled(): Matcher<View> {
        return object : BoundedMatcher<View, ListView>(ListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("footer-divider is enabled")
            }

            override fun matchesSafely(item: ListView): Boolean {
                return item.areFooterDividersEnabled()
            }
        }
    }

    private fun areHeaderDividersEnabled(): Matcher<View> {
        return object : BoundedMatcher<View, ListView>(ListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("header-divider is enabled")
            }

            override fun matchesSafely(item: ListView): Boolean {
                return item.areHeaderDividersEnabled()
            }
        }
    }
}
