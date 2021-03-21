package net.crimsonwoods.easydatabinding.binding

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import kotlin.test.Test
import net.crimsonwoods.easydatabinding.binding.ViewGroupBindingTest.ViewGroupMatchers.isClipChildren
import net.crimsonwoods.easydatabinding.binding.ViewGroupBindingTest.ViewGroupMatchers.isClipToPadding
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.models.Bool
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
class ViewGroupBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    @Config(qualifiers = "land")
    fun testBinding_setClipChildren_Res_True() {
        scenario.onFragment { fragment ->
            // abc_action_bar_embed_tabs is true when screen rotation is landscape, else false.
            (fragment.requireView() as ViewGroup)
                .setClipChildren(Bool.of(androidx.appcompat.R.bool.abc_action_bar_embed_tabs))
        }
        onView(
            allOf(
                isAssignableFrom(LinearLayout::class.java),
                withChild(withId(android.R.id.text1))
            )
        ).check(
            matches(
                isClipChildren()
            )
        )
    }

    @Test
    @Config(qualifiers = "port")
    fun testBinding_setClipChildren_Res_False() {
        scenario.onFragment { fragment ->
            // abc_action_bar_embed_tabs is true when screen rotation is landscape, else false.
            (fragment.requireView() as ViewGroup)
                .setClipChildren(Bool.of(androidx.appcompat.R.bool.abc_action_bar_embed_tabs))
        }
        onView(
            allOf(
                isAssignableFrom(LinearLayout::class.java),
                withChild(withId(android.R.id.text1))
            )
        ).check(
            matches(
                IsNot(isClipChildren())
            )
        )
    }

    @Test
    fun testBinding_setClipChildren_Value() {
        scenario.onFragment { fragment ->
            (fragment.requireView() as ViewGroup)
                .setClipChildren(Bool.FALSE)
        }
        onView(
            allOf(
                isAssignableFrom(LinearLayout::class.java),
                withChild(withId(android.R.id.text1))
            )
        ).check(
            matches(
                IsNot(isClipChildren())
            )
        )
    }

    @Test
    @Config(qualifiers = "land")
    fun testBinding_setClipToPadding_Res_True() {
        scenario.onFragment { fragment ->
            // abc_action_bar_embed_tabs is true when screen rotation is landscape, else false.
            (fragment.requireView() as ViewGroup)
                .setClipToPadding(Bool.of(androidx.appcompat.R.bool.abc_action_bar_embed_tabs))
        }
        onView(
            allOf(
                isAssignableFrom(LinearLayout::class.java),
                withChild(withId(android.R.id.text1))
            )
        ).check(
            matches(
                isClipToPadding()
            )
        )
    }

    @Test
    @Config(qualifiers = "port")
    fun testBinding_setClipToPadding_Res_False() {
        scenario.onFragment { fragment ->
            // abc_action_bar_embed_tabs is true when screen rotation is landscape, else false.
            (fragment.requireView() as ViewGroup)
                .setClipToPadding(Bool.of(androidx.appcompat.R.bool.abc_action_bar_embed_tabs))
        }
        onView(
            allOf(
                isAssignableFrom(LinearLayout::class.java),
                withChild(withId(android.R.id.text1))
            )
        ).check(
            matches(
                IsNot(isClipToPadding())
            )
        )
    }

    @Test
    fun testBinding_setClipToPadding_Value() {
        scenario.onFragment { fragment ->
            (fragment.requireView() as ViewGroup)
                .setClipToPadding(Bool.FALSE)
        }
        onView(
            allOf(
                isAssignableFrom(LinearLayout::class.java),
                withChild(withId(android.R.id.text1))
            )
        ).check(
            matches(
                IsNot(isClipToPadding())
            )
        )
    }

    private object ViewGroupMatchers {
        fun isClipChildren(): Matcher<in View> {
            return IsClipChildrenMatcher()
        }

        fun isClipToPadding(): Matcher<in View> {
            return IsClipToPaddingMatcher()
        }

        private class IsClipChildrenMatcher :
            BoundedMatcher<View, ViewGroup>(ViewGroup::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("is clipping children")
            }

            override fun matchesSafely(item: ViewGroup): Boolean {
                return item.clipChildren
            }
        }

        private class IsClipToPaddingMatcher :
            BoundedMatcher<View, ViewGroup>(ViewGroup::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("is clipping to padding")
            }

            override fun matchesSafely(item: ViewGroup): Boolean {
                return item.clipToPadding
            }
        }
    }
}
