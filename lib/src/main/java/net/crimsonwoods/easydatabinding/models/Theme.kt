package net.crimsonwoods.easydatabinding.models

import androidx.annotation.StyleRes

data class Theme(
    @StyleRes val resId: Int
) {
    companion object {
        @JvmStatic
        fun of(@StyleRes resId: Int): Theme = Theme(resId)
    }
}
