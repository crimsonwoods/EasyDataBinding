package net.crimsonwoods.easydatabinding.binding.appcompat

import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.binding.toBoolean
import net.crimsonwoods.easydatabinding.binding.toCharSequence
import net.crimsonwoods.easydatabinding.binding.toColorStateList
import net.crimsonwoods.easydatabinding.binding.toDrawable
import net.crimsonwoods.easydatabinding.binding.toPx
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextAppearance
import net.crimsonwoods.easydatabinding.models.Tint


@BindingAdapter("showText")
fun SwitchCompat.setShowText(value: Bool) {
    showText = value.toBoolean()
}

@BindingAdapter("splitTrack")
fun SwitchCompat.setSplitTrack(value: Bool) {
    splitTrack = value.toBoolean()
}

@BindingAdapter("switchMinWidth")
fun SwitchCompat.setSwitchMinWidth(value: Dimension) {
    switchMinWidth = value.toPx().roundToInt()
}

@BindingAdapter("switchPadding")
fun SwitchCompat.setSwitchPadding(value: Dimension) {
    switchPadding = value.toPx().roundToInt()
}

@BindingAdapter("switchTextAppearance")
fun SwitchCompat.setSwitchTextAppearance(value: TextAppearance) {
    setSwitchTextAppearance(context, value.resId)
}

@BindingAdapter("android:textOff")
fun SwitchCompat.setTextOff(value: Text) {
    textOff = value.toCharSequence()
}

@BindingAdapter("android:textOn")
fun SwitchCompat.setTextOn(value: Text) {
    textOn = value.toCharSequence()
}

@BindingAdapter("android:thumb")
fun SwitchCompat.setThumbDrawable(value: Drawable) {
    thumbDrawable = value.toDrawable()
}

@BindingAdapter("thumbTextPadding")
fun SwitchCompat.setThumbTextPadding(value: Dimension) {
    thumbTextPadding = value.toPx().roundToInt()
}

@BindingAdapter("thumbTint")
fun SwitchCompat.setThumbTintList(value: Tint) {
    thumbTintList = value.toColorStateList()
}

@BindingAdapter("track")
fun SwitchCompat.setTrackDrawable(value: Drawable) {
    trackDrawable = value.toDrawable()
}

@BindingAdapter("trackTint")
fun SwitchCompat.setTrackTintList(value: Tint) {
    trackTintList = value.toColorStateList()
}
