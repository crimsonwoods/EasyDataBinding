package net.crimsonwoods.easydatabinding.models

import androidx.annotation.StyleRes

data class TextAppearance(
    @StyleRes val resId: Int
) {
    companion object {
        @JvmStatic
        fun of(@StyleRes resId: Int): TextAppearance = TextAppearance(resId)
    }
}
