package net.crimsonwoods.easydatabinding.matcher

import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.Interpolator
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object ViewMatchers {
    inline fun <reified T : View, reified U : Drawable> withDrawableTypeOf(
        noinline drawable: T.() -> Drawable?
    ): Matcher<View> {
        return DrawableTypeMatcher(T::class, U::class, drawable)
    }

    inline fun <reified T : View, reified U : Interpolator> withInterpolatorTypeOf(
        noinline interpolator: T.() -> Interpolator?
    ): Matcher<View> {
        return InterpolatorTypeMatcher(T::class, U::class, interpolator)
    }

    fun withRotation(value: Float): Matcher<View> {
        return WithRotationMatcher(value)
    }

    fun withRotationX(value: Float): Matcher<View> {
        return WithRotationXMatcher(value)
    }

    fun withRotationY(value: Float): Matcher<View> {
        return WithRotationYMatcher(value)
    }

    fun withScaleX(value: Float): Matcher<View> {
        return WithScaleXMatcher(value)
    }

    fun withScaleY(value: Float): Matcher<View> {
        return WithScaleYMatcher(value)
    }

    private class WithRotationMatcher(
        private val value: Float
    ) : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("with rotation: $value")
        }

        override fun matchesSafely(item: View): Boolean {
            return item.rotation == value
        }
    }

    private class WithRotationXMatcher(
        private val value: Float
    ) : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("with rotationX: $value")
        }

        override fun matchesSafely(item: View): Boolean {
            return item.rotationX == value
        }
    }

    private class WithRotationYMatcher(
        private val value: Float
    ) : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("with rotationY: $value")
        }

        override fun matchesSafely(item: View): Boolean {
            return item.rotationY == value
        }
    }

    private class WithScaleXMatcher(
        private val value: Float
    ) : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("with scaleX: $value")
        }

        override fun matchesSafely(item: View): Boolean {
            return item.scaleX == value
        }
    }

    private class WithScaleYMatcher(
        private val value: Float
    ) : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("with scaleY: $value")
        }

        override fun matchesSafely(item: View): Boolean {
            return item.scaleY == value
        }
    }
}
