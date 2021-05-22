package net.crimsonwoods.easydatabinding.binding

import android.view.View
import android.widget.ScrollView
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
class ScrollViewBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setFillViewport() {
        scenario.onFragment { fragment ->
            // fillViewport is disabled by default.
            fragment.requireView().findViewById<ScrollView>(R.id.scroll)
                .setFillViewport(Bool.TRUE)
        }
        onView(withId(R.id.scroll))
            .check(matches(isFillViewport()))
    }

    private fun isFillViewport(): Matcher<View> {
        return object : BoundedMatcher<View, ScrollView>(ScrollView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("stretch content to fill the viewport")
            }

            override fun matchesSafely(item: ScrollView): Boolean {
                return item.isFillViewport
            }
        }
    }
}
