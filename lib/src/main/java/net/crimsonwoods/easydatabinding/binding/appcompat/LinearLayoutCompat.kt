package net.crimsonwoods.easydatabinding.binding.appcompat

import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.binding.toBoolean
import net.crimsonwoods.easydatabinding.binding.toDrawable
import net.crimsonwoods.easydatabinding.binding.toInt
import net.crimsonwoods.easydatabinding.binding.toPx
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Integer

@BindingAdapter("android:baselineAligned")
fun LinearLayoutCompat.setBaselineAligned(value: Bool) {
    isBaselineAligned = value.toBoolean()
}

@BindingAdapter("android:baselineAlignedChildIndex")
fun LinearLayoutCompat.setBaselineAlignedChildIndex(value: Integer) {
    baselineAlignedChildIndex = value.toInt()
}

@BindingAdapter("android:divider")
fun LinearLayoutCompat.setDivider(value: Drawable) {
    dividerDrawable = value.toDrawable()
}

@BindingAdapter("android:measureWithLargestChild")
fun LinearLayoutCompat.setMeasureWithLargestChild(value: Bool) {
    isMeasureWithLargestChildEnabled = value.toBoolean()
}

@BindingAdapter("android:dividerPadding")
fun LinearLayoutCompat.setDividerPadding(value: Dimension) {
    dividerPadding = value.toPx().roundToInt()
}
