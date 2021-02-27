package net.crimsonwoods.easydatabinding.models

import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IntRange

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

    data class StateList(
        val stateList: android.content.res.ColorStateList
    ) : Color()

    companion object {
        @JvmStatic
        fun argb(
            @ColorInt value: kotlin.Int
        ): Color = Int(value)

        @JvmStatic
        fun argb(
            @IntRange(from = 0, to = 255) alpha: kotlin.Int,
            @IntRange(from = 0, to = 255) red: kotlin.Int,
            @IntRange(from = 0, to = 255) green: kotlin.Int,
            @IntRange(from = 0, to = 255) blue: kotlin.Int
        ): Color = Int(android.graphics.Color.argb(alpha, red, green, blue))

        @JvmStatic
        fun of(
            @ColorRes resId: kotlin.Int
        ): Color = Res(resId)

        @JvmStatic
        fun of(
            color: kotlin.String
        ): Color = String(color)

        @JvmStatic
        fun of(
            drawable: ColorDrawable
        ): Color = Drawable(drawable)

        @JvmStatic
        fun of(
            stateList: android.content.res.ColorStateList
        ): Color = StateList(stateList)
    }
}
