package net.crimsonwoods.easydatabinding.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Image

@BindingAdapter("android:src")
fun ImageView.setImage(image: Image) = when (image) {
    is Image.Res -> {
        setImageResource(image.resId)
    }
    is Image.Drawable -> {
        setImageDrawable(image.rawValue)
    }
    is Image.Bitmap -> {
        setImageBitmap(image.rawValue)
    }
    is Image.Uri -> {
        setImageURI(image.rawValue)
    }
    is Image.None -> {
        setImageDrawable(null)
    }
}
