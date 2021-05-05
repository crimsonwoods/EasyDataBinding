package net.crimsonwoods.easydatabinding.shadows

import android.text.TextPaint
import org.robolectric.annotation.Implements

@Implements(TextPaint::class)
open class ShadowTextPaint : ShadowPaint() {
}
