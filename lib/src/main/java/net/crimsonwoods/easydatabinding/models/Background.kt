package net.crimsonwoods.easydatabinding.models

import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

sealed class Background {
    sealed class Color : Background() {
        data class Int(
            @ColorInt
            val rawValue: kotlin.Int,
        ) : Color()

        data class Res(
            @ColorRes
            val resId: kotlin.Int
        ) : Color()

        data class String(
            val color: kotlin.String
        ) : Color()

        data class Drawable(
            val drawable: ColorDrawable
        ) : Color()
    }

    data class Res(
        @DrawableRes
        val resId: Int
    ) : Background()

    data class Drawable(
        val drawable: android.graphics.drawable.Drawable?
    ) : Background()

    companion object {
        @JvmStatic
        fun of(@DrawableRes resId: Int): Background = Res(resId)

        @JvmStatic
        fun of(drawable: android.graphics.drawable.Drawable?): Background = Drawable(drawable)

        @JvmStatic
        fun argb(alpha: Int, red: Int, green: Int, blue: Int): Background =
            Color.Int(android.graphics.Color.argb(alpha, red, green, blue))

        @JvmStatic
        fun rgb(red: Int, green: Int, blue: Int): Background =
            Color.Int(android.graphics.Color.rgb(red, green, blue))

        @JvmStatic
        fun ofColor(@ColorRes resId: Int): Background = Color.Res(resId)

        @JvmStatic
        fun ofColor(color: String): Background = Color.String(color)

        @JvmStatic
        fun ofColor(drawable: ColorDrawable): Background = Color.Drawable(drawable)
    }
}
