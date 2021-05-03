package net.crimsonwoods.easydatabinding.matcher

import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.Interpolator
import org.hamcrest.Matcher

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
}
