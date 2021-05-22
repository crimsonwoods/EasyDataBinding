package net.crimsonwoods.easydatabinding.binding

import android.view.View
import android.widget.FrameLayout
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
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FrameLayoutBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setMeasureAllChildren() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<FrameLayout>(R.id.contents)
                .setMeasureAllChildren(Bool.TRUE)
        }
        onView(withId(R.id.contents))
            .check(matches(measureAllChildren()))
    }

    private fun measureAllChildren(): Matcher<View> {
        return object : BoundedMatcher<View, FrameLayout>(FrameLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("measure-all-children is enabled")
            }

            override fun matchesSafely(item: FrameLayout): Boolean {
                return item.measureAllChildren
            }
        }
    }
}
