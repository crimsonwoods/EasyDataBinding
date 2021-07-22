package net.crimsonwoods.easydatabinding.models

import android.content.res.Resources
import androidx.annotation.FractionRes

sealed class Fraction {
    data class Res(
        @FractionRes val resId: Int,
        val base: Int,
        val parentBase: Int
    ) : Fraction()

    data class Float(
        val rawValue: kotlin.Float
    ) : Fraction()

    companion object {
        @JvmField
        val ZERO: Fraction = Float(0f)

        @JvmStatic
        fun of(value: kotlin.Float): Fraction = Float(rawValue = value)

        @JvmStatic
        fun of(@FractionRes resId: Int, base: Int = 1, parentBase: Int = 1): Fraction = Res(
            resId = resId,
            base = base,
            parentBase = parentBase
        )
    }
}

fun Fraction.toFloat(resources: Resources): kotlin.Float {
    return when (this) {
        is Fraction.Res -> {
            resources.getFraction(resId, base, parentBase)
        }
        is Fraction.Float -> {
            rawValue
        }
    }
}
