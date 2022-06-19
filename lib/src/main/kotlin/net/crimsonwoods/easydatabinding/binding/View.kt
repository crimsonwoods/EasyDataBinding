package net.crimsonwoods.easydatabinding.binding

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import androidx.core.view.updatePaddingRelative
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.models.Animation
import net.crimsonwoods.easydatabinding.models.Background
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Color
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Float
import net.crimsonwoods.easydatabinding.models.Integer
import net.crimsonwoods.easydatabinding.models.Interpolator
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.Tint
import net.crimsonwoods.easydatabinding.models.asColor
import net.crimsonwoods.easydatabinding.models.asDrawable
import net.crimsonwoods.easydatabinding.models.isColor
import net.crimsonwoods.easydatabinding.models.isDrawable
import net.crimsonwoods.easydatabinding.models.toAnimation
import net.crimsonwoods.easydatabinding.models.toBoolean
import net.crimsonwoods.easydatabinding.models.toCharSequence
import net.crimsonwoods.easydatabinding.models.toColorStateList
import net.crimsonwoods.easydatabinding.models.toDrawable
import net.crimsonwoods.easydatabinding.models.toFloat
import net.crimsonwoods.easydatabinding.models.toInt
import net.crimsonwoods.easydatabinding.models.toInterpolator

@BindingAdapter("android:alpha")
fun View.setAlpha(value: Float) {
    alpha = value.toFloat(resources)
}

@BindingAdapter("android:background")
fun View.setBackground(value: Background?) = when (value) {
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
    is Background.Res -> {
        setBackgroundResource(value.resId)
    }
    is Background.Attr -> {
        when {
            value.attr.isColor(context) -> {
                setBackgroundColor(checkNotNull(value.attr.asColor(context)).toInt(context))
            }
            value.attr.isDrawable(context) -> {
                background = checkNotNull(value.attr.asDrawable(context)).toDrawable(context)
            }
            else -> {
                throw IllegalArgumentException("Unsupported attribute resource is supplied.")
            }
        }
    }
    is Background.Drawable -> {
        background = value.drawable.toDrawable(context)
    }
    is Background.None -> {
        background = null
    }
    null -> {
        background = null
    }
}

@BindingAdapter("android:backgroundTint")
fun View.setBackgroundTint(value: Tint?) = when (value) {
    is Tint.Res -> {
        backgroundTintList = ContextCompat.getColorStateList(context, value.resId)
    }
    is Tint.ColorStateList -> {
        backgroundTintList = value.rawValue
    }
    is Tint.None -> {
        backgroundTintList = null
    }
    null -> {
        backgroundTintList = null
    }
}

@BindingAdapter("android:contentDescription")
fun View.setContentDescription(value: Text?) {
    contentDescription = value?.toCharSequence(resources)
}

@BindingAdapter("android:paddingStart")
fun View.setPaddingStart(value: Dimension) {
    updatePaddingRelative(start = value.toPx(context).roundToInt())
}

@BindingAdapter("android:paddingEnd")
fun View.setPaddingEnd(value: Dimension) {
    updatePaddingRelative(end = value.toPx(context).roundToInt())
}

@BindingAdapter("android:paddingLeft")
fun View.setPaddingLeft(value: Dimension) {
    updatePadding(left = value.toPx(context).roundToInt())
}

@BindingAdapter("android:paddingRight")
fun View.setPaddingRight(value: Dimension) {
    updatePadding(right = value.toPx(context).roundToInt())
}

@BindingAdapter("android:paddingTop")
fun View.setPaddingTop(value: Dimension) {
    updatePaddingRelative(top = value.toPx(context).roundToInt())
}

@BindingAdapter("android:paddingBottom")
fun View.setPaddingBottom(value: Dimension) {
    updatePaddingRelative(bottom = value.toPx(context).roundToInt())
}

@BindingAdapter("android:padding")
fun View.setPadding(value: Dimension) {
    setPadding(value.toPx(context).roundToInt())
}

@BindingAdapter("android:minHeight")
fun View.setMinHeight(value: Dimension) {
    minimumHeight = value.toPx(context).roundToInt()
}

@BindingAdapter("android:minWidth")
fun View.setMinWidth(value: Dimension) {
    minimumWidth = value.toPx(context).roundToInt()
}

@BindingAdapter("android:clickable")
fun View.setClickable(value: Bool) {
    isClickable = value.toBoolean(resources)
}

@BindingAdapter("android:longClickable")
fun View.setLongClickable(value: Bool) {
    isLongClickable = value.toBoolean()
}

@BindingAdapter("android:focusable")
fun View.setFocusable(value: Bool) {
    isFocusable = value.toBoolean(resources)
}

@BindingAdapter("android:enabled")
fun View.setEnabled(value: Bool) {
    isEnabled = value.toBoolean(resources)
}

@BindingAdapter("android:rotation")
fun View.setRotation(value: Float) {
    rotation = value.toFloat(resources)
}

@BindingAdapter("android:rotationX")
fun View.setRotationX(value: Float) {
    rotationX = value.toFloat(resources)
}

@BindingAdapter("android:rotationY")
fun View.setRotationY(value: Float) {
    rotationY = value.toFloat(resources)
}

@BindingAdapter("android:scaleX")
fun View.setScaleX(value: Float) {
    scaleX = value.toFloat(resources)
}

@BindingAdapter("android:scaleY")
fun View.setScaleY(value: Float) {
    scaleY = value.toFloat(resources)
}

@BindingAdapter("android:soundEffectsEnabled")
fun View.setSoundEffectsEnabled(value: Bool) {
    isSoundEffectsEnabled = value.toBoolean(resources)
}

@BindingAdapter("android:visibility")
fun View.setVisibility(value: Integer) {
    visibility = value.toInt(resources)
}

internal val View.toAnimation: Animation.() -> android.view.animation.Animation?
    get() {
        return { toAnimation(context) }
    }

internal val View.toBoolean: Bool.() -> Boolean
    get() {
        return { toBoolean(resources) }
    }

internal val View.toCharSequence: Text.() -> CharSequence
    get() {
        return { toCharSequence(resources) }
    }

internal val View.toInt: Integer.() -> Int
    get() {
        return { toInt(resources) }
    }

internal val View.toPx: Dimension.() -> kotlin.Float
    get() {
        return { toPx(resources) }
    }

internal val View.toColorInt: Color.() -> Int
    get() {
        return { toInt(context) }
    }

internal val View.toColorStateList: Tint.() -> ColorStateList?
    get() {
        return { toColorStateList(context) }
    }

internal val View.toDrawable: Drawable.() -> android.graphics.drawable.Drawable?
    get() {
        return { toDrawable(context) }
    }

internal val View.toInterpolator: Interpolator.() -> android.view.animation.Interpolator?
    get() {
        return { toInterpolator(context) }
    }

internal val View.toFloat: Float.() -> kotlin.Float
    get() {
        return { toFloat(resources) }
    }
