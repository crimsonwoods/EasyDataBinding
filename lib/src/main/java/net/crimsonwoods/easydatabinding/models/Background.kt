package net.crimsonwoods.easydatabinding.models

import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

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

    data class Drawable(
        val drawable: android.graphics.drawable.Drawable?
    ) : Background()
}
