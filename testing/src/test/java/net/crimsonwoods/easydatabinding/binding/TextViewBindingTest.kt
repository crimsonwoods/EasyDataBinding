package net.crimsonwoods.easydatabinding.binding

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import java.util.Locale
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Color
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Integer
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextAppearance
import net.crimsonwoods.easydatabinding.models.Tint
import net.crimsonwoods.easydatabinding.shadows.ShadowTextView
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
class TextViewBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setCursorVisible() {
        onView(withId(android.R.id.text1)).check(matches(isCursorVisible()))
        scenario.onFragment { fragment ->
            fragment.requireView().requireViewById<TextView>(android.R.id.text1)
                .setCursorVisible(Bool.FALSE)
        }
        onView(withId(android.R.id.text1)).check(matches(not(isCursorVisible())))
    }

    @Test
    fun testBinding_setDrawablePadding() {
        onView(withId(android.R.id.text1)).check(matches(withDrawablePadding(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().requireViewById<TextView>(android.R.id.text1)
                .setDrawablePadding(Dimension.px(123f))
        }
        onView(withId(android.R.id.text1)).check(matches(withDrawablePadding(123)))
    }

    @Test
    fun testBinding_setDrawableTintList() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        onView(withId(android.R.id.text1)).check(matches(withDrawableTintList(null)))
        scenario.onFragment { fragment ->
            fragment.requireView().requireViewById<TextView>(android.R.id.text1)
                .setDrawableTintList(Tint.of(R.color.test_tint))
        }
        val expected = ContextCompat.getColorStateList(context, R.color.test_tint)
        onView(withId(android.R.id.text1)).check(matches(withDrawableTintList(expected)))
    }

    @Config(shadows = [ShadowTextView::class])
    @Test
    fun testBinding_setElegantTextHeight() {
        onView(withId(android.R.id.text1)).check(matches(not(isElegantTextHeight())))
        scenario.onFragment { fragment ->
            fragment.requireView().requireViewById<TextView>(android.R.id.text1)
                .setElegantTextHeight(Bool.TRUE)
        }
        onView(withId(android.R.id.text1)).check(matches(isElegantTextHeight()))
    }

    @Test
    fun testBinding_setEms() {
        onView(withId(android.R.id.text1)).check(matches(allOf(withMaxEms(-1), withMinEms(-1))))
        scenario.onFragment { fragment ->
            fragment.requireView().requireViewById<TextView>(android.R.id.text1)
                .setEms(Integer.wrap(12))
        }
        onView(withId(android.R.id.text1)).check(matches(allOf(withMaxEms(12), withMinEms(12))))
    }

    @Config(sdk = [Build.VERSION_CODES.P])
    @Test
    fun testBinding_setFallbackLineSpacing_P() {
        onView(withId(android.R.id.text1)).check(matches(isFallbackLineSpacing()))
        scenario.onFragment { fragment ->
            fragment.requireView().requireViewById<TextView>(android.R.id.text1)
                .setFallbackLineSpacing(Bool.FALSE)
        }
        onView(withId(android.R.id.text1)).check(matches(not(isFallbackLineSpacing())))
    }

    @Config(sdk = [Build.VERSION_CODES.O])
    @Test
    fun testBinding_setFallbackLineSpacing_O() {
        assertFailsWith<UnsupportedOperationException> {
            scenario.onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1)
                    .setFallbackLineSpacing(Bool.FALSE)
            }
        }
    }

    // TODO Add more tests...

    @Test
    fun testBinding_setText_for_Res() {
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(R.string.test_text))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("Testing")))
    }

    @Test
    fun testBinding_setText_for_ResWithArgs() {
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(R.string.test_text_with_args, 1, 2))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("1/2")))
    }

    @Test
    fun testBinding_setText_for_CharSequence() {
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of("test"))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("test")))
    }

    @Test
    @Config(qualifiers = "ja-rJP")
    fun testBinding_setText_for_Multilingual_jaJP() {
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(Locale.JAPAN to "テスト", Locale.US to "Test"))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("テスト")))
    }

    @Test
    @Config(qualifiers = "en-rUS")
    fun testBinding_setText_for_Multilingual_jaJP_Fallback() {
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(mapOf(Locale.JAPAN to "テスト"), "test"))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("test")))
    }

    @Test
    @Config(qualifiers = "en-rUS")
    fun testBinding_setText_for_Multilingual_jaJP_FallbackRes() {
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(mapOf(Locale.JAPAN to "テスト"), android.R.string.ok))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("OK")))
    }

    @Test
    @Config(qualifiers = "en-rUS")
    fun testBinding_setText_for_Multilingual_enUS() {
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(Locale.JAPAN to "テスト", Locale.US to "Test"))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("Test")))
    }

    @Test
    fun testBinding_setTextAllCaps() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextAllCaps(Bool.TRUE)
        }
        onView(withId(android.R.id.text1)).check(matches(isAllCaps()))
    }

    @Test
    fun testBinding_setTextAppearance() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextAppearance(TextAppearance(R.style.TextAppearance_Testing_Test))
        }
        val textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            32f,
            context.resources.displayMetrics
        )
        val textColor = ContextCompat.getColor(context, R.color.test_color)
        onView(withId(android.R.id.text1))
            .check(matches(withTextSize(textSize)))
            .check(matches(hasTextColor(textColor)))
    }

    @Test
    fun testBinding_setTextColor_for_Int() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextColor(Color.Int(0xff))
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0xff)))
    }

    @Test
    fun testBinding_setTextColor_for_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextColor(Color.Res(android.R.color.white))
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(android.graphics.Color.WHITE)))
    }

    @Test
    fun testBinding_setTextColor_for_String() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextColor(Color.String("red"))
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(android.graphics.Color.RED)))
    }

    @Test
    fun testBinding_setTextColor_for_Drawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextColor(Color.Drawable(ColorDrawable(0x0000ff)))
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0x0000ff)))
    }

    @Test
    fun testBinding_setTextColor_for_StateList() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        val stateList = ColorStateList(
            arrayOf(intArrayOf(), intArrayOf(android.R.attr.state_selected)),
            intArrayOf(0x0000ff, 0x00ffff)
        )
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextColor(Color.StateList(stateList))
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(stateList)))
    }

    @Test
    fun testBinding_setHint() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHint(Text.of(R.string.test_text))
        }
        onView(withId(android.R.id.text1)).check(matches(withHint("Testing")))
    }

    @Test
    fun testBinding_setHintTextColor_for_Int() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHintTextColor(Color.Int(0xff))
        }
        onView(withId(android.R.id.text1)).check(matches(withHintColor(0xff)))
    }

    @Test
    fun testBinding_setHintTextColor_for_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHintTextColor(Color.Res(android.R.color.white))
        }
        onView(withId(android.R.id.text1)).check(matches(withHintColor(android.graphics.Color.WHITE)))
    }

    @Test
    fun testBinding_setHintTextColor_for_String() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHintTextColor(Color.String("red"))
        }
        onView(withId(android.R.id.text1)).check(matches(withHintColor(android.graphics.Color.RED)))
    }

    @Test
    fun testBinding_setHintTextColor_for_Drawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHintTextColor(Color.Drawable(ColorDrawable(0x0000ff)))
        }
        onView(withId(android.R.id.text1)).check(matches(withHintColor(0x0000ff)))
    }

    @Test
    fun testBinding_setHintTextColor_for_StateList() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        val stateList = ColorStateList(
            arrayOf(intArrayOf(), intArrayOf(android.R.attr.state_selected)),
            intArrayOf(0x0000ff, 0x00ffff)
        )
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHintTextColor(Color.StateList(stateList))
        }
        onView(withId(android.R.id.text1)).check(matches(withHintTint(stateList)))
    }

    private fun isCursorVisible(): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("is cursor visible")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.isCursorVisible
            }
        }
    }

    private fun withDrawablePadding(@Px value: Int): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with drawable padding $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.compoundDrawablePadding == value
            }
        }
    }

    private fun withDrawableTintList(value: ColorStateList?): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with drawable tint list $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return TextViewCompat.getCompoundDrawableTintList(item)
                    ?.toString() == value?.toString()
            }
        }
    }

    private fun isElegantTextHeight(): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("is elegant text height")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.isElegantTextHeight
            }
        }
    }

    private fun withMaxEms(value: Int): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with max ems $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.maxEms == value
            }
        }
    }

    private fun withMinEms(value: Int): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with min ems $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.minEms == value
            }
        }
    }

    private fun isFallbackLineSpacing(): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("is fallback line spacing")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.isFallbackLineSpacing
            }
        }
    }

    private fun withFirstBaselineToTopHeight(@Px value: Int): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with first baseline to top height $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                val h = TextViewCompat.getFirstBaselineToTopHeight(item)
                return h == value
            }
        }
    }

    private fun hasTextColor(@ColorInt color: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has color with integer $color")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.currentTextColor == color
            }
        }
    }

    private fun hasTextColor(stateList: ColorStateList): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has color with state-list $stateList")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.textColors.toString() == stateList.toString()
            }
        }
    }

    private fun withHint(value: CharSequence?): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with hint $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.hint == value
            }
        }
    }

    private fun withHintTint(value: ColorStateList?): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with hint text color $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.hintTextColors?.toString() == value?.toString()
            }
        }
    }

    private fun withHintColor(@ColorInt value: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with hint text color ${android.graphics.Color.valueOf(value)}")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.currentHintTextColor == value
            }
        }
    }

    private fun isAllCaps(): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("is all caps")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.isAllCaps
            }
        }
    }

    private fun withTextSize(@Px value: Float): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with text size $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.textSize == value
            }
        }
    }

    private abstract class TextViewMatcher : BoundedMatcher<View, TextView>(TextView::class.java) {
        abstract override fun describeTo(description: Description)
        abstract override fun matchesSafely(item: TextView): Boolean
    }
}
