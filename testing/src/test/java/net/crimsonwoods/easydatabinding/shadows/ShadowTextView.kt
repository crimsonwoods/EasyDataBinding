package net.crimsonwoods.easydatabinding.shadows

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.widget.TextView
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements

@Implements(TextView::class)
open class ShadowTextView : org.robolectric.shadows.ShadowTextView() {
    private val textPaint = TextPaint(TextPaint.ANTI_ALIAS_FLAG).apply {
        typeface = Typeface.DEFAULT
        textSize = 15f
    }
    private var isElegantTextHeight = false

    @Implementation
    protected fun setElegantTextHeight(value: Boolean) {
        isElegantTextHeight = value
    }

    @Implementation
    protected fun isElegantTextHeight(): Boolean {
        return isElegantTextHeight
    }

    @Implementation
    protected fun getPaint(): Paint {
        return textPaint
    }

    @Implementation
    protected fun getFontVariationSettings(): String? {
        return textPaint.fontVariationSettings
    }

    @Implementation
    protected fun setFontVariationSettings(value: String?): Boolean {
        return textPaint.setFontVariationSettings(value)
    }
}
