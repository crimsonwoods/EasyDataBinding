package net.crimsonwoods.easydatabinding.models

import androidx.annotation.StringRes
import java.util.*

sealed class Text {
    /**
     * Text data loaded from resource.
     */
    data class Res(
        @StringRes
        val resId: Int,
    ) : Text()

    /**
     * Text data loaded from resource with arguments.
     */
    data class ResWithArgs(
        @StringRes
        val resId: Int,
        val args: List<Any?>,
    ) : Text() {
        constructor(@StringRes resId: Int, vararg args: Any?) : this(
            resId,
            args.toList()
        )
    }

    /**
     * Usual text data
     */
    data class CharSequence(
        val rawValue: kotlin.CharSequence,
    ) : Text(), kotlin.CharSequence by rawValue

    /**
     * Multilingual text data
     */
    data class Multilingual(
        val values: Map<Locale, kotlin.CharSequence>,
        val fallback: kotlin.CharSequence? = null,
        @StringRes
        val fallbackRes: Int = 0,
    ) : Text()
}
