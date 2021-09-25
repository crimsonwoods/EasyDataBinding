package net.crimsonwoods.easydatabinding.binding

import android.widget.CompoundButton
import androidx.core.widget.CompoundButtonCompat
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Tint

@BindingAdapter("android:checked")
fun CompoundButton.setChecked(value: Bool) {
    isChecked = value.toBoolean()
}

@BindingAdapter("android:button")
fun CompoundButton.setButtonDrawable(value: Drawable) {
    if (value is Drawable.Res) {
        setButtonDrawable(value.resId)
    } else {
        buttonDrawable = value.toDrawable()
    }
}

@BindingAdapter("android:buttonTint")
fun CompoundButton.setButtonTint(value: Tint) {
    CompoundButtonCompat.setButtonTintList(this, value.toColorStateList())
}
