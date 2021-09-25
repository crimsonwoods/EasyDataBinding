package net.crimsonwoods.easydatabinding.models

import android.content.res.Resources
import android.os.Build
import android.util.TypedValue
import androidx.annotation.DimenRes

sealed class Float {
    data class Res(
        @DimenRes
        val resId: Int,
    ) : Float()

    data class Value(
        val rawValue: kotlin.Float,
    ) : Float()

    companion object {
        val MIN_VALUE: Float = Value(kotlin.Float.MIN_VALUE)
        val MAX_VALUE: Float = Value(kotlin.Float.MAX_VALUE)
        val POSITIVE_INFINITY: Float = Value(kotlin.Float.POSITIVE_INFINITY)
        val NEGATIVE_INFINITY: Float = Value(kotlin.Float.NEGATIVE_INFINITY)
        val NaN: Float = Value(kotlin.Float.NaN)

        @JvmStatic
        fun of(value: kotlin.Float): Float = Value(value)

        @JvmStatic
        fun of(@DimenRes resId: Int): Float = Res(resId)
    }
}

@Throws(Resources.NotFoundException::class)
fun Float.toFloat(resources: Resources): kotlin.Float = when (this) {
    is Float.Res -> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            resources.getFloat(resId)
        } else {
            val value = TypedValue()
            resources.getValue(resId, value, true)
            if (value.type == TypedValue.TYPE_FLOAT) {
                value.float
            } else {
                throw Resources.NotFoundException(
                    "Resource ID #0x${resId.toHex()} type #0x${value.type.toHex()} is not valid"
                )
            }
        }
    }
    is Float.Value -> {
        rawValue
    }
}

private fun Int.toHex(): String = "0x${toString(16)}"
