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
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Fraction
import net.crimsonwoods.easydatabinding.models.Integer
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.Tint
import net.crimsonwoods.easydatabinding.models.toAnimation
import net.crimsonwoods.easydatabinding.models.toBoolean
import net.crimsonwoods.easydatabinding.models.toCharSequence
import net.crimsonwoods.easydatabinding.models.toColorStateList
import net.crimsonwoods.easydatabinding.models.toDrawable
import net.crimsonwoods.easydatabinding.models.toFloat
import net.crimsonwoods.easydatabinding.models.toInt

@BindingAdapter("android:alpha")
fun View.setAlpha(value: Fraction) {
    alpha = value.toFloat(resources)
}

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
    is Background.Res -> {
        setBackgroundResource(value.resId)
    }
    is Background.Drawable -> {
        background = value.drawable.toDrawable(context)
    }
    is Background.None -> {
        background = null
    }
}

@BindingAdapter("android:backgroundTint")
fun View.setBackgroundTint(value: Tint) = when (value) {
    is Tint.Res -> {
        backgroundTintList = ContextCompat.getColorStateList(context, value.resId)
    }
    is Tint.ColorStateList -> {
        backgroundTintList = value.rawValue
    }
    is Tint.None -> {
        backgroundTintList = null
    }
}

@BindingAdapter("android:contentDescription")
fun View.setContentDescription(value: Text) {
    contentDescription = value.toCharSequence(resources)
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

@BindingAdapter("android:focusable")
fun View.setFocusable(value: Bool) {
    isFocusable = value.toBoolean(resources)
}

@BindingAdapter("android:enabled")
fun View.setEnabled(value: Bool) {
    isEnabled = value.toBoolean(resources)
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

internal val View.toPx: Dimension.() -> Float
    get() {
        return { toPx(resources) }
    }

internal val View.toColorStateList: Tint.() -> ColorStateList?
    get() {
        return { toColorStateList(context) }
    }

internal val View.toDrawable: Drawable.() -> android.graphics.drawable.Drawable?
    get() {
        return { toDrawable(context) }
    }
