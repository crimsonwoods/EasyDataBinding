package net.crimsonwoods.easydatabinding.binding

import android.widget.ScrollView
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Bool

@BindingAdapter("android:fillViewport")
fun ScrollView.setFillViewport(value: Bool) {
    isFillViewport = value.toBoolean()
}
