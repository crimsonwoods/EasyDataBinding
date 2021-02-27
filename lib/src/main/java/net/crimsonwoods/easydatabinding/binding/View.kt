package net.crimsonwoods.easydatabinding.binding

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Background

@BindingAdapter("android:background")
fun View.setBackground(color: Background) = when (color) {
    is Background.Color.Int -> {
        setBackgroundColor(color.rawValue)
    }
    is Background.Color.Res -> {
        setBackgroundColor(ContextCompat.getColor(context, color.resId))
    }
    is Background.Color.String -> {
        setBackgroundColor(android.graphics.Color.parseColor(color.color))
    }
    is Background.Color.Drawable -> {
        setBackgroundColor(color.drawable.color)
    }
}
