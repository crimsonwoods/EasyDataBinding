package net.crimsonwoods.easydatabinding.binding

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.BackgroundColor

@BindingAdapter("android:background")
fun View.setBackgroundColor(color: BackgroundColor) = when (color) {
    is BackgroundColor.Int -> {
        setBackgroundColor(color.rawValue)
    }
    is BackgroundColor.Res -> {
        setBackgroundColor(ContextCompat.getColor(context, color.resId))
    }
    is BackgroundColor.String -> {
        setBackgroundColor(android.graphics.Color.parseColor(color.color))
    }
    is BackgroundColor.Drawable -> {
        setBackgroundColor(color.drawable.color)
    }
}
