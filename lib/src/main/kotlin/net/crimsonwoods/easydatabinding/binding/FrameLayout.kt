package net.crimsonwoods.easydatabinding.binding

import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Bool

@BindingAdapter("android:measureAllChildren")
fun FrameLayout.setMeasureAllChildren(value: Bool) {
    measureAllChildren = value.toBoolean()
}
