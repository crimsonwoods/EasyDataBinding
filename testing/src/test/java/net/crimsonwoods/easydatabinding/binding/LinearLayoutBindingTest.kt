package net.crimsonwoods.easydatabinding.binding

import android.graphics.drawable.NinePatchDrawable
import android.view.View
import android.widget.LinearLayout
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
import kotlin.test.Test
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.matcher.DrawableTypeMatcher
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Integer
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LinearLayoutBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setBaselineAligned() {
        scenario.onFragment { fragment ->
            (fragment.requireView() as LinearLayout)
                .setBaselineAligned(Bool.FALSE)
        }
        onView(withId(R.id.root))
            .check(matches(not(isBaselineAligned())))
    }

    @Test
    fun testBinding_setBaselineAlignedChildIndex() {
        scenario.onFragment { fragment ->
            (fragment.requireView() as LinearLayout)
                .setBaselineAlignedChildIndex(Integer.wrap(1))
        }
        onView(withId(R.id.root))
            .check(matches(withBaselineAlignedChildIndex(1)))
    }

    @Test
    fun testBinding_setDivider() {
        scenario.onFragment { fragment ->
            (fragment.requireView() as LinearLayout)
                .setDivider(Drawable.of(R.drawable.test_drawable_9patch))
        }
        onView(withId(R.id.root))
            .check(matches(withDividerDrawableTypeOf<NinePatchDrawable>()))
    }

    @Test
    fun testBinding_setMeasureWithLargestChild() {
        scenario.onFragment { fragment ->
            (fragment.requireView() as LinearLayout)
                .setMeasureWithLargestChild(Bool.TRUE)
        }
        onView(withId(R.id.root))
            .check(matches(isMeasureWithLargestChild()))
    }

    @Test
    fun testBinding_setDividerPadding() {
        scenario.onFragment { fragment ->
            (fragment.requireView() as LinearLayout)
                .setDividerPadding(Dimension.px(10.0f))
        }
        onView(withId(R.id.root))
            .check(matches(withDividerPadding(10)))
    }

    private fun isBaselineAligned(): Matcher<View> {
        return object : BoundedMatcher<View, LinearLayout>(LinearLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("baseline-aligned is enabled")
            }

            override fun matchesSafely(item: LinearLayout): Boolean {
                return item.isBaselineAligned
            }
        }
    }

    private fun withBaselineAlignedChildIndex(value: Int): Matcher<View> {
        return object : BoundedMatcher<View, LinearLayout>(LinearLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with baseline-aligned-child-index $value")
            }

            override fun matchesSafely(item: LinearLayout): Boolean {
                return item.baselineAlignedChildIndex == value
            }
        }
    }

    private inline fun <reified T : android.graphics.drawable.Drawable> withDividerDrawableTypeOf(): Matcher<View> {
        return DrawableTypeMatcher(LinearLayout::class, T::class) {
            dividerDrawable
        }
    }

    private fun isMeasureWithLargestChild(): Matcher<View> {
        return object : BoundedMatcher<View, LinearLayout>(LinearLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("measure-with-largest-child is enabled")
            }

            override fun matchesSafely(item: LinearLayout): Boolean {
                return item.isMeasureWithLargestChildEnabled
            }
        }
    }

    private fun withDividerPadding(@Px value: Int): Matcher<View> {
        return object : BoundedMatcher<View, LinearLayout>(LinearLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with divider-padding $value")
            }

            override fun matchesSafely(item: LinearLayout): Boolean {
                return item.dividerPadding == value
            }
        }
    }
}
