package net.crimsonwoods.easydatabinding.shadows

import android.graphics.Typeface
import android.graphics.fonts.FontVariationAxis
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.shadows.ShadowTypeface

@Implements(value = Typeface::class, looseSignatures = true)
open class ShadowTypeface : ShadowTypeface() {
    @Implementation
    protected fun isSupportedAxes(axis: Int): Boolean {
        return true
    }

    companion object {
        @Implementation
        @JvmStatic
        protected fun createFromTypefaceWithVariation(
            family: Typeface?,
            axes: List<FontVariationAxis>
        ): Typeface {
            return Typeface.defaultFromStyle((family ?: Typeface.DEFAULT).style)
        }
    }
}
