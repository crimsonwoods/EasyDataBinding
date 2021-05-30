package net.crimsonwoods.easydatabinding.binding

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Integer

@BindingAdapter("android:baselineAligned")
fun LinearLayout.setBaselineAligned(value: Bool) {
    isBaselineAligned = value.toBoolean()
}

@BindingAdapter("android:baselineAlignedChildIndex")
fun LinearLayout.setBaselineAlignedChildIndex(value: Integer) {
    baselineAlignedChildIndex = value.toInt()
}

@BindingAdapter("android:divider")
fun LinearLayout.setDivider(value: Drawable) {
    dividerDrawable = value.toDrawable()
}

@BindingAdapter("android:measureWithLargestChild")
fun LinearLayout.setMeasureWithLargestChild(value: Bool) {
    isMeasureWithLargestChildEnabled = value.toBoolean()
}

@BindingAdapter("android:dividerPadding")
fun LinearLayout.setDividerPadding(value: Dimension) {
    dividerPadding = value.toPx().roundToInt()
}
