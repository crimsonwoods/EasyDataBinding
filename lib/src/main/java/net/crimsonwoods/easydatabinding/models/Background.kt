package net.crimsonwoods.easydatabinding.models

import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

sealed class Background {
    data class Int(
        @ColorInt
        val rawValue: kotlin.Int,
    ) : Background()

    data class Res(
        @ColorRes
        val resId: kotlin.Int
    ) : Background()

    data class String(
        val color: kotlin.String
    ) : Background()

    data class Drawable(
        val drawable: ColorDrawable
    ) : Background()
}
