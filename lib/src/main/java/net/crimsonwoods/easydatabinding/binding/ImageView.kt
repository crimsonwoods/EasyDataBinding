package net.crimsonwoods.easydatabinding.binding

import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Image
import net.crimsonwoods.easydatabinding.models.Integer
import net.crimsonwoods.easydatabinding.models.Tint
import net.crimsonwoods.easydatabinding.models.toBoolean
import net.crimsonwoods.easydatabinding.models.toColorStateList
import net.crimsonwoods.easydatabinding.models.toInt

@BindingAdapter("android:cropToPadding")
fun ImageView.setCropToPadding(value: Bool) {
    cropToPadding = value.toBoolean(resources)
}

@BindingAdapter("android:maxHeight")
fun ImageView.setMaxHeight(value: Dimension) {
    maxHeight = value.toPx(resources).roundToInt()
}

@BindingAdapter("android:maxWidth")
fun ImageView.setMaxWidth(value: Dimension) {
    maxWidth = value.toPx(resources).roundToInt()
}

@BindingAdapter("android:scaleType")
fun ImageView.setScaleType(value: Integer) {
    scaleType = when (val v = value.toInt(resources)) {
        0 -> ImageView.ScaleType.MATRIX
        1 -> ImageView.ScaleType.FIT_XY
        2 -> ImageView.ScaleType.FIT_START
        3 -> ImageView.ScaleType.FIT_CENTER
        4 -> ImageView.ScaleType.FIT_END
        5 -> ImageView.ScaleType.CENTER
        6 -> ImageView.ScaleType.CENTER_CROP
        7 -> ImageView.ScaleType.CENTER_INSIDE
        else -> throw IllegalArgumentException("Unknown scale type $v is given.")
    }
}

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

@BindingAdapter("tint")
fun ImageView.setTintList(value: Tint) {
    ImageViewCompat.setImageTintList(this, value.toColorStateList(context))
}
