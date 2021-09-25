package net.crimsonwoods.easydatabinding.binding

import android.widget.SearchView
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Text

@BindingAdapter("android:iconifiedByDefault")
fun SearchView.setIconifiedByDefault(value: Bool) {
    isIconifiedByDefault = value.toBoolean()
}

@BindingAdapter("android:maxWidth")
fun SearchView.setMaxWidth(value: Dimension) {
    maxWidth = value.toPx().roundToInt()
}

@BindingAdapter("android:queryHint")
fun SearchView.setQueryHint(value: Text) {
    queryHint = value.toCharSequence()
}
