package net.crimsonwoods.easydatabinding.models

import androidx.annotation.StringRes
import java.util.Locale

sealed class Text {
    /**
     * Text data loaded from resource with arguments.
     */
    data class Res(
        @StringRes
        val resId: Int,
        val args: List<Any?>,
    ) : Text() {
        constructor(@StringRes resId: Int, vararg args: Any?) : this(
            resId,
            args.toList()
        )

        constructor(@StringRes resId: Int) : this(resId, emptyList())
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
        val fallbackResId: Int = 0,
    ) : Text()

    companion object {
        @JvmStatic
        fun of(
            @StringRes resId: Int
        ): Text = Res(resId = resId)

        @JvmStatic
        fun of(
            @StringRes resId: Int, vararg args: Any?
        ): Text = Res(resId = resId, args = args)

        @JvmStatic
        fun of(
            value: kotlin.CharSequence
        ): Text = CharSequence(rawValue = value)

        @JvmStatic
        fun of(
            vararg values: Pair<Locale, kotlin.CharSequence>
        ): Text = Multilingual(values = values.toMap())

        @JvmStatic
        fun of(
            values: Map<Locale, kotlin.CharSequence>,
            fallback: kotlin.CharSequence
        ): Text = Multilingual(values = values, fallback = fallback)

        @JvmStatic
        fun of(
            values: Map<Locale, kotlin.CharSequence>,
            @StringRes fallbackResId: Int
        ): Text = Multilingual(values = values, fallback = null, fallbackResId = fallbackResId)

        @JvmStatic
        fun empty(): Text = CharSequence(rawValue = "")
    }
}
