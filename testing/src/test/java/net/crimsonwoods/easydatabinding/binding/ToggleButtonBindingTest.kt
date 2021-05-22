package net.crimsonwoods.easydatabinding.binding

import android.view.View
import android.widget.ToggleButton
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
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToggleButtonBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setTextOff() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ToggleButton>(R.id.toggle)
                .setTextOff(Text.of(R.string.test_text))
        }
        onView(withId(R.id.toggle))
            .check(matches(withTextOff("Testing")))
    }

    @Test
    fun testBinding_setTextOn() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ToggleButton>(R.id.toggle)
                .setTextOn(Text.of(R.string.test_text))
        }
        onView(withId(R.id.toggle))
            .check(matches(withTextOn("Testing")))
    }

    private fun withTextOff(value: CharSequence?): Matcher<View> {
        return object : BoundedMatcher<View, ToggleButton>(ToggleButton::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with text-off $value")
            }

            override fun matchesSafely(item: ToggleButton): Boolean {
                return item.textOff == value
            }
        }
    }

    private fun withTextOn(value: CharSequence?): Matcher<View> {
        return object : BoundedMatcher<View, ToggleButton>(ToggleButton::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with text-on $value")
            }

            override fun matchesSafely(item: ToggleButton): Boolean {
                return item.textOn == value
            }
        }
    }
}
