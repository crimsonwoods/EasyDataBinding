package net.crimsonwoods.easydatabinding.binding

import android.os.Build
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.core.widget.TintableCompoundDrawablesView
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Color
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Float
import net.crimsonwoods.easydatabinding.models.Integer
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextAppearance
import net.crimsonwoods.easydatabinding.models.Tint
import net.crimsonwoods.easydatabinding.models.toCharSequence

@BindingAdapter("android:cursorVisible")
fun TextView.setCursorVisible(value: Bool) {
    isCursorVisible = value.toBoolean()
}

@BindingAdapter("android:drawablePadding")
fun TextView.setDrawablePadding(value: Dimension) {
    compoundDrawablePadding = value.toPx().roundToInt()
}

@BindingAdapter("android:drawableTint")
fun TextView.setDrawableTintList(value: Tint) {
    if (this !is TintableCompoundDrawablesView && Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:drawableTint\" attribute is being supported after Android N or later."
        )
    }
    TextViewCompat.setCompoundDrawableTintList(this, value.toColorStateList())
}

@BindingAdapter("android:elegantTextHeight")
fun TextView.setElegantTextHeight(value: Bool) {
    isElegantTextHeight = value.toBoolean()
}

@BindingAdapter("android:ems")
fun TextView.setEms(value: Integer) {
    setEms(value.toInt())
}

@RequiresApi(value = Build.VERSION_CODES.P)
@BindingAdapter("android:fallbackLineSpacing")
fun TextView.setFallbackLineSpacing(value: Bool) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        isFallbackLineSpacing = value.toBoolean()
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:fallbackLineSpacing\" attribute is being supported after Android P or later."
        )
    }
}

@BindingAdapter("android:firstBaselineToTopHeight")
fun TextView.setFirstBaselineToTopHeight(value: Dimension) {
    TextViewCompat.setFirstBaselineToTopHeight(this, value.toPx().roundToInt())
}

@BindingAdapter("android:fontFeatureSettings")
fun TextView.setFontFeatureSettings(value: Text) {
    fontFeatureSettings = value.toCharSequence().toString()
}

@RequiresApi(value = Build.VERSION_CODES.O)
@BindingAdapter("android:fontVariationSettings")
fun TextView.setFontVariationSettings(value: Text): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        setFontVariationSettings(value.toCharSequence().toString())
    } else {
        throw UnsupportedOperationException(
            "BindingAdapter for \"android:fontVariationSettings\" attribute is being supported after Android O or later."
        )
    }
}

@BindingAdapter("android:freezesText")
fun TextView.setFreezesText(value: Bool) {
    freezesText = value.toBoolean()
}

@BindingAdapter("android:height")
fun TextView.setHeight(value: Dimension) {
    height = value.toPx().roundToInt()
}

@BindingAdapter("android:includeFontPadding")
fun TextView.setIncludeFontPadding(value: Bool) {
    includeFontPadding = value.toBoolean()
}

@BindingAdapter("android:lastBaselineToBottomHeight")
fun TextView.setLastBaselineToBottomHeight(value: Dimension) {
    TextViewCompat.setLastBaselineToBottomHeight(this, value.toPx().roundToInt())
}

@BindingAdapter("android:lineHeight")
fun TextView.setLineHeight(value: Dimension) {
    TextViewCompat.setLineHeight(this, value.toPx().roundToInt())
}

@BindingAdapter("android:lineSpacingExtra")
fun TextView.setLineSpacingExtra(value: Dimension) {
    setLineSpacing(value.toPx(), lineSpacingMultiplier)
}

@BindingAdapter("android:lineSpacingMultiplier")
fun TextView.setLineSpacingMultiplier(value: Float) {
    setLineSpacing(lineSpacingExtra, value.toFloat())
}

@BindingAdapter("android:lines")
fun TextView.setLines(value: Integer) {
    setLines(value.toInt())
}

@BindingAdapter("android:linksClickable")
fun TextView.setLinksClickable(value: Bool) {
    linksClickable = value.toBoolean()
}

@BindingAdapter("android:maxEms")
fun TextView.setMaxEms(value: Integer) {
    maxEms = value.toInt()
}

@BindingAdapter("android:maxHeight")
fun TextView.setMaxHeight(value: Dimension) {
    maxHeight = value.toPx().roundToInt()
}

@BindingAdapter("android:maxLines")
fun TextView.setMaxLines(value: Integer) {
    maxLines = value.toInt()
}

@BindingAdapter("android:maxWidth")
fun TextView.setMaxWidth(value: Dimension) {
    maxWidth = value.toPx().roundToInt()
}

@BindingAdapter("android:minEms")
fun TextView.setMinEms(value: Integer) {
    minEms = value.toInt()
}

@BindingAdapter("android:minHeight")
fun TextView.setMinHeight(value: Dimension) {
    minHeight = value.toPx().roundToInt()
}

@BindingAdapter("android:minLines")
fun TextView.setMinLines(value: Integer) {
    minLines = value.toInt()
}

@BindingAdapter("android:minWidth")
fun TextView.setMinWidth(value: Dimension) {
    minWidth = value.toPx().roundToInt()
}

@BindingAdapter("android:scrollHorizontally")
fun TextView.setScrollHorizontally(value: Bool) {
    setHorizontallyScrolling(value.toBoolean())
}

@BindingAdapter("android:selectAllOnFocus")
fun TextView.setSelectAllOnFocus(value: Bool) {
    setSelectAllOnFocus(value.toBoolean())
}

@BindingAdapter("android:text")
fun TextView.setText(text: Text) {
    this.text = text.toCharSequence(resources)
}

@BindingAdapter("android:textAllCaps")
fun TextView.setTextAllCaps(value: Bool) {
    isAllCaps = value.toBoolean()
}

@BindingAdapter("android:textAppearance")
fun TextView.setTextAppearance(value: TextAppearance) {
    TextViewCompat.setTextAppearance(this, value.resId)
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

@BindingAdapter("android:hint")
fun TextView.setHint(value: Text) {
    hint = value.toCharSequence()
}

@BindingAdapter("android:textColorHint")
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
fun TextView.setTextSize(size: Dimension) = when (size) {
    is Dimension.Px -> {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, size.rawValue)
    }
    is Dimension.Sp -> {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size.rawValue)
    }
    is Dimension.Dp -> {
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, size.rawValue)
    }
    is Dimension.Res -> {
        setTextSize(TypedValue.COMPLEX_UNIT_FRACTION, context.resources.getDimension(size.resId))
    }
}

@BindingAdapter("android:width")
fun TextView.setWidth(value: Dimension) {
    width = value.toPx().roundToInt()
}
