package net.crimsonwoods.easydatabinding.models

import android.content.res.Resources
import android.os.Build
import android.text.SpannableStringBuilder
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
            args.toList(),
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

    private data class Builder(
        val builder: (Resources) -> kotlin.CharSequence,
    ) : Text()

    private data class Concatenated(
        val text1: Text,
        val text2: Text,
    ) : Text()

    infix operator fun plus(other: Text): Text = Concatenated(this, other)

    fun toCharSequence(resources: Resources): kotlin.CharSequence = when (this) {
        is Res -> {
            if (args.isNotEmpty()) {
                resources.getString(resId, *args.toTypedArray())
            } else {
                resources.getString(resId)
            }
        }
        is CharSequence -> {
            rawValue
        }
        is Multilingual -> {
            val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                resources.configuration.locales[0]
            } else {
                @Suppress("DEPRECATION")
                resources.configuration.locale
            }
            values[locale] ?: fallback ?: resources.getString(fallbackResId)
        }
        is Builder -> {
            builder(resources)
        }
        is Concatenated -> {
            SpannableStringBuilder()
                .append(text1.toCharSequence(resources))
                .append(text2.toCharSequence(resources))
        }
    }

    companion object {
        @JvmStatic
        fun of(
            @StringRes resId: Int,
        ): Text = Res(resId = resId)

        @JvmStatic
        fun of(
            @StringRes resId: Int, vararg args: Any?,
        ): Text = Res(resId = resId, args = args)

        @JvmStatic
        fun of(
            value: kotlin.CharSequence,
        ): Text = CharSequence(rawValue = value)

        @JvmStatic
        fun of(
            vararg values: Pair<Locale, kotlin.CharSequence>,
        ): Text = Multilingual(values = values.toMap())

        @JvmStatic
        fun of(
            values: Map<Locale, kotlin.CharSequence>,
            fallback: kotlin.CharSequence,
        ): Text = Multilingual(values = values, fallback = fallback)

        @JvmStatic
        fun of(
            values: Map<Locale, kotlin.CharSequence>,
            @StringRes fallbackResId: Int,
        ): Text = Multilingual(values = values, fallback = null, fallbackResId = fallbackResId)

        @JvmStatic
        fun of(
            builder: (Resources) -> kotlin.CharSequence,
        ): Text = Builder(builder = builder)

        @JvmStatic
        fun empty(): Text = CharSequence(rawValue = "")
    }
}
