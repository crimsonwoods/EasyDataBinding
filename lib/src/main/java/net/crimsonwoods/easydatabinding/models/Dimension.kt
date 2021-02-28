package net.crimsonwoods.easydatabinding.models

import androidx.annotation.DimenRes

sealed class Dimension {
    data class Px(
        @androidx.annotation.Px
        val rawValue: Float
    ) : Dimension()

    data class Sp(
        val rawValue: Float
    ) : Dimension()

    data class Dp(
        val rawValue: Float
    ) : Dimension()

    data class Res(
        @DimenRes
        val resId: Int
    ) : Dimension()

    companion object {
        @JvmStatic
        fun px(value: Float): Dimension = Px(value)

        @JvmStatic
        fun sp(value: Float): Dimension = Sp(value)

        @JvmStatic
        fun dp(value: Float): Dimension = Dp(value)

        @JvmStatic
        fun of(@DimenRes resId: Int): Dimension = Res(resId)
    }
}
