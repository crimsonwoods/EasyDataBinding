package net.crimsonwoods.easydatabinding.binding

import android.widget.ViewFlipper
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Integer

@BindingAdapter("android:flipInterval")
fun ViewFlipper.setFlipInterval(value: Integer) {
    flipInterval = value.toInt()
}

@BindingAdapter("android:autoStart")
fun ViewFlipper.setAutoStart(value: Bool) {
    isAutoStart = value.toBoolean()
}
