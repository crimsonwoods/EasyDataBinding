package net.crimsonwoods.easydatabinding.models

import android.content.res.Resources
import androidx.annotation.IntegerRes

sealed class Integer {
    data class Res(
        @IntegerRes val resId: Int
    ) : Integer()

    data class Value(
        val rawValue: Int
    ) : Integer()

    companion object {
        @JvmStatic
        fun wrap(value: Int): Integer = Value(value)

        @JvmStatic
        fun of(@IntegerRes resId: Int): Integer = Res(resId)
    }
}

fun Integer.toInt(resources: Resources): Int {
    return when (this) {
        is Integer.Res -> {
            resources.getInteger(resId)
        }
        is Integer.Value -> {
            rawValue
        }
    }
}
