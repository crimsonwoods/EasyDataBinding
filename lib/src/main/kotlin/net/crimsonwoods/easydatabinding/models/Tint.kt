package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat

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

fun Tint.toColorStateList(resources: Resources, theme: Resources.Theme?): ColorStateList? {
    return when (this) {
        is Tint.Res -> ResourcesCompat.getColorStateList(resources, resId, theme)
        is Tint.ColorStateList -> rawValue
        is Tint.None -> null
    }
}

fun Tint.toColorStateList(context: Context): ColorStateList? {
    return toColorStateList(context.resources, context.theme)
}
