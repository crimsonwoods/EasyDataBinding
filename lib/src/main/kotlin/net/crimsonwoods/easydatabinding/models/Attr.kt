package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat

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
    return value.type in TypedValue.TYPE_FIRST_COLOR_INT..TypedValue.TYPE_LAST_COLOR_INT
}

fun Attr.asColor(theme: Resources.Theme): Color? {
    val value = TypedValue()
    if (!theme.resolveAttribute(resId, value, true)) {
        return null
    }
    return if (value.type !in TypedValue.TYPE_FIRST_COLOR_INT..TypedValue.TYPE_LAST_COLOR_INT) {
        null
    } else {
        Color.argb(value.data)
    }
}

fun Attr.isColor(context: Context): Boolean = isColor(context.theme)

fun Attr.asColor(context: Context): Color? = asColor(context.theme)

fun Attr.isDrawable(context: Context): Boolean {
    val value = TypedValue()
    if (!context.theme.resolveAttribute(resId, value, true)) {
        return false
    }
    return if (value.type != TypedValue.TYPE_STRING || value.resourceId == 0) {
        false
    } else {
        context.resources.getResourceTypeName(value.resourceId) == "drawable"
    }
}

fun Attr.asDrawable(context: Context): Drawable? {
    val value = TypedValue()
    if (!context.theme.resolveAttribute(resId, value, true)) {
        return null
    }
    return if (value.type != TypedValue.TYPE_STRING || value.resourceId == 0) {
        null
    } else {
        ContextCompat.getDrawable(context, value.resourceId)?.let {
            Drawable.of(it)
        }
    }
}

fun Attr.isDimension(context: Context): Boolean {
    val value = TypedValue()
    if (!context.theme.resolveAttribute(resId, value, true)) {
        return false
    }
    return value.type == TypedValue.TYPE_DIMENSION
}

fun Attr.asDimension(context: Context): Dimension? {
    val value = TypedValue()
    return if (!context.theme.resolveAttribute(resId, value, true)) {
        null
    } else if (value.type != TypedValue.TYPE_DIMENSION) {
        null
    } else when (TypedValue.COMPLEX_UNIT_MASK and (value.data ushr TypedValue.COMPLEX_UNIT_SHIFT)) {
        TypedValue.COMPLEX_UNIT_DIP -> {
            Dimension.dp(TypedValue.complexToFloat(value.data))
        }
        TypedValue.COMPLEX_UNIT_PX -> {
            Dimension.px(TypedValue.complexToFloat(value.data))
        }
        TypedValue.COMPLEX_UNIT_SP -> {
            Dimension.sp(TypedValue.complexToFloat(value.data))
        }
        TypedValue.COMPLEX_UNIT_PT,
        TypedValue.COMPLEX_UNIT_IN,
        TypedValue.COMPLEX_UNIT_MM -> {
            TODO("Not supported yet.")
        }
        else -> {
            null
        }
    }
}
