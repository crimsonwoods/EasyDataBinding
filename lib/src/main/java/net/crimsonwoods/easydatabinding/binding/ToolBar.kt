package net.crimsonwoods.easydatabinding.binding

import android.os.Build
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.models.Color
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextAppearance
import net.crimsonwoods.easydatabinding.models.Theme

@RequiresApi(value = Build.VERSION_CODES.Q)
@BindingAdapter("android:collapseContentDescription")
fun Toolbar.setCollapseContentDescription(value: Text) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        collapseContentDescription = value.toCharSequence()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:collapseContentDescription\" attribute is being supported after Android Q or later."
        )
    }
}

@RequiresApi(value = Build.VERSION_CODES.Q)
@BindingAdapter("android:collapseIcon")
fun Toolbar.setCollapseIcon(value: Drawable) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        collapseIcon = value.toDrawable()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:collapseIcon\" attribute is being supported after Android Q or later."
        )
    }
}

@BindingAdapter("android:contentInsetEnd")
fun Toolbar.setContentInsetEnd(value: Dimension) {
    setContentInsetsRelative(contentInsetStart, value.toPx().roundToInt())
}

@RequiresApi(value = Build.VERSION_CODES.N)
@BindingAdapter("android:contentInsetEndWithActions")
fun Toolbar.setContentInsetEndWithActions(value: Dimension) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        contentInsetEndWithActions = value.toPx().roundToInt()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:contentInsetEndWithActions\" attribute is being supported after Android N or later."
        )
    }
}

@BindingAdapter("android:contentInsetLeft")
fun Toolbar.setContentInsetLeft(value: Dimension) {
    setContentInsetsAbsolute(value.toPx().roundToInt(), contentInsetRight)
}

@BindingAdapter("android:contentInsetRight")
fun Toolbar.setContentInsetRight(value: Dimension) {
    setContentInsetsAbsolute(contentInsetLeft, value.toPx().roundToInt())
}

@BindingAdapter("android:contentInsetStart")
fun Toolbar.setContentInsetStart(value: Dimension) {
    setContentInsetsRelative(value.toPx().roundToInt(), contentInsetEnd)
}

@RequiresApi(value = Build.VERSION_CODES.N)
@BindingAdapter("android:contentInsetStartWithNavigation")
fun Toolbar.setContentInsetStartWithNavigation(value: Dimension) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        contentInsetEndWithActions = value.toPx().roundToInt()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:contentInsetStartWithNavigation\" attribute is being supported after Android N or later."
        )
    }
}

@BindingAdapter("android:logo")
fun Toolbar.setLogo(value: Drawable) {
    logo = value.toDrawable()
}

@BindingAdapter("android:logoDescription")
fun Toolbar.setLogoDescription(value: Text) {
    logoDescription = value.toCharSequence()
}

@BindingAdapter("android:navigationContentDescription")
fun Toolbar.setNavigationContentDescription(value: Text) {
    navigationContentDescription = value.toCharSequence()
}

@BindingAdapter("android:navigationIcon")
fun Toolbar.setNavigationIcon(value: Drawable) {
    navigationIcon = value.toDrawable()
}

@BindingAdapter("android:popupTheme")
fun Toolbar.setPopupTheme(value: Theme) {
    popupTheme = value.resId
}

@BindingAdapter("android:subtitle")
fun Toolbar.setSubtitle(value: Text) {
    subtitle = value.toCharSequence()
}

@BindingAdapter("android:subtitleTextAppearance")
fun Toolbar.setSubtitleTextAppearance(value: TextAppearance) {
    setSubtitleTextAppearance(context, value.resId)
}

@BindingAdapter("android:subtitleTextColor")
fun Toolbar.setSubtitleTextColor(value: Color) {
    setSubtitleTextColor(value.toColorInt())
}

@BindingAdapter("android:title")
fun Toolbar.setTitle(value: Text) {
    title = value.toCharSequence()
}

@RequiresApi(value = Build.VERSION_CODES.N)
@BindingAdapter("android:titleMargin")
fun Toolbar.setTitleMargin(value: Dimension) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val margin = value.toPx().roundToInt()
        setTitleMargin(margin, margin, margin, margin)
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:titleMargin\" attribute is being supported after Android N or later."
        )
    }
}

@RequiresApi(value = Build.VERSION_CODES.N)
@BindingAdapter("android:titleMarginBottom")
fun Toolbar.setTitleMarginBottom(value: Dimension) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        titleMarginBottom = value.toPx().roundToInt()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:titleMarginBottom\" attribute is being supported after Android N or later."
        )
    }
}

@RequiresApi(value = Build.VERSION_CODES.N)
@BindingAdapter("android:titleMarginEnd")
fun Toolbar.setTitleMarginEnd(value: Dimension) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        titleMarginEnd = value.toPx().roundToInt()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:titleMarginEnd\" attribute is being supported after Android N or later."
        )
    }
}

@RequiresApi(value = Build.VERSION_CODES.N)
@BindingAdapter("android:titleMarginStart")
fun Toolbar.setTitleMarginStart(value: Dimension) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        titleMarginStart = value.toPx().roundToInt()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:titleMarginStart\" attribute is being supported after Android N or later."
        )
    }
}

@RequiresApi(value = Build.VERSION_CODES.N)
@BindingAdapter("android:titleMarginTop")
fun Toolbar.setTitleMarginTop(value: Dimension) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        titleMarginTop = value.toPx().roundToInt()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:titleMarginTop\" attribute is being supported after Android N or later."
        )
    }
}

@BindingAdapter("android:titleTextAppearance")
fun Toolbar.setTitleTextAppearance(value: TextAppearance) {
    setTitleTextAppearance(context, value.resId)
}

@BindingAdapter("android:titleTextColor")
fun Toolbar.setTitleTextColor(value: Color) {
    setTitleTextColor(value.toColorInt())
}
