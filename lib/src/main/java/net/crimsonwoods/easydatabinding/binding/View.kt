package net.crimsonwoods.easydatabinding.binding

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Background

@BindingAdapter("android:background")
fun View.setBackground(color: Background) = when (color) {
    is Background.Int -> {
        setBackgroundColor(color.rawValue)
    }
    is Background.Res -> {
        setBackgroundColor(ContextCompat.getColor(context, color.resId))
    }
    is Background.String -> {
        setBackgroundColor(android.graphics.Color.parseColor(color.color))
    }
    is Background.Drawable -> {
        setBackgroundColor(color.drawable.color)
    }
}
