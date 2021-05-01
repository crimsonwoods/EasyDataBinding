package net.crimsonwoods.easydatabinding.binding

import android.view.View
import android.widget.ViewAnimator
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
import net.crimsonwoods.easydatabinding.models.Animation
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewAnimatorBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setInAnimation() {
        scenario.onFragment { fragment ->
            fragment.requireView().requireViewById<ViewAnimator>(R.id.flipper)
                .setInAnimation(Animation.of(R.anim.test_animation_translate))
        }
        onView(withId(R.id.flipper))
            .check(matches(hasInAnimation()))
    }

    @Test
    fun testBinding_setOutAnimation() {
        scenario.onFragment { fragment ->
            fragment.requireView().requireViewById<ViewAnimator>(R.id.flipper)
                .setOutAnimation(Animation.of(R.anim.test_animation_translate))
        }
        onView(withId(R.id.flipper))
            .check(matches(hasOutAnimation()))
    }

    private fun hasInAnimation(): Matcher<View> {
        return object : BoundedMatcher<View, ViewAnimator>(ViewAnimator::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has in-animation")
            }

            override fun matchesSafely(item: ViewAnimator): Boolean {
                return item.inAnimation != null
            }
        }
    }

    private fun hasOutAnimation(): Matcher<View> {
        return object : BoundedMatcher<View, ViewAnimator>(ViewAnimator::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has out-animation")
            }

            override fun matchesSafely(item: ViewAnimator): Boolean {
                return item.outAnimation != null
            }
        }
    }
}
