package net.crimsonwoods.easydatabinding.binding

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isFocusable
import androidx.test.espresso.matcher.ViewMatchers.withAlpha
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import kotlin.test.Test
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.models.Background
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Fraction
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.Tint
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsNot
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setAlpha_Res_Base() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setAlpha(Fraction.of(R.fraction.test_fraction, 1, 2))
        }
        onView(withId(android.R.id.text1)).check(matches(withAlpha(0.5f)))
    }

    @Test
    fun testBinding_setAlpha_Res_ParentBase() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setAlpha(Fraction.of(R.fraction.test_fraction_p, 2, 1))
        }
        onView(withId(android.R.id.text1)).check(matches(withAlpha(0.5f)))
    }

    @Test
    fun testBinding_setAlpha_Float() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setAlpha(Fraction.of(0.1f))
        }
        onView(withId(android.R.id.text1)).check(matches(withAlpha(0.1f)))
    }

    @Test
    fun testBinding_setBackground_Color_Int() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Color.Int(0xff))
        }
        onView(withId(android.R.id.text1)).check(matches(hasBackgroundColor(0xff)))
    }

    @Test
    fun testBinding_setBackground_Color_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Color.Res(android.R.color.white))
        }
        onView(withId(android.R.id.text1)).check(matches(hasBackgroundColor(Color.WHITE)))
    }

    @Test
    fun testBinding_setBackground_Color_String() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Color.String("blue"))
        }
        onView(withId(android.R.id.text1)).check(matches(hasBackgroundColor(Color.BLUE)))
    }

    @Test
    fun testBinding_setBackground_Color_Drawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Color.Drawable(ColorDrawable(Color.YELLOW)))
        }
        onView(withId(android.R.id.text1)).check(matches(hasBackgroundColor(Color.YELLOW)))
    }

    @Test
    fun testBinding_setBackground_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Res(android.R.drawable.screen_background_dark))
        }
        onView(withId(android.R.id.text1)).check(matches(hasBackgroundColor(Color.parseColor("#ff000000"))))
    }

    @Test
    fun testBinding_setBackground_Drawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackground(Background.Drawable(ShapeDrawable(RectShape())))
        }
        onView(withId(android.R.id.text1)).check(matches(isDrawableClassOf<ShapeDrawable>()))
    }

    @Test
    fun testBinding_setBackground_None() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .apply {
                    setBackgroundColor(Color.RED)
                    setBackground(Background.None)
                }
        }
        onView(withId(android.R.id.text1)).check(matches(noBackground()))
    }

    @Test
    fun testBinding_setBackgroundTint_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackgroundTint(Tint.of(android.R.color.tab_indicator_text))
        }
        onView(withId(android.R.id.text1)).check(matches(withBackgroundTint { tint ->
            tint.defaultColor == Color.parseColor("#808080")
        }))
    }

    @Test
    fun testBinding_setBackgroundTint_ColorStateList() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setBackgroundTint(Tint.of(ColorStateList.valueOf(Color.RED)))
        }
        onView(withId(android.R.id.text1)).check(matches(withBackgroundTint { tint ->
            tint.defaultColor == Color.RED
        }))
    }

    @Test
    fun testBinding_setBackgroundTint_None() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .apply {
                    backgroundTintList = ColorStateList.valueOf(Color.RED)
                    setBackgroundTint(Tint.none())
                }
        }
        onView(withId(android.R.id.text1)).check(matches(noBackgroundTint()))
    }

    @Test
    fun testBinding_setContentDescription_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setContentDescription(Text.of(android.R.string.ok))
        }
        onView(withId(android.R.id.text1)).check(matches(withContentDescription("OK")))
    }

    @Test
    fun testBinding_setContentDescription_CharSequence() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setContentDescription(Text.of("test"))
        }
        onView(withId(android.R.id.text1)).check(matches(withContentDescription("test")))
    }

    @Test
    fun testBinding_setPaddingStart() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setPaddingStart(Dimension.px(1f))
        }
        onView(withId(android.R.id.text1)).check(matches(withPaddingStart(1)))
    }

    @Test
    fun testBinding_setPaddingEnd() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setPaddingEnd(Dimension.px(2f))
        }
        onView(withId(android.R.id.text1)).check(matches(withPaddingEnd(2)))
    }

    @Test
    fun testBinding_setPaddingLeft() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setPaddingLeft(Dimension.px(3f))
        }
        onView(withId(android.R.id.text1)).check(matches(withPaddingStart(3)))
    }

    @Test
    fun testBinding_setPaddingRight() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setPaddingRight(Dimension.px(4f))
        }
        onView(withId(android.R.id.text1)).check(matches(withPaddingEnd(4)))
    }

    @Test
    fun testBinding_setPaddingTop() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setPaddingTop(Dimension.px(5f))
        }
        onView(withId(android.R.id.text1)).check(matches(withPaddingTop(5)))
    }

    @Test
    fun testBinding_setPaddingBottom() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setPaddingBottom(Dimension.px(6f))
        }
        onView(withId(android.R.id.text1)).check(matches(withPaddingBottom(6)))
    }

    @Test
    fun testBinding_setPadding() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setPadding(Dimension.px(10f))
        }
        onView(withId(android.R.id.text1)).check(matches(withPadding(10)))
    }

    @Test
    fun testBinding_setMinHeight() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setMinHeight(Dimension.px(10f))
        }
        onView(withId(android.R.id.text1)).check(matches(hasMinHeight(10)))
    }

    @Test
    fun testBinding_setMinWidth() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setMinWidth(Dimension.px(10f))
        }
        onView(withId(android.R.id.text1)).check(matches(hasMinWidth(10)))
    }

    @Test
    fun testBinding_setClickable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setClickable(Bool.TRUE)
        }
        onView(withId(android.R.id.text1)).check(matches(isClickable()))
    }

    @Test
    fun testBinding_setFocusable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setFocusable(Bool.TRUE)
        }
        onView(withId(android.R.id.text1)).check(matches(isFocusable()))
    }

    @Test
    fun testBinding_setEnabled() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setEnabled(Bool.FALSE)
        }
        onView(withId(android.R.id.text1)).check(matches(IsNot(isEnabled())))
    }

    @Test
    fun testBinding_setSoundEffectsEnabled() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setSoundEffectsEnabled(Bool.FALSE)
        }
        onView(withId(android.R.id.text1)).check(matches(IsNot(isSoundEffectsEnabled())))
    }

    private fun hasBackgroundColor(@ColorInt color: Int): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has background color with ${color.toString(16)}")
            }

            override fun matchesSafely(item: View): Boolean {
                return when (val background = item.background) {
                    is ColorDrawable -> background.color == color
                    else -> false
                }
            }
        }
    }

    private fun withBackgroundTint(predicate: (ColorStateList) -> Boolean): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has background tint")
            }

            override fun matchesSafely(item: View): Boolean {
                val backgroundTintList = item.backgroundTintList
                return backgroundTintList != null && predicate(backgroundTintList)
            }
        }
    }

    private inline fun <reified T : Drawable> isDrawableClassOf(): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has background drawable type of ${T::class.java.simpleName}")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.background?.javaClass == T::class.java
            }
        }
    }

    private fun noBackground(): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has background")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.background == null
            }
        }
    }

    private fun noBackgroundTint(): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has background")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.backgroundTintList == null
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withPadding(@Px value: Int): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has padding with $value")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.paddingStart == value &&
                        item.paddingEnd == value &&
                        item.paddingTop == value &&
                        item.paddingBottom == value
            }
        }
    }

    private fun withPaddingStart(@Px value: Int): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has paddingStart with $value")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.paddingStart == value
            }
        }
    }

    private fun withPaddingEnd(@Px value: Int): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has paddingEnd with $value")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.paddingEnd == value
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withPaddingTop(@Px value: Int): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has paddingTop with $value")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.paddingTop == value
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withPaddingBottom(@Px value: Int): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has paddingBottom with $value")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.paddingBottom == value
            }
        }
    }

    private fun hasMinHeight(@Px value: Int): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has paddingBottom with $value")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.minimumHeight == value
            }
        }
    }

    private fun hasMinWidth(@Px value: Int): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has paddingBottom with $value")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.minimumWidth == value
            }
        }
    }

    private fun isSoundEffectsEnabled(): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("is enabled for sound effects")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.isSoundEffectsEnabled
            }
        }
    }
}
