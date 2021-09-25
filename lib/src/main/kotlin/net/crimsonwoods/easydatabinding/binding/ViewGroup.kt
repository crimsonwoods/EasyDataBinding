package net.crimsonwoods.easydatabinding.binding

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.toBoolean

@BindingAdapter("android:clipChildren")
fun ViewGroup.setClipChildren(value: Bool) {
    clipChildren = value.toBoolean(resources)
}

@BindingAdapter("android:clipToPadding")
fun ViewGroup.setClipToPadding(value: Bool) {
    clipToPadding = value.toBoolean(resources)
}
