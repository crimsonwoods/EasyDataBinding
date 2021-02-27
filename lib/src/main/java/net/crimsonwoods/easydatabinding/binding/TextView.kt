package net.crimsonwoods.easydatabinding.binding

import android.os.Build
import android.util.TypedValue
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Color
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextSize

@BindingAdapter("android:text")
fun TextView.setText(text: Text) = when (text) {
    is Text.Res -> {
        if (text.args.isNotEmpty()) {
            setText(context.getString(text.resId, *text.args.toTypedArray()))
        } else {
            setText(text.resId)
        }
    }
    is Text.CharSequence -> {
        setText(text.rawValue)
    }
    is Text.Multilingual -> {
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0]
        } else {
            @Suppress("DEPRECATION")
            context.resources.configuration.locale
        }
        setText(text.values[locale] ?: text.fallback ?: context.getString(text.fallbackResId))
    }
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

@BindingAdapter("android:hintTextColor")
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
fun TextView.setTextSize(size: TextSize) = when (size) {
    is TextSize.Px -> {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, size.rawValue)
    }
    is TextSize.Sp -> {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size.rawValue)
    }
    is TextSize.Dp -> {
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, size.rawValue)
    }
    is TextSize.Res -> {
        setTextSize(TypedValue.COMPLEX_UNIT_FRACTION, context.resources.getDimension(size.resId))
    }
}
