package net.crimsonwoods.easydatabinding.shadows

import android.graphics.Paint
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.shadows.ShadowPaint

@Implements(value = Paint::class, looseSignatures = true)
open class ShadowPaint : ShadowPaint() {
    private var fontVariationSettings: String? = null

    @Implementation
    protected fun getFontVariationSettings(): String? {
        return fontVariationSettings
    }

    @Implementation
    protected fun setFontVariationSettings(value: String?): Boolean {
        fontVariationSettings = if (value.isNullOrEmpty()) {
            null
        } else {
            value.orEmpty()
        }
        return true
    }
}
