package net.crimsonwoods.easydatabinding.binding

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.text.TextPaint
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.annotation.RequiresApi
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
import kotlin.test.assertTrue
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Color
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Float
import net.crimsonwoods.easydatabinding.models.Integer
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextAppearance
import net.crimsonwoods.easydatabinding.models.Tint
import net.crimsonwoods.easydatabinding.shadows.ShadowTypeface
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
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setCursorVisible(Bool.FALSE)
        }
        onView(withId(android.R.id.text1)).check(matches(not(isCursorVisible())))
    }

    @Test
    fun testBinding_setDrawablePadding() {
        onView(withId(android.R.id.text1)).check(matches(withDrawablePadding(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setDrawablePadding(Dimension.px(123f))
        }
        onView(withId(android.R.id.text1)).check(matches(withDrawablePadding(123)))
    }

    @Config(sdk = [Build.VERSION_CODES.N])
    @Test
    fun testBinding_setDrawableTintList_N() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        onView(withId(android.R.id.text1)).check(matches(withDrawableTintList(null)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setDrawableTintList(Tint.of(R.color.test_tint))
        }
        val expected = ContextCompat.getColorStateList(context, R.color.test_tint)
        onView(withId(android.R.id.text1)).check(matches(withDrawableTintList(expected)))
    }

    @Test
    fun testBinding_setElegantTextHeight() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setElegantTextHeight(Bool.TRUE)
        }
        // Currently, ElegantTextHeight is not supported on Robolectric.
    }

    @Test
    fun testBinding_setEms() {
        onView(withId(android.R.id.text1)).check(matches(allOf(withMaxEms(-1), withMinEms(-1))))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setEms(Integer.wrap(12))
        }
        onView(withId(android.R.id.text1)).check(matches(allOf(withMaxEms(12), withMinEms(12))))
    }

    @Config(sdk = [Build.VERSION_CODES.P])
    @Test
    fun testBinding_setFallbackLineSpacing_P() {
        onView(withId(android.R.id.text1)).check(matches(isFallbackLineSpacing()))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
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

    @Test
    fun testBinding_setFirstBaselineToTopHeight() {
        val resources = ApplicationProvider.getApplicationContext<Context>().resources
        val paint = TextPaint(TextPaint.ANTI_ALIAS_FLAG).apply {
            density = resources.displayMetrics.density
            textSize = 15f
        }
        onView(withId(android.R.id.text1)).check(matches(withFirstBaselineToTopHeight(-paint.fontMetricsInt.top)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setFirstBaselineToTopHeight(Dimension.px(123f))
        }
        onView(withId(android.R.id.text1)).check(matches(withFirstBaselineToTopHeight(123)))
    }

    @Test
    fun testBinding_setFontFeatureSettings() {
        onView(withId(android.R.id.text1)).check(matches(withFontFeatureSettings(null)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setFontFeatureSettings(Text.of("\"smcp\" on"))
        }
        onView(withId(android.R.id.text1)).check(matches(withFontFeatureSettings("\"smcp\" on")))
    }

    @Config(shadows = [ShadowTypeface::class], sdk = [Build.VERSION_CODES.O])
    @Test
    fun testBinding_setFontVariationSettings_O() {
        onView(withId(android.R.id.text1)).check(matches(withFontVariationSettings(null)))
        scenario.onFragment { fragment ->
            assertTrue {
                fragment.requireView().findViewById<TextView>(android.R.id.text1)
                    .setFontVariationSettings(Text.of("\"wdth\" 123"))
            }
        }
        onView(withId(android.R.id.text1)).check(matches(withFontVariationSettings("\"wdth\" 123")))
    }

    @Test
    fun testBinding_setFreezesText() {
        onView(withId(android.R.id.text1)).check(matches(not(isTextFrozen())))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setFreezesText(Bool.TRUE)
        }
        onView(withId(android.R.id.text1)).check(matches(isTextFrozen()))
    }

    @Test
    fun testBinding_setHeight() {
        onView(withId(android.R.id.text1)).check(matches(withMaxHeight(-1)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHeight(Dimension.px(123f))
        }
        onView(withId(android.R.id.text1)).check(matches(withMaxHeight(123)))
    }

    @Test
    fun testBinding_setWidth() {
        onView(withId(android.R.id.text1)).check(matches(withMaxWidth(Int.MAX_VALUE)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setWidth(Dimension.px(123f))
        }
        onView(withId(android.R.id.text1)).check(matches(withMaxWidth(123)))
    }

    @Test
    fun testBinding_setIncludeFontPadding() {
        onView(withId(android.R.id.text1)).check(matches(isFontPaddingIncluded()))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setIncludeFontPadding(Bool.FALSE)
        }
        onView(withId(android.R.id.text1)).check(matches(not(isFontPaddingIncluded())))
    }

    @Test
    fun testBinding_setLineHeight() {
        onView(withId(android.R.id.text1)).check(matches(withLineHeight(35)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setLineHeight(Dimension.px(48f))
        }
        onView(withId(android.R.id.text1)).check(matches(withLineHeight(48)))
    }

    @Test
    fun testBinding_setLineSpacingExtra() {
        onView(withId(android.R.id.text1)).check(matches(withLineSpacingExtra(0f)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setLineSpacingExtra(Dimension.px(12f))
        }
        onView(withId(android.R.id.text1)).check(matches(withLineSpacingExtra(12f)))
    }

    @Test
    fun testBinding_setLineSpacingMultiplier() {
        onView(withId(android.R.id.text1)).check(matches(withLineSpacingMultiplier(1.0f)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setLineSpacingMultiplier(Float.of(1.2f))
        }
        onView(withId(android.R.id.text1)).check(matches(withLineSpacingMultiplier(1.2f)))
    }

    @Test
    fun testBinding_setMinLines() {
        onView(withId(android.R.id.text1)).check(matches(withMinLines(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setMinLines(Integer.wrap(1))
        }
        onView(withId(android.R.id.text1)).check(matches(withMinLines(1)))
    }

    @Test
    fun testBinding_setMinWidth() {
        onView(withId(android.R.id.text1)).check(matches(withMinWidth(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setMinWidth(Dimension.px(123f))
        }
        onView(withId(android.R.id.text1)).check(matches(withMinWidth(123)))
    }

    @Config(sdk = [Build.VERSION_CODES.Q])
    @Test
    fun testBinding_setScrollHorizontally_Q() {
        onView(withId(android.R.id.text1)).check(matches(not(isHorizontallyScrollable())))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setScrollHorizontally(Bool.TRUE)
        }
        onView(withId(android.R.id.text1)).check(matches(isHorizontallyScrollable()))
    }

    @Test
    fun testBinding_setSelectAllOnFocus() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setSelectAllOnFocus(Bool.TRUE)
        }
        // Behavior of "setSelectAllOnFocus" cannot be verified on Robolectric.
    }

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
    fun testBinding_setText_for_null() {
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of("test"))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("test")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(null as Text?)
        }
        onView(withId(android.R.id.text1)).check(matches(withText("")))
    }

    @Config(sdk = [Build.VERSION_CODES.P])
    @Test
    fun testBinding_setTextAllCaps_P() {
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
    fun testBinding_setHint_for_null() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHint(Text.of("test"))
        }
        onView(withId(android.R.id.text1)).check(matches(withHint("test")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHint(null as Text?)
        }
        onView(withId(android.R.id.text1)).check(matches(withHint(null)))
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

    @Test
    fun testBinding_setHintTextColor_for_null() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHintTextColor(null as Color?)
        }
        onView(withId(android.R.id.text1)).check(matches(noHintColors()))
    }

    @Test
    fun testBinding_setTextScaleX() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextScaleX(Float.of(R.dimen.half))
        }
        // Behavior of "setTextScaleX" cannot be verified on Robolectric.
    }

    @Test
    fun testBinding_setTextSize() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val defaultTextSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            14f,
            context.resources.displayMetrics
        )
        onView(withId(android.R.id.text1)).check(matches(withTextSize(defaultTextSize)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextSize(Dimension.sp(18f))
        }
        val expected = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            18f,
            context.resources.displayMetrics
        )
        onView(withId(android.R.id.text1)).check(matches(withTextSize(expected)))
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

    private fun withLineHeight(@Px value: Int): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with line height $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.lineHeight == value
            }
        }
    }

    private fun withLineSpacingExtra(@Px value: kotlin.Float): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with line spacing extra $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.lineSpacingExtra == value
            }
        }
    }

    private fun withLineSpacingMultiplier(value: kotlin.Float): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with line spacing multiplier $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.lineSpacingMultiplier == value
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
                return TextViewCompat.getFirstBaselineToTopHeight(item) == value
            }
        }
    }

    private fun withFontFeatureSettings(value: String?): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with font feature settings $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.fontFeatureSettings == value
            }
        }
    }

    @RequiresApi(value = Build.VERSION_CODES.O)
    private fun withFontVariationSettings(value: String?): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with font variation settings $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.fontVariationSettings == value
            }
        }
    }

    private fun isTextFrozen(): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("is text frozen")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.freezesText
            }
        }
    }

    private fun withMaxHeight(value: Int): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with max height")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.maxHeight == value
            }
        }
    }

    private fun withMaxWidth(value: Int): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with max width")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.maxWidth == value
            }
        }
    }

    private fun isFontPaddingIncluded(): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("is font paddinbg included")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.includeFontPadding
            }
        }
    }

    private fun withMinLines(value: Int): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with min lines $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.minLines == value
            }
        }
    }

    private fun withMinWidth(value: Int): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with min width $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.minWidth == value
            }
        }
    }

    private fun isHorizontallyScrollable(): Matcher<View> {
        return object : TextViewMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("is horizontally scrollable")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.isHorizontallyScrollable
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
                description.appendText("with hint text color $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.currentHintTextColor == value
            }
        }
    }

    private fun noHintColors(): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has no color with state-list")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.hintTextColors == null
            }
        }
    }

    @RequiresApi(value = Build.VERSION_CODES.P)
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

    private fun withTextSize(@Px value: kotlin.Float): Matcher<View> {
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
