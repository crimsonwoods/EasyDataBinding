package net.crimsonwoods.easydatabinding.binding.appcompat

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.Px
import androidx.appcompat.widget.SwitchCompat
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
import net.crimsonwoods.easydatabinding.fragment.AppCompatTestFragment
import net.crimsonwoods.easydatabinding.matcher.ViewMatchers.withDrawableTypeOf
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextAppearance
import net.crimsonwoods.easydatabinding.models.Tint
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SwitchCompatBindingTest {
    private lateinit var scenario: FragmentScenario<AppCompatTestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<AppCompatTestFragment>(
            themeResId = R.style.FragmentScenarioEmptyFragmentAppCompatActivityTheme
        ).moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setShowText() {
        scenario.onFragment { fragment ->
            // "showText" is set to true by default.
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setShowText(Bool.FALSE)
        }
        onView(withId(R.id.selected))
            .check(matches(not(isTextShown())))
    }

    @Test
    fun testBinding_setSplitTrack() {
        scenario.onFragment { fragment ->
            // "splitTrack" is set to false by default.
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setSplitTrack(Bool.TRUE)
        }
        onView(withId(R.id.selected))
            .check(matches(isTrackSplit()))
    }

    @Test
    fun testBinding_setSwitchMinWidth() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setSwitchMinWidth(Dimension.px(123f))
        }
        onView(withId(R.id.selected))
            .check(matches(withSwitchMinWidth(123)))
    }

    @Test
    fun testBinding_setSwitchPadding() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setSwitchPadding(Dimension.px(123f))
        }
        onView(withId(R.id.selected))
            .check(matches(withSwitchPadding(123)))
    }

    @Test
    fun testBinding_setSwitchTextAppearance() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setSwitchTextAppearance(TextAppearance.of(R.style.TextAppearance_Testing_Test))
        }
        // Unfortunately, we have no way to verify modifying "switchTextAppearance" attribute value.
    }

    @Test
    fun testBinding_setTextOff() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setTextOff(Text.of(R.string.test_text))
        }
        onView(withId(R.id.selected))
            .check(matches(withTextOff("Testing")))
    }

    @Test
    fun testBinding_setTextOn() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setTextOn(Text.of(R.string.test_text))
        }
        onView(withId(R.id.selected))
            .check(matches(withTextOn("Testing")))
    }

    @Test
    fun testBinding_setThumbDrawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setThumbDrawable(Drawable.of(R.drawable.test_drawable_oval))
        }
        onView(withId(R.id.selected))
            .check(matches(withThumbDrawableTypeOf<GradientDrawable>()))
    }

    @Test
    fun testBinding_setThumbTextPadding() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setThumbTextPadding(Dimension.px(123f))
        }
        onView(withId(R.id.selected))
            .check(matches(withThumbTextPadding(123)))
    }

    @Test
    fun testBinding_setThumbTintList_M() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setThumbTintList(Tint.of(R.color.test_tint))
        }
        val expected = ContextCompat.getColorStateList(context, R.color.test_tint)
        onView(withId(R.id.selected))
            .check(matches(withThumbTintList(expected)))
    }

    @Test
    fun testBinding_setTrackDrawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setTrackDrawable(Drawable.of(R.drawable.test_drawable_oval))
        }
        onView(withId(R.id.selected))
            .check(matches(withTrackDrawableTypeOf<GradientDrawable>()))
    }

    @Test
    fun testBinding_setTrackTintList_M() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SwitchCompat>(R.id.selected)
                .setTrackTintList(Tint.of(R.color.test_tint))
        }
        val expected = ContextCompat.getColorStateList(context, R.color.test_tint)
        onView(withId(R.id.selected))
            .check(matches(withTrackTintList(expected)))
    }

    private fun isTextShown(): Matcher<View> {
        return object : SwitchCompatMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("is show-text")
            }

            override fun matchesSafely(item: SwitchCompat): Boolean {
                return item.showText
            }
        }
    }

    private fun isTrackSplit(): Matcher<View> {
        return object : SwitchCompatMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("is track-split")
            }

            override fun matchesSafely(item: SwitchCompat): Boolean {
                return item.splitTrack
            }
        }
    }

    private fun withSwitchMinWidth(@Px value: Int): Matcher<View> {
        return object : SwitchCompatMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with switch-min-width $value")
            }

            override fun matchesSafely(item: SwitchCompat): Boolean {
                return item.switchMinWidth == value
            }
        }
    }

    private fun withSwitchPadding(@Px value: Int): Matcher<View> {
        return object : SwitchCompatMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with switch-padding $value")
            }

            override fun matchesSafely(item: SwitchCompat): Boolean {
                return item.switchPadding == value
            }
        }
    }

    private fun withTextOff(value: CharSequence?): Matcher<View> {
        return object : SwitchCompatMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with text-off $value")
            }

            override fun matchesSafely(item: SwitchCompat): Boolean {
                return item.textOff == value
            }
        }
    }

    private fun withTextOn(value: CharSequence?): Matcher<View> {
        return object : SwitchCompatMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with text-on $value")
            }

            override fun matchesSafely(item: SwitchCompat): Boolean {
                return item.textOn == value
            }
        }
    }

    private inline fun <reified T : android.graphics.drawable.Drawable> withThumbDrawableTypeOf(): Matcher<View> {
        return withDrawableTypeOf<SwitchCompat, T>(SwitchCompat::getThumbDrawable)
    }

    private fun withThumbTextPadding(value: Int): Matcher<View> {
        return object : SwitchCompatMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with thumb-text-padding $value")
            }

            override fun matchesSafely(item: SwitchCompat): Boolean {
                return item.thumbTextPadding == value
            }
        }
    }

    private fun withThumbTintList(value: ColorStateList?): Matcher<View> {
        return object : SwitchCompatMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with thumb-tint-list $value")
            }

            override fun matchesSafely(item: SwitchCompat): Boolean {
                return item.thumbTintList?.toString() == value?.toString()
            }
        }
    }

    private inline fun <reified T : android.graphics.drawable.Drawable> withTrackDrawableTypeOf(): Matcher<View> {
        return withDrawableTypeOf<SwitchCompat, T>(SwitchCompat::getTrackDrawable)
    }

    private fun withTrackTintList(value: ColorStateList?): Matcher<View> {
        return object : SwitchCompatMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with track-tint-list $value")
            }

            override fun matchesSafely(item: SwitchCompat): Boolean {
                return item.trackTintList?.toString() == value?.toString()
            }
        }
    }

    private abstract class SwitchCompatMatcher :
        BoundedMatcher<View, SwitchCompat>(SwitchCompat::class.java) {
        abstract override fun describeTo(description: Description)
        abstract override fun matchesSafely(item: SwitchCompat): Boolean
    }
}
