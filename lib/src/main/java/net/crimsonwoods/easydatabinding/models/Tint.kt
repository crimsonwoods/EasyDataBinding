package net.crimsonwoods.easydatabinding.models

import androidx.annotation.ColorRes

sealed class Tint {
    data class Res(
        @ColorRes val resId: Int
    ) : Tint()

    data class ColorStateList(
        val rawValue: android.content.res.ColorStateList
    ) : Tint()

    object None : Tint()

    companion object {
        @JvmStatic
        fun of(
            @ColorRes resId: Int
        ): Tint = Res(resId)

        @JvmStatic
        fun of(
            colorStateList: android.content.res.ColorStateList
        ): Tint = ColorStateList(colorStateList)

        @JvmStatic
        fun none(): Tint = None
    }
}
