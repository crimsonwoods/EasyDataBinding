package net.crimsonwoods.easydatabinding.binding

import android.os.Build
import android.view.View
import android.widget.ViewFlipper
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
class ViewFlipperBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    // TODO Enables this test when Java9 or later can be used to run tests.
    /*
    @Test
    fun testBinding_setFlipInterval() {
        scenario.onFragment { fragment ->
            // Default interval is 3000ms.
            fragment.requireView().requireViewById<ViewFlipper>(R.id.flipper)
                .setFlipInterval(Integer.wrap(1234))
        }
        onView(withId(R.id.flipper))
            .check(matches(withFlipInterval(1234)))
    }
     */

    @Test
    fun testBinding_setAutoStart() {
        scenario.onFragment { fragment ->
            // "autoStart" is set to false by default.
            fragment.requireView().requireViewById<ViewFlipper>(R.id.flipper)
                .setAutoStart(Bool.TRUE)
        }
        onView(withId(R.id.flipper))
            .check(matches(isAutoStart()))
    }

    private fun withFlipInterval(value: Int): Matcher<View> {
        return object : ViewFlipperMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with flip-interval $value")
            }

            override fun matchesSafely(item: ViewFlipper): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    return item.flipInterval == value
                } else {
                    throw UnsupportedOperationException(
                        "This matcher can be worked for Android Q or later."
                    )
                }
            }
        }
    }

    private fun isAutoStart(): Matcher<View> {
        return object : ViewFlipperMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("is auto-start")
            }

            override fun matchesSafely(item: ViewFlipper): Boolean {
                return item.isAutoStart
            }
        }
    }

    private abstract class ViewFlipperMatcher :
        BoundedMatcher<View, ViewFlipper>(ViewFlipper::class.java) {
        abstract override fun describeTo(description: Description)
        abstract override fun matchesSafely(item: ViewFlipper): Boolean
    }
}
