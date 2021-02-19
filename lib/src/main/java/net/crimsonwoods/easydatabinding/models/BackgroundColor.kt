package net.crimsonwoods.easydatabinding.models

import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

sealed class BackgroundColor {
    data class Int(
        @ColorInt
        val rawValue: kotlin.Int,
    ) : BackgroundColor()

    data class Res(
        @ColorRes
        val resId: kotlin.Int
    ) : BackgroundColor()

    data class String(
        val color: kotlin.String
    ) : BackgroundColor()

    data class Drawable(
        val drawable: ColorDrawable
    ) : BackgroundColor()
}
