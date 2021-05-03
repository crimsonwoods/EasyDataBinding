package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.annotation.InterpolatorRes

sealed class Interpolator {
    data class Res(
        @InterpolatorRes val resId: Int
    ) : Interpolator()

    data class Value(
        val rawValue: android.view.animation.Interpolator
    ) : Interpolator()

    object None : Interpolator()

    companion object {
        @JvmStatic
        fun of(@InterpolatorRes resId: Int): Interpolator = Res(resId)

        @JvmStatic
        fun of(value: android.view.animation.Interpolator): Interpolator = Value(value)
    }
}

fun Interpolator.toInterpolator(context: Context): android.view.animation.Interpolator? {
    return when (this) {
        is Interpolator.Res -> {
            AnimationUtils.loadInterpolator(context, resId)
        }
        is Interpolator.Value -> {
            rawValue
        }
        is Interpolator.None -> {
            null
        }
    }
}
