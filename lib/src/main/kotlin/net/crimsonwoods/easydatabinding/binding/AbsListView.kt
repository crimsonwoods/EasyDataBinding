package net.crimsonwoods.easydatabinding.binding

import android.widget.AbsListView
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Bool

@BindingAdapter("android:fastScrollEnabled")
fun AbsListView.setFastScrollEnabled(value: Bool) {
    isFastScrollEnabled = value.toBoolean()
}

@BindingAdapter("android:smoothScrollbar")
fun AbsListView.setSmoothScrollbarEnabled(value: Bool) {
    isSmoothScrollbarEnabled = value.toBoolean()
}

@BindingAdapter("android:stackFromBottom")
fun AbsListView.setStackFromBottom(value: Bool) {
    isStackFromBottom = value.toBoolean()
}

@BindingAdapter("android:textFilterEnabled")
fun AbsListView.setTextFilterEnabled(value: Bool) {
    isTextFilterEnabled = value.toBoolean()
}
