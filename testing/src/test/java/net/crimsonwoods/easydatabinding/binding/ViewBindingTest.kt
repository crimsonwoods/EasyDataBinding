package net.crimsonwoods.easydatabinding.binding

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ColorStateListDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
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
import net.crimsonwoods.easydatabinding.matcher.ViewMatchers.withRotation
import net.crimsonwoods.easydatabinding.matcher.ViewMatchers.withRotationX
import net.crimsonwoods.easydatabinding.matcher.ViewMatchers.withRotationY
import net.crimsonwoods.easydatabinding.matcher.ViewMatchers.withScaleX
import net.crimsonwoods.easydatabinding.matcher.ViewMatchers.withScaleY
import net.crimsonwoods.easydatabinding.models.Background
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Float
import net.crimsonwoods.easydatabinding.models.Integer
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
        scenario = launchFragmentInContainer<TestFragment>(
            themeResId = R.style.Theme_AppCompat_Light,
        ).moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setAlpha_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setAlpha(Float.of(R.dimen.half))
        }
        onView(withId(R.id.border)).check(matches(withAlpha(0.5f)))
    }

    @Test
    fun testBinding_setAlpha_Value() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setAlpha(Float.of(0.1f))
        }
        onView(withId(R.id.border)).check(matches(withAlpha(0.1f)))
    }

    @Test
    fun testBinding_setBackground_Color_Int() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setBackground(Background.Color.Int(0xff))
        }
        onView(withId(R.id.border)).check(matches(hasBackgroundColor(0xff)))
    }

    @Test
    fun testBinding_setBackground_Color_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setBackground(Background.Color.Res(android.R.color.white))
        }
        onView(withId(R.id.border)).check(matches(hasBackgroundColor(Color.WHITE)))
    }

    @Test
    fun testBinding_setBackground_Color_String() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setBackground(Background.Color.String("blue"))
        }
        onView(withId(R.id.border)).check(matches(hasBackgroundColor(Color.BLUE)))
    }

    @Test
    fun testBinding_setBackground_Color_Drawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setBackground(Background.Color.Drawable(ColorDrawable(Color.YELLOW)))
        }
        onView(withId(R.id.border)).check(matches(hasBackgroundColor(Color.YELLOW)))
    }

    @Test
    fun testBinding_setBackground_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setBackground(Background.Res(android.R.drawable.screen_background_dark))
        }
        onView(withId(R.id.border)).check(matches(hasBackgroundColor(Color.parseColor("#ff000000"))))
    }

    @Test
    fun testBinding_setBackground_Drawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setBackground(Background.of(ShapeDrawable(RectShape())))
        }
        onView(withId(R.id.border)).check(matches(isDrawableClassOf<ShapeDrawable>()))
    }

    @Test
    fun testBinding_setBackground_None() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .apply {
                    setBackgroundColor(Color.RED)
                    setBackground(Background.None)
                }
        }
        onView(withId(R.id.border)).check(matches(noBackground()))
    }

    @Test
    fun testBinding_setBackground_null() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .apply {
                    setBackgroundColor(Color.RED)
                    setBackground(null as Background?)
                }
        }
        onView(withId(R.id.border)).check(matches(noBackground()))
    }

    @Test
    fun testBinding_setBackground_Attr_Drawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setBackground(Background.attr(R.attr.selectableItemBackground))
        }
        onView(withId(R.id.border)).check(matches(isDrawableClassOf<RippleDrawable>()))
    }

    @Test
    fun testBinding_setBackground_Attr_Color() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setBackground(Background.attr(R.attr.colorPrimary))
        }
        onView(withId(R.id.border)).check(matches(isDrawableClassOf<ColorDrawable>()))
    }

    @Test
    fun testBinding_setBackgroundTint_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setBackgroundTint(Tint.of(android.R.color.tab_indicator_text))
        }
        onView(withId(R.id.border)).check(
            matches(
                withBackgroundTint { tint ->
                    tint.defaultColor == Color.parseColor("#808080")
                },
            ),
        )
    }

    @Test
    fun testBinding_setBackgroundTint_ColorStateList() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setBackgroundTint(Tint.of(ColorStateList.valueOf(Color.RED)))
        }
        onView(withId(R.id.border)).check(
            matches(
                withBackgroundTint { tint ->
                    tint.defaultColor == Color.RED
                },
            ),
        )
    }

    @Test
    fun testBinding_setBackgroundTint_None() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .apply {
                    backgroundTintList = ColorStateList.valueOf(Color.RED)
                    setBackgroundTint(Tint.none())
                }
        }
        onView(withId(R.id.border)).check(matches(noBackgroundTint()))
    }

    @Test
    fun testBinding_setBackgroundTint_null() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .apply {
                    backgroundTintList = ColorStateList.valueOf(Color.RED)
                    setBackgroundTint(null as Tint?)
                }
        }
        onView(withId(R.id.border)).check(matches(noBackgroundTint()))
    }

    @Test
    fun testBinding_setContentDescription_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setContentDescription(Text.of(android.R.string.ok))
        }
        onView(withId(R.id.border)).check(matches(withContentDescription("OK")))
    }

    @Test
    fun testBinding_setContentDescription_CharSequence() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setContentDescription(Text.of("test"))
        }
        onView(withId(R.id.border)).check(matches(withContentDescription("test")))
    }

    @Test
    fun testBinding_setContentDescription_null() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .contentDescription = "test"
        }
        onView(withId(R.id.border)).check(matches(withContentDescription("test")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setContentDescription(null as Text?)
        }
        onView(withId(R.id.border)).check(matches(withContentDescription(null as String?)))
    }

    @Test
    fun testBinding_setForeground_null() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .apply {
                    foreground = ColorDrawable(Color.RED)
                    setForeground(null as net.crimsonwoods.easydatabinding.models.Drawable?)
                }
        }
        onView(withId(R.id.border)).check(matches(noForeground()))
    }

    @Test
    fun testBinding_setForeground_None() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .apply {
                    foreground = ColorDrawable(Color.RED)
                    setForeground(net.crimsonwoods.easydatabinding.models.Drawable.none())
                }
        }
        onView(withId(R.id.border)).check(matches(noForeground()))
    }

    @Test
    fun testBinding_setForeground_Color() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .apply {
                    foreground = null
                    setForeground(net.crimsonwoods.easydatabinding.models.Drawable.ofColor(Color.RED))
                }
        }
        onView(withId(R.id.border)).check(matches(withForeground(ColorDrawable(Color.RED))))
    }

    @Test
    fun testBinding_setForegroundTint_null() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .apply {
                    foregroundTintList = ColorStateList.valueOf(Color.RED)
                    setForegroundTint(null as Tint?)
                }
        }
        onView(withId(R.id.border)).check(matches(noForegroundTint()))
    }

    @Test
    fun testBinding_setForegroundTint_None() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .apply {
                    foregroundTintList = ColorStateList.valueOf(Color.RED)
                    setForegroundTint(Tint.none())
                }
        }
        onView(withId(R.id.border)).check(matches(noForegroundTint()))
    }

    @Test
    fun testBinding_setForegroundTint_Color() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .apply {
                    foregroundTintList = null
                    setForegroundTint(Tint.of(android.R.color.tab_indicator_text))
                }
        }
        onView(withId(R.id.border)).check(
            matches(
                withForegroundTint { tint ->
                    tint.defaultColor == Color.parseColor("#808080")
                },
            ),
        )
    }

    @Test
    fun testBinding_setPaddingStart() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setPaddingStart(Dimension.px(1f))
        }
        onView(withId(R.id.border)).check(matches(withPaddingStart(1)))
    }

    @Test
    fun testBinding_setPaddingEnd() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setPaddingEnd(Dimension.px(2f))
        }
        onView(withId(R.id.border)).check(matches(withPaddingEnd(2)))
    }

    @Test
    fun testBinding_setPaddingLeft() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setPaddingLeft(Dimension.px(3f))
        }
        onView(withId(R.id.border)).check(matches(withPaddingStart(3)))
    }

    @Test
    fun testBinding_setPaddingRight() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setPaddingRight(Dimension.px(4f))
        }
        onView(withId(R.id.border)).check(matches(withPaddingEnd(4)))
    }

    @Test
    fun testBinding_setPaddingTop() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setPaddingTop(Dimension.px(5f))
        }
        onView(withId(R.id.border)).check(matches(withPaddingTop(5)))
    }

    @Test
    fun testBinding_setPaddingBottom() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setPaddingBottom(Dimension.px(6f))
        }
        onView(withId(R.id.border)).check(matches(withPaddingBottom(6)))
    }

    @Test
    fun testBinding_setPadding() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setPadding(Dimension.px(10f))
        }
        onView(withId(R.id.border)).check(matches(withPadding(10)))
    }

    @Test
    fun testBinding_setMinHeight() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setMinHeight(Dimension.px(10f))
        }
        onView(withId(R.id.border)).check(matches(hasMinHeight(10)))
    }

    @Test
    fun testBinding_setMinWidth() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setMinWidth(Dimension.px(10f))
        }
        onView(withId(R.id.border)).check(matches(hasMinWidth(10)))
    }

    @Test
    fun testBinding_setClickable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setClickable(Bool.TRUE)
        }
        onView(withId(R.id.border)).check(matches(isClickable()))
    }

    @Test
    fun testBinding_setLongClickable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setLongClickable(Bool.TRUE)
        }
        onView(withId(R.id.border)).check(matches(isLongClickable()))
    }

    @Test
    fun testBinding_setFocusable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setFocusable(Bool.TRUE)
        }
        onView(withId(R.id.border)).check(matches(isFocusable()))
    }

    @Test
    fun testBinding_setEnabled() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setEnabled(Bool.FALSE)
        }
        onView(withId(R.id.border)).check(matches(IsNot(isEnabled())))
    }

    @Test
    fun testBinding_setRotation() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setRotation(Float.of(90f))
        }
        onView(withId(R.id.border)).check(matches(withRotation(90f)))
    }

    @Test
    fun testBinding_setRotationX() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setRotationX(Float.of(90f))
        }
        onView(withId(R.id.border)).check(matches(withRotationX(90f)))
    }

    @Test
    fun testBinding_setRotationY() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setRotationY(Float.of(90f))
        }
        onView(withId(R.id.border)).check(matches(withRotationY(90f)))
    }

    @Test
    fun testBinding_setScaleX() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setScaleX(Float.of(1.5f))
        }
        onView(withId(R.id.border)).check(matches(withScaleX(1.5f)))
    }

    @Test
    fun testBinding_setScaleY() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setScaleY(Float.of(1.5f))
        }
        onView(withId(R.id.border)).check(matches(withScaleY(1.5f)))
    }

    @Test
    fun testBinding_setSoundEffectsEnabled() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setSoundEffectsEnabled(Bool.FALSE)
        }
        onView(withId(R.id.border)).check(matches(IsNot(isSoundEffectsEnabled())))
    }

    @Test
    fun testBinding_setVisibility_Visible() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setVisibility(Integer.wrap(View.VISIBLE))
        }
        onView(withId(R.id.border)).check(
            matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE,
                ),
            ),
        )
    }

    @Test
    fun testBinding_setVisibility_Invisible() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setVisibility(Integer.wrap(View.INVISIBLE))
        }
        onView(withId(R.id.border)).check(
            matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.INVISIBLE,
                ),
            ),
        )
    }

    @Test
    fun testBinding_setVisibility_Gone() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<View>(R.id.border)
                .setVisibility(Integer.wrap(View.GONE))
        }
        onView(withId(R.id.border)).check(
            matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.GONE,
                ),
            ),
        )
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

    private fun noForeground(): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has foreground")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.foreground == null
            }
        }
    }

    private fun withForeground(value: Drawable?): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has foreground with $value")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.foreground.drawableEquals(value)
            }

            private fun Drawable?.drawableEquals(other: Drawable?): Boolean {
                if ((this == null && other == null) || (this === other)) {
                    return true
                }
                if (this == null || other == null) {
                    return false
                }
                if (this.javaClass !== other.javaClass) {
                    return false
                }
                return when (this) {
                    is ColorDrawable -> {
                        color == (other as ColorDrawable).color
                    }
                    is ColorStateListDrawable -> {
                        colorStateList == (other as ColorStateListDrawable).colorStateList
                    }
                    is BitmapDrawable -> {
                        bitmap == (other as BitmapDrawable).bitmap
                    }
                    else -> {
                        // other types are not supported.
                        false
                    }
                }
            }
        }
    }

    private fun noForegroundTint(): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has foreground")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.foregroundTintList == null
            }
        }
    }

    private fun withForegroundTint(predicate: (ColorStateList) -> Boolean): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has foreground tint")
            }

            override fun matchesSafely(item: View): Boolean {
                val tintList = item.foregroundTintList
                return tintList != null && predicate(tintList)
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

    private fun isLongClickable(): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("is long-clickable")
            }

            override fun matchesSafely(item: View): Boolean {
                return item.isLongClickable
            }
        }
    }
}
