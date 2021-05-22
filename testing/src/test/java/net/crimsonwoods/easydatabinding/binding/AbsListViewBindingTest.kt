package net.crimsonwoods.easydatabinding.binding

import android.view.View
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
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
import org.hamcrest.core.IsNot
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AbsListViewBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<ListView>(R.id.list).apply {
                    // To enable fast-scroll, ListView must contain many items (over 4 pages).
                    adapter = object : ArrayAdapter<String>(
                        fragment.requireContext(),
                        R.layout.test_list_item,
                        R.id.mainText,
                        (0..1000).map { "test$it" }
                    ) {}
                }
            }
    }

    @Test
    fun testBinding_setFastScrollEnabled() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ListView>(R.id.list)
                .setFastScrollEnabled(Bool.TRUE)
        }
        onView(withId(R.id.list))
            .check(matches(isFastScrollEnabled()))
    }

    @Test
    fun testBinding_setSmoothScrollbar() {
        scenario.onFragment { fragment ->
            // SmoothScrollbar is enabled by default.
            fragment.requireView().findViewById<ListView>(R.id.list)
                .setSmoothScrollbarEnabled(Bool.FALSE)
        }
        onView(withId(R.id.list))
            .check(matches(IsNot(isSmoothScrollbarEnabled())))
    }

    @Test
    fun testBinding_setStackFromBottom() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ListView>(R.id.list)
                .setStackFromBottom(Bool.TRUE)
        }
        onView(withId(R.id.list))
            .check(matches(isStackFromBottom()))
    }

    @Test
    fun testBinding_setTextFilterEnabled() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ListView>(R.id.list)
                .setTextFilterEnabled(Bool.TRUE)
        }
        onView(withId(R.id.list))
            .check(matches(isTextFilterEnabled()))
    }

    private fun isFastScrollEnabled(): Matcher<View> {
        return object : BoundedMatcher<View, AbsListView>(AbsListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("fast-scroll is enabled")
            }

            override fun matchesSafely(item: AbsListView): Boolean {
                return item.isFastScrollEnabled
            }
        }
    }

    private fun isSmoothScrollbarEnabled(): Matcher<View> {
        return object : BoundedMatcher<View, AbsListView>(AbsListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("smooth-scrollbar is enabled")
            }

            override fun matchesSafely(item: AbsListView): Boolean {
                return item.isSmoothScrollbarEnabled
            }
        }
    }

    private fun isStackFromBottom(): Matcher<View> {
        return object : BoundedMatcher<View, AbsListView>(AbsListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("stack-from-bottom is enabled")
            }

            override fun matchesSafely(item: AbsListView): Boolean {
                return item.isStackFromBottom
            }
        }
    }

    private fun isTextFilterEnabled(): Matcher<View> {
        return object : BoundedMatcher<View, AbsListView>(AbsListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("text-filter is enabled")
            }

            override fun matchesSafely(item: AbsListView): Boolean {
                return item.isTextFilterEnabled
            }
        }
    }
}
