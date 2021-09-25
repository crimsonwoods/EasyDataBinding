package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.DimenRes

sealed class Dimension {
    data class Px(
        @androidx.annotation.Px
        val rawValue: kotlin.Float
    ) : Dimension()

    data class Sp(
        val rawValue: kotlin.Float
    ) : Dimension()

    data class Dp(
        val rawValue: kotlin.Float
    ) : Dimension()

    data class Res(
        @DimenRes
        val resId: Int
    ) : Dimension()

    @androidx.annotation.Px
    fun toPx(resources: Resources): kotlin.Float = when (this) {
        is Px -> rawValue
        is Sp -> TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            rawValue,
            resources.displayMetrics
        )
        is Dp -> TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            rawValue,
            resources.displayMetrics
        )
        is Res -> resources.getDimension(resId)
    }

    @androidx.annotation.Px
    fun toPx(context: Context): kotlin.Float = toPx(context.resources)

    companion object {
        @JvmStatic
        fun px(value: kotlin.Float): Dimension = Px(value)

        @JvmStatic
        fun sp(value: kotlin.Float): Dimension = Sp(value)

        @JvmStatic
        fun dp(value: kotlin.Float): Dimension = Dp(value)

        @JvmStatic
        fun of(@DimenRes resId: Int): Dimension = Res(resId)
    }
}
