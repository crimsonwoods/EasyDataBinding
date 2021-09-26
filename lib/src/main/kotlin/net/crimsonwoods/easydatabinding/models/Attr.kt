package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.AttrRes

data class Attr(
    @AttrRes val resId: Int,
) {
    companion object {
        @JvmStatic
        fun of(@AttrRes resId: Int): Attr = Attr(resId = resId)
    }
}

fun Attr.isColor(theme: Resources.Theme): Boolean {
    val value = TypedValue()
    if (!theme.resolveAttribute(resId, value, true)) {
        return false
    }
    if (value.type !in TypedValue.TYPE_FIRST_COLOR_INT..TypedValue.TYPE_LAST_COLOR_INT) {
        return false
    }
    return true
}

fun Attr.asColor(theme: Resources.Theme): Color? {
    val value = TypedValue()
    if (!theme.resolveAttribute(resId, value, true)) {
        return null
    }
    if (value.type !in TypedValue.TYPE_FIRST_COLOR_INT..TypedValue.TYPE_LAST_COLOR_INT) {
        return null
    }
    return Color.argb(value.data)
}

fun Attr.isColor(context: Context): Boolean = isColor(context.theme)

fun Attr.asColor(context: Context): Color? = asColor(context.theme)
