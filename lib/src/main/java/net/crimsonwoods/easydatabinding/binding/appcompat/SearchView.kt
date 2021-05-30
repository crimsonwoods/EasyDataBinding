package net.crimsonwoods.easydatabinding.binding.appcompat

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.binding.toBoolean
import net.crimsonwoods.easydatabinding.binding.toCharSequence
import net.crimsonwoods.easydatabinding.binding.toPx
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Text

@BindingAdapter("iconifiedByDefault")
fun SearchView.setIconifiedByDefault(value: Bool) {
    setIconifiedByDefault(value.toBoolean())
}

@BindingAdapter("android:maxWidth")
fun SearchView.setMaxWidth(value: Dimension) {
    maxWidth = value.toPx().roundToInt()
}

@BindingAdapter("queryHint")
fun SearchView.setQueryHint(value: Text) {
    queryHint = value.toCharSequence()
}
