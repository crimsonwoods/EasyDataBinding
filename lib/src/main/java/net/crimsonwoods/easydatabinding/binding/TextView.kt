package net.crimsonwoods.easydatabinding.binding

import android.util.TypedValue
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Color
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextAppearance
import net.crimsonwoods.easydatabinding.models.toCharSequence

@BindingAdapter("android:text")
fun TextView.setText(text: Text) {
    this.text = text.toCharSequence(resources)
}

@BindingAdapter("android:textAllCaps")
fun TextView.setTextAllCaps(value: Bool) {
    isAllCaps = value.toBoolean()
}

@BindingAdapter("android:textAppearance")
fun TextView.setTextAppearance(value: TextAppearance) {
    TextViewCompat.setTextAppearance(this, value.resId)
}

@BindingAdapter("android:textColor")
fun TextView.setTextColor(color: Color) = when (color) {
    is Color.Int -> {
        setTextColor(color.rawValue)
    }
    is Color.Res -> {
        setTextColor(ContextCompat.getColor(context, color.resId))
    }
    is Color.String -> {
        setTextColor(android.graphics.Color.parseColor(color.color))
    }
    is Color.Drawable -> {
        setTextColor(color.drawable.color)
    }
    is Color.StateList -> {
        setTextColor(color.stateList)
    }
}

@BindingAdapter("android:hint")
fun TextView.setHint(value: Text) {
    hint = value.toCharSequence()
}

@BindingAdapter("android:textColorHint")
fun TextView.setHintTextColor(color: Color) = when (color) {
    is Color.Int -> {
        setHintTextColor(color.rawValue)
    }
    is Color.Res -> {
        setHintTextColor(ContextCompat.getColor(context, color.resId))
    }
    is Color.String -> {
        setHintTextColor(android.graphics.Color.parseColor(color.color))
    }
    is Color.Drawable -> {
        setHintTextColor(color.drawable.color)
    }
    is Color.StateList -> {
        setHintTextColor(color.stateList)
    }
}

@BindingAdapter("android:textSize")
fun TextView.setTextSize(size: Dimension) = when (size) {
    is Dimension.Px -> {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, size.rawValue)
    }
    is Dimension.Sp -> {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size.rawValue)
    }
    is Dimension.Dp -> {
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, size.rawValue)
    }
    is Dimension.Res -> {
        setTextSize(TypedValue.COMPLEX_UNIT_FRACTION, context.resources.getDimension(size.resId))
    }
}
