@file:SuppressLint("UseSwitchCompatOrMaterialCode")

package net.crimsonwoods.easydatabinding.binding

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Switch
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextAppearance
import net.crimsonwoods.easydatabinding.models.Tint

@BindingAdapter("android:showText")
fun Switch.setShowText(value: Bool) {
    showText = value.toBoolean()
}

@BindingAdapter("android:splitTrack")
fun Switch.setSplitTrack(value: Bool) {
    splitTrack = value.toBoolean()
}

@BindingAdapter("android:switchMinWidth")
fun Switch.setSwitchMinWidth(value: Dimension) {
    switchMinWidth = value.toPx().roundToInt()
}

@BindingAdapter("android:switchPadding")
fun Switch.setSwitchPadding(value: Dimension) {
    switchPadding = value.toPx().roundToInt()
}

@BindingAdapter("android:switchTextAppearance")
fun Switch.setSwitchTextAppearance(value: TextAppearance) {
    setSwitchTextAppearance(context, value.resId)
}

@BindingAdapter("android:textOff")
fun Switch.setTextOff(value: Text) {
    textOff = value.toCharSequence()
}

@BindingAdapter("android:textOn")
fun Switch.setTextOn(value: Text) {
    textOn = value.toCharSequence()
}

@BindingAdapter("android:thumb")
fun Switch.setThumbDrawable(value: Drawable) {
    thumbDrawable = value.toDrawable()
}

@BindingAdapter("android:thumbTextPadding")
fun Switch.setThumbTextPadding(value: Dimension) {
    thumbTextPadding = value.toPx().roundToInt()
}

@RequiresApi(value = Build.VERSION_CODES.M)
@BindingAdapter("android:thumbTint")
fun Switch.setThumbTintList(value: Tint) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        thumbTintList = value.toColorStateList()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:thumbTint\" attribute is being supported after Android M or later."
        )
    }
}

@BindingAdapter("android:track")
fun Switch.setTrackDrawable(value: Drawable) {
    trackDrawable = value.toDrawable()
}

@RequiresApi(value = Build.VERSION_CODES.M)
@BindingAdapter("android:trackTint")
fun Switch.setTrackTintList(value: Tint) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        trackTintList = value.toColorStateList()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:trackTint\" attribute is being supported after Android M or later."
        )
    }
}
