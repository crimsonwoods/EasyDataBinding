package net.crimsonwoods.easydatabinding.binding

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Background

@BindingAdapter("android:background")
fun View.setBackground(value: Background) = when (value) {
    is Background.Color.Int -> {
        setBackgroundColor(value.rawValue)
    }
    is Background.Color.Res -> {
        setBackgroundColor(ContextCompat.getColor(context, value.resId))
    }
    is Background.Color.String -> {
        setBackgroundColor(android.graphics.Color.parseColor(value.color))
    }
    is Background.Color.Drawable -> {
        setBackgroundColor(value.drawable.color)
    }
    is Background.Drawable -> {
        background = value.drawable
    }
}
