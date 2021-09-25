package net.crimsonwoods.easydatabinding.binding

import android.widget.ListView
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable

@BindingAdapter("android:divider")
fun ListView.setDivider(value: Drawable) {
    divider = value.toDrawable()
}

@BindingAdapter("android:dividerHeight")
fun ListView.setDividerHeight(value: Dimension) {
    dividerHeight = value.toPx().roundToInt()
}

@BindingAdapter("android:footerDividersEnabled")
fun ListView.setFooterDividersEnabled(value: Bool) {
    setFooterDividersEnabled(value.toBoolean())
}

@BindingAdapter("android:headerDividersEnabled")
fun ListView.setHeaderDividersEnabled(value: Bool) {
    setHeaderDividersEnabled(value.toBoolean())
}
