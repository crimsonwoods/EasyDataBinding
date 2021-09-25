package net.crimsonwoods.easydatabinding.binding

import android.os.Build
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Integer
import net.crimsonwoods.easydatabinding.models.Interpolator
import net.crimsonwoods.easydatabinding.models.Tint

@BindingAdapter("android:indeterminate")
fun ProgressBar.setIndeterminate(value: Bool) {
    this.isIndeterminate = value.toBoolean()
}

@BindingAdapter("android:indeterminateDrawable")
fun ProgressBar.setIndeterminateDrawable(value: Drawable) {
    indeterminateDrawable = value.toDrawable()
}

@BindingAdapter("android:indeterminateTint")
fun ProgressBar.setIndeterminateTintList(value: Tint) {
    indeterminateTintList = value.toColorStateList()
}

@BindingAdapter("android:interpolator")
fun ProgressBar.setInterpolator(value: Interpolator) {
    interpolator = value.toInterpolator() ?: LinearInterpolator()
}

@BindingAdapter("android:max")
fun ProgressBar.setMax(value: Integer) {
    max = value.toInt()
}

@RequiresApi(value = Build.VERSION_CODES.Q)
@BindingAdapter("android:maxHeight")
fun ProgressBar.setMaxHeight(value: Dimension) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        maxHeight = value.toPx().roundToInt()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:maxHeight\" attribute is being supported after Android Q or later."
        )
    }
}

@RequiresApi(value = Build.VERSION_CODES.Q)
@BindingAdapter("android:maxWidth")
fun ProgressBar.setMaxWidth(value: Dimension) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        maxWidth = value.toPx().roundToInt()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:maxWidth\" attribute is being supported after Android Q or later."
        )
    }
}

@RequiresApi(value = Build.VERSION_CODES.O)
@BindingAdapter("android:min")
fun ProgressBar.setMin(value: Integer) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        min = value.toInt()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:min\" attribute is being supported after Android Q or later."
        )
    }
}

@RequiresApi(value = Build.VERSION_CODES.Q)
@BindingAdapter("android:minHeight")
fun ProgressBar.setMinHeight(value: Dimension) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        minHeight = value.toPx().roundToInt()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:minHeight\" attribute is being supported after Android Q or later."
        )
    }
}

@RequiresApi(value = Build.VERSION_CODES.Q)
@BindingAdapter("android:minWidth")
fun ProgressBar.setMinWidth(value: Dimension) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        minWidth = value.toPx().roundToInt()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:minWidth\" attribute is being supported after Android Q or later."
        )
    }
}

@BindingAdapter("android:progress")
fun ProgressBar.setProgress(value: Integer) {
    progress = value.toInt()
}

@BindingAdapter("android:progressBackgroundTint")
fun ProgressBar.setProgressBackgroundTintList(value: Tint) {
    progressBackgroundTintList = value.toColorStateList()
}

@BindingAdapter("android:progressDrawable")
fun ProgressBar.setProgressDrawable(value: Drawable) {
    progressDrawable = value.toDrawable()
}

@BindingAdapter("android:progressTint")
fun ProgressBar.setProgressTintList(value: Tint) {
    progressTintList = value.toColorStateList()
}

@BindingAdapter("android:secondaryProgress")
fun ProgressBar.setSecondaryProgress(value: Integer) {
    secondaryProgress = value.toInt()
}

@BindingAdapter("android:secondaryProgressTint")
fun ProgressBar.setSecondaryProgressTint(value: Tint) {
    secondaryProgressTintList = value.toColorStateList()
}
