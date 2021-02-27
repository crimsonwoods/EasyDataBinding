package net.crimsonwoods.easydatabinding.models

import androidx.annotation.DimenRes

sealed class TextSize {
    data class Px(
        @androidx.annotation.Px
        val rawValue: Float
    ) : TextSize()

    data class Sp(
        val rawValue: Float
    ) : TextSize()

    data class Dp(
        val rawValue: Float
    ) : TextSize()

    data class Res(
        @DimenRes
        val resId: Int
    ) : TextSize()

    companion object {
        @JvmStatic
        fun px(value: Float): TextSize = Px(value)

        @JvmStatic
        fun sp(value: Float): TextSize = Sp(value)

        @JvmStatic
        fun dp(value: Float): TextSize = Dp(value)

        @JvmStatic
        fun of(@DimenRes resId: Int): TextSize = Res(resId)
    }
}
