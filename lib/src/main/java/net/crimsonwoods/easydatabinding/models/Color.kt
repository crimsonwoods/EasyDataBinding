package net.crimsonwoods.easydatabinding.models

import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

sealed class Color {
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

    data class ColorStateList(
        val stateList: android.content.res.ColorStateList
    ) : Color()
}
