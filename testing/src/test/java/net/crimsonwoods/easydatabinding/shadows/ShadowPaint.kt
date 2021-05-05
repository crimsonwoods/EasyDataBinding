package net.crimsonwoods.easydatabinding.shadows

import android.graphics.Paint
import org.robolectric.annotation.Implements
import org.robolectric.shadows.ShadowPaint

@Implements(value = Paint::class, looseSignatures = true)
open class ShadowPaint : ShadowPaint()
