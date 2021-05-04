package net.crimsonwoods.easydatabinding.shadows

import android.widget.TextView
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements

@Implements(TextView::class)
open class ShadowTextView : org.robolectric.shadows.ShadowTextView() {
    private var isElegantTextHeight = false

    @Implementation
    protected fun setElegantTextHeight(value: Boolean) {
        isElegantTextHeight = value
    }

    @Implementation
    protected fun isElegantTextHeight(): Boolean {
        return isElegantTextHeight
    }
}
