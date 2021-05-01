package net.crimsonwoods.easydatabinding.matcher

import android.graphics.drawable.Drawable
import android.view.View
import org.hamcrest.Matcher

object ViewMatchers {
    inline fun <reified T : View, reified U : Drawable> withDrawableTypeOf(
        noinline drawable: T.() -> Drawable?
    ): Matcher<View> {
        return DrawableTypeMatcher(T::class, U::class, drawable)
    }
}
