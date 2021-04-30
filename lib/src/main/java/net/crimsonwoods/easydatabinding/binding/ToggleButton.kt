package net.crimsonwoods.easydatabinding.binding

import android.widget.ToggleButton
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Text

@BindingAdapter("android:textOff")
fun ToggleButton.setTextOff(value: Text) {
    textOff = value.toCharSequence()
}

@BindingAdapter("android:textOn")
fun ToggleButton.setTextOn(value: Text) {
    textOn = value.toCharSequence()
}
