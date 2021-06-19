package net.crimsonwoods.easydatabinding.binding.appcompat

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.binding.toCharSequence
import net.crimsonwoods.easydatabinding.binding.toColorInt
import net.crimsonwoods.easydatabinding.binding.toDrawable
import net.crimsonwoods.easydatabinding.binding.toPx
import net.crimsonwoods.easydatabinding.models.Color
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextAppearance
import net.crimsonwoods.easydatabinding.models.Theme

@BindingAdapter("collapseContentDescription")
fun Toolbar.setCollapseContentDescription(value: Text) {
    collapseContentDescription = value.toCharSequence()
}

@BindingAdapter("collapseIcon")
fun Toolbar.setCollapseIcon(value: Drawable) {
    collapseIcon = value.toDrawable()
}

@BindingAdapter("contentInsetEnd")
fun Toolbar.setContentInsetEnd(value: Dimension) {
    setContentInsetsRelative(contentInsetStart, value.toPx().roundToInt())
}

@BindingAdapter("contentInsetEndWithActions")
fun Toolbar.setContentInsetEndWithActions(value: Dimension) {
    contentInsetEndWithActions = value.toPx().roundToInt()
}

@BindingAdapter("contentInsetLeft")
fun Toolbar.setContentInsetLeft(value: Dimension) {
    setContentInsetsAbsolute(value.toPx().roundToInt(), contentInsetRight)
}

@BindingAdapter("contentInsetRight")
fun Toolbar.setContentInsetRight(value: Dimension) {
    setContentInsetsAbsolute(contentInsetLeft, value.toPx().roundToInt())
}

@BindingAdapter("contentInsetStart")
fun Toolbar.setContentInsetStart(value: Dimension) {
    setContentInsetsRelative(value.toPx().roundToInt(), contentInsetEnd)
}

@BindingAdapter("contentInsetStartWithNavigation")
fun Toolbar.setContentInsetStartWithNavigation(value: Dimension) {
    contentInsetStartWithNavigation = value.toPx().roundToInt()
}

@BindingAdapter("logo")
fun Toolbar.setLogo(value: Drawable) {
    logo = value.toDrawable()
}

@BindingAdapter("logoDescription")
fun Toolbar.setLogoDescription(value: Text) {
    logoDescription = value.toCharSequence()
}

@BindingAdapter("navigationContentDescription")
fun Toolbar.setNavigationContentDescription(value: Text) {
    navigationContentDescription = value.toCharSequence()
}

@BindingAdapter("navigationIcon")
fun Toolbar.setNavigationIcon(value: Drawable) {
    navigationIcon = value.toDrawable()
}

@BindingAdapter("popupTheme")
fun Toolbar.setPopupTheme(value: Theme) {
    popupTheme = value.resId
}

@BindingAdapter("subtitle")
fun Toolbar.setSubtitle(value: Text) {
    subtitle = value.toCharSequence()
}

@BindingAdapter("subtitleTextAppearance")
fun Toolbar.setSubtitleTextAppearance(value: TextAppearance) {
    setSubtitleTextAppearance(context, value.resId)
}

@BindingAdapter("subtitleTextColor")
fun Toolbar.setSubtitleTextColor(value: Color) {
    setSubtitleTextColor(value.toColorInt())
}

@BindingAdapter("title")
fun Toolbar.setTitle(value: Text) {
    title = value.toCharSequence()
}

@BindingAdapter("titleMargin")
fun Toolbar.setTitleMargin(value: Dimension) {
    val margin = value.toPx().roundToInt()
    setTitleMargin(margin, margin, margin, margin)
}

@BindingAdapter("titleMarginBottom")
fun Toolbar.setTitleMarginBottom(value: Dimension) {
    titleMarginBottom = value.toPx().roundToInt()
}

@BindingAdapter("titleMarginEnd")
fun Toolbar.setTitleMarginEnd(value: Dimension) {
    titleMarginEnd = value.toPx().roundToInt()
}

@BindingAdapter("titleMarginStart")
fun Toolbar.setTitleMarginStart(value: Dimension) {
    titleMarginStart = value.toPx().roundToInt()
}

@BindingAdapter("titleMarginTop")
fun Toolbar.setTitleMarginTop(value: Dimension) {
    titleMarginTop = value.toPx().roundToInt()
}

@BindingAdapter("titleTextAppearance")
fun Toolbar.setTitleTextAppearance(value: TextAppearance) {
    setTitleTextAppearance(context, value.resId)
}

@BindingAdapter("titleTextColor")
fun Toolbar.setTitleTextColor(value: Color) {
    setTitleTextColor(value.toColorInt())
}
