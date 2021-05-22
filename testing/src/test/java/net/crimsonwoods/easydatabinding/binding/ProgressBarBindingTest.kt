package net.crimsonwoods.easydatabinding.binding

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.NinePatchDrawable
import android.os.Build
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ProgressBar
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import kotlin.test.assertFailsWith
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.matcher.ViewMatchers
import net.crimsonwoods.easydatabinding.matcher.ViewMatchers.withDrawableTypeOf
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Integer
import net.crimsonwoods.easydatabinding.models.Interpolator
import net.crimsonwoods.easydatabinding.models.Tint
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
class ProgressBarBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setIndeterminate() {
        scenario.onFragment { fragment ->
            // "indeterminate" is set to false by default when "indeterminateOnly" is set to false.
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setIndeterminate(Bool.TRUE)
        }
        onView(withId(R.id.progress))
            .check(matches(isIndeterminate()))
    }

    @Test
    fun testBinding_setIndeterminateDrawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setIndeterminateDrawable(Drawable.of(R.drawable.test_drawable_9patch))
        }
        onView(withId(R.id.progress))
            .check(matches(withIndeterminateDrawableTypeOf<NinePatchDrawable>()))
    }

    @Test
    fun testBinding_setIndeterminateTintList() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setIndeterminateTintList(Tint.of(R.color.test_tint))
        }
        val expected = ContextCompat.getColorStateList(context, R.color.test_tint)
        onView(withId(R.id.progress))
            .check(matches(withIndeterminateTintList(expected)))
    }

    @Test
    fun testBinding_setInterpolator() {
        // Robolectric's "ShadowAnimationUtils.loadInterpolator" always returns "LinearInterpolator"
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setInterpolator(Interpolator.of(AccelerateInterpolator(2.0f)))
        }
        onView(withId(R.id.progress))
            .check(matches(withInterpolatorTypeOf<AccelerateInterpolator>()))
    }

    @Test
    fun testBinding_setMax() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setMax(Integer.wrap(123))
        }
        onView(withId(R.id.progress))
            .check(matches(withMax(123)))
    }

    // TODO Enables this test when Java9 or later can be used to run tests.
    /*
    @Config(sdk = [Build.VERSION_CODES.Q])
    @Test
    fun testBinding_setMaxHeight_Q() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setMaxHeight(Dimension.px(200f))
        }
        onView(withId(R.id.progress))
            .check(matches(withMaxHeight(200)))
    }
     */

    @Config(sdk = [Build.VERSION_CODES.P])
    @Test
    fun testBinding_setMaxHeight_P() {
        assertFailsWith<UnsupportedOperationException> {
            scenario.onFragment { fragment ->
                fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                    .setMaxHeight(Dimension.px(200f))
            }
        }
    }

    // TODO Enables this test when Java9 or later can be used to run tests.
    /*
    @Config(sdk = [Build.VERSION_CODES.Q])
    @Test
    fun testBinding_setMaxWidth_Q() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setMaxWidth(Dimension.px(200f))
        }
        onView(withId(R.id.progress))
            .check(matches(withMaxWidth(200)))
    }
     */

    @Config(sdk = [Build.VERSION_CODES.P])
    @Test
    fun testBinding_setMaxWidth_P() {
        assertFailsWith<UnsupportedOperationException> {
            scenario.onFragment { fragment ->
                fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                    .setMaxWidth(Dimension.px(200f))
            }
        }
    }

    @Config(sdk = [Build.VERSION_CODES.O])
    @Test
    fun testBinding_setMin_O() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setMin(Integer.wrap(Int.MIN_VALUE))
        }
        onView(withId(R.id.progress))
            .check(matches(withMin(Int.MIN_VALUE)))
    }

    @Config(sdk = [Build.VERSION_CODES.N])
    @Test
    fun testBinding_setMin_N() {
        assertFailsWith<UnsupportedOperationException> {
            scenario.onFragment { fragment ->
                fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                    .setMin(Integer.wrap(Int.MIN_VALUE))
            }
        }
    }

    // TODO Enables this test when Java9 or later can be used to run tests.
    /*
    @Config(sdk = [Build.VERSION_CODES.Q])
    @Test
    fun testBinding_setMinHeight_Q() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setMinHeight(Dimension.px(10f))
        }
        onView(withId(R.id.progress))
            .check(matches(withMinHeight(10)))
    }
     */

    @Config(sdk = [Build.VERSION_CODES.P])
    @Test
    fun testBinding_setMinHeight_P() {
        assertFailsWith<UnsupportedOperationException> {
            scenario.onFragment { fragment ->
                fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                    .setMinHeight(Dimension.px(10f))
            }
        }
    }

    // TODO Enables this test when Java9 or later can be used to run tests.
    /*
    @Config(sdk = [Build.VERSION_CODES.Q])
    @Test
    fun testBinding_setMinHeight_Q() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setMinWidth(Dimension.px(10f))
        }
        onView(withId(R.id.progress))
            .check(matches(withMinWidth(10)))
    }
     */

    @Config(sdk = [Build.VERSION_CODES.P])
    @Test
    fun testBinding_setMinWidth_P() {
        assertFailsWith<UnsupportedOperationException> {
            scenario.onFragment { fragment ->
                fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                    .setMinWidth(Dimension.px(10f))
            }
        }
    }

    @Test
    fun testBinding_setProgress() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setProgress(Integer.wrap(12))
        }
        onView(withId(R.id.progress))
            .check(matches(withProgress(12)))
    }

    @Test
    fun testBinding_setProgressBackgroundTintList() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setProgressBackgroundTintList(Tint.of(R.color.test_tint))
        }
        val expected = ContextCompat.getColorStateList(context, R.color.test_tint)
        onView(withId(R.id.progress))
            .check(matches(withProgressBackgroundTintList(expected)))
    }

    @Test
    fun testBinding_setProgressDrawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setProgressDrawable(Drawable.of(R.drawable.test_drawable_9patch))
        }
        onView(withId(R.id.progress))
            .check(matches(withProgressDrawableTypeOf<NinePatchDrawable>()))
    }

    @Test
    fun testBinding_setProgressTintList() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setProgressTintList(Tint.of(R.color.test_tint))
        }
        val expected = ContextCompat.getColorStateList(context, R.color.test_tint)
        onView(withId(R.id.progress))
            .check(matches(withProgressTintList(expected)))
    }

    @Test
    fun testBinding_setSecondaryProgress() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setSecondaryProgress(Integer.wrap(12))
        }
        onView(withId(R.id.progress))
            .check(matches(withSecondaryProgress(12)))
    }

    @Test
    fun testBinding_setSecondaryProgressTint() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ProgressBar>(R.id.progress)
                .setSecondaryProgressTint(Tint.of(R.color.test_tint))
        }
        val expected = ContextCompat.getColorStateList(context, R.color.test_tint)
        onView(withId(R.id.progress))
            .check(matches(withSecondaryProgressTintList(expected)))
    }

    private fun isIndeterminate(): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("is indeterminate")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.isIndeterminate
            }
        }
    }

    private inline fun <reified T : android.graphics.drawable.Drawable> withIndeterminateDrawableTypeOf(): Matcher<View> {
        return withDrawableTypeOf<ProgressBar, T>(ProgressBar::getIndeterminateDrawable)
    }

    private fun withIndeterminateTintList(value: ColorStateList?): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with indeterminate-tint-list $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.indeterminateTintList?.toString() == value?.toString()
            }
        }
    }

    private inline fun <reified T : android.view.animation.Interpolator> withInterpolatorTypeOf(): Matcher<View> {
        return ViewMatchers.withInterpolatorTypeOf<ProgressBar, T>(ProgressBar::getInterpolator)
    }

    private fun withMax(value: Int): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with max $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.max == value
            }
        }
    }

    private fun withMaxHeight(@Px value: Int): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with max height $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.maxHeight == value
            }
        }
    }

    private fun withMaxWidth(@Px value: Int): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with max width $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.maxWidth == value
            }
        }
    }

    private fun withMin(value: Int): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with min $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.min == value
            }
        }
    }

    private fun withMinHeight(@Px value: Int): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with min height $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.minHeight == value
            }
        }
    }

    private fun withMinWidth(@Px value: Int): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with min width $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.minWidth == value
            }
        }
    }

    private fun withProgress(value: Int): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with progress $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.progress == value
            }
        }
    }

    private fun withProgressBackgroundTintList(value: ColorStateList?): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with progress-background-tint $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.progressBackgroundTintList?.toString() == value?.toString()
            }
        }
    }

    private inline fun <reified T : android.graphics.drawable.Drawable> withProgressDrawableTypeOf(): Matcher<View> {
        return withDrawableTypeOf<ProgressBar, T>(ProgressBar::getProgressDrawable)
    }

    private fun withProgressTintList(value: ColorStateList?): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with progress-tint $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.progressTintList?.toString() == value?.toString()
            }
        }
    }

    private fun withSecondaryProgress(value: Int): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with secondary-progress $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.secondaryProgress == value
            }
        }
    }

    private fun withSecondaryProgressTintList(value: ColorStateList?): Matcher<View> {
        return object : ProgressViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with secondary-progress-tint $value")
            }

            override fun matchesSafely(item: ProgressBar): Boolean {
                return item.secondaryProgressTintList?.toString() == value?.toString()
            }
        }
    }

    private abstract class ProgressViewMatcher :
        BoundedMatcher<View, ProgressBar>(ProgressBar::class.java) {
        abstract override fun describeTo(description: Description)
        abstract override fun matchesSafely(item: ProgressBar): Boolean
    }
}
