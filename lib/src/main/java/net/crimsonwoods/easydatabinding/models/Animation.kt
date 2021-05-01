package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes

sealed class Animation {
    data class Res(
        @AnimRes val resId: Int
    ) : Animation()

    data class Value(
        val rawValue: android.view.animation.Animation
    ) : Animation()

    object None : Animation()

    companion object {
        @JvmStatic
        fun of(@AnimRes resId: Int): Animation = Res(resId)

        @JvmStatic
        fun of(anim: android.view.animation.Animation): Animation = Value(anim)

        @JvmStatic
        fun none(): Animation = None
    }
}

fun Animation.toAnimation(context: Context): android.view.animation.Animation? = when (this) {
    is Animation.Res -> {
        AnimationUtils.loadAnimation(context, resId)
    }
    is Animation.Value -> {
        rawValue
    }
    is Animation.None -> {
        null
    }
}
