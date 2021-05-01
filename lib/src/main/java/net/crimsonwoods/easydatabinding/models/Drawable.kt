package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ColorStateListDrawable
import android.graphics.drawable.NinePatchDrawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

sealed class Drawable {
    data class Res(
        @DrawableRes
        val resId: Int
    ) : Drawable()

    data class Value(
        val rawValue: android.graphics.drawable.Drawable
    ) : Drawable()

    data class Animation(
        val animation: AnimationDrawable
    ) : Drawable()

    data class Bitmap(
        val bitmap: BitmapDrawable
    ) : Drawable()

    data class Color(
        val color: ColorDrawable
    ) : Drawable()

    data class NinePatch(
        val ninePatch: NinePatchDrawable
    ) : Drawable()

    data class Vector(
        val vector: VectorDrawable
    ) : Drawable()

    data class VectorCompat(
        val vector: VectorDrawableCompat
    ) : Drawable()

    data class RawBitmap(
        val bitmap: android.graphics.Bitmap
    ) : Drawable()

    data class ColorRes(
        @androidx.annotation.ColorRes
        val resId: Int
    ) : Drawable()

    data class ColorInt(
        @androidx.annotation.ColorInt
        val color: Int
    ) : Drawable()

    @RequiresApi(value = Build.VERSION_CODES.O)
    data class ColorLong(
        @androidx.annotation.ColorLong
        val color: Long
    ) : Drawable()

    @RequiresApi(value = Build.VERSION_CODES.Q)
    data class ColorStateList(
        val colors: ColorStateListDrawable
    ) : Drawable()

    @RequiresApi(value = Build.VERSION_CODES.Q)
    data class RawColorStateList(
        val colors: android.content.res.ColorStateList
    ) : Drawable()

    data class RawNinePatch(
        val ninePatch: android.graphics.NinePatch
    ) : Drawable()

    object None : Drawable()

    companion object {
        @JvmStatic
        fun of(@DrawableRes resId: Int): Drawable = Res(resId)

        @JvmStatic
        fun of(drawable: android.graphics.drawable.Drawable?): Drawable = when (drawable) {
            null -> None
            is AnimationDrawable -> Animation(drawable)
            is BitmapDrawable -> Bitmap(drawable)
            is ColorDrawable -> Color(drawable)
            is NinePatchDrawable -> NinePatch(drawable)
            is VectorDrawable -> Vector(drawable)
            is VectorDrawableCompat -> VectorCompat(drawable)
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && drawable is ColorStateListDrawable) {
                    ColorStateList(drawable)
                } else {
                    Value(drawable)
                }
            }
        }

        @JvmStatic
        fun of(drawable: AnimationDrawable): Drawable = Animation(drawable)

        @JvmStatic
        fun of(drawable: BitmapDrawable): Drawable = Bitmap(drawable)

        @JvmStatic
        fun of(drawable: ColorDrawable): Drawable = Color(drawable)

        @JvmStatic
        fun of(drawable: NinePatchDrawable): Drawable = NinePatch(drawable)

        @JvmStatic
        fun of(drawable: VectorDrawable): Drawable = Vector(drawable)

        @JvmStatic
        fun of(drawable: VectorDrawableCompat): Drawable = VectorCompat(drawable)

        @JvmStatic
        fun of(bitmap: android.graphics.Bitmap): Drawable = RawBitmap(bitmap)

        @JvmStatic
        fun ofColorRes(@androidx.annotation.ColorRes colorRes: Int): Drawable = ColorRes(colorRes)

        @JvmStatic
        fun ofColor(@androidx.annotation.ColorInt color: Int): Drawable = ColorInt(color)

        @RequiresApi(value = Build.VERSION_CODES.O)
        @JvmStatic
        fun ofColor(@androidx.annotation.ColorLong color: Long): Drawable = ColorLong(color)

        @RequiresApi(value = Build.VERSION_CODES.Q)
        @JvmStatic
        fun of(colors: android.content.res.ColorStateList): Drawable = RawColorStateList(colors)

        @RequiresApi(value = Build.VERSION_CODES.Q)
        @JvmStatic
        fun of(colors: ColorStateListDrawable): Drawable = ColorStateList(colors)

        @JvmStatic
        fun of(ninePatch: android.graphics.NinePatch): Drawable = RawNinePatch(ninePatch)

        @JvmStatic
        fun none(): Drawable = None
    }
}

fun Drawable.toDrawable(context: Context): android.graphics.drawable.Drawable? = when (this) {
    is Drawable.Res -> {
        ContextCompat.getDrawable(context, resId)
    }
    is Drawable.Value -> {
        rawValue
    }
    is Drawable.Animation -> {
        animation
    }
    is Drawable.Bitmap -> {
        bitmap
    }
    is Drawable.Color -> {
        color
    }
    is Drawable.NinePatch -> {
        ninePatch
    }
    is Drawable.Vector -> {
        vector
    }
    is Drawable.VectorCompat -> {
        vector
    }
    is Drawable.RawBitmap -> {
        BitmapDrawable(context.resources, bitmap)
    }
    is Drawable.ColorRes -> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val tint = try {
                ContextCompat.getColorStateList(context, resId)
            } catch (_: Resources.NotFoundException) {
                null
            }
            if (tint != null) {
                ColorStateListDrawable(tint)
            } else {
                ColorDrawable(ContextCompat.getColor(context, resId))
            }
        } else {
            ColorDrawable(ContextCompat.getColor(context, resId))
        }
    }
    is Drawable.ColorInt -> {
        ColorDrawable(color)
    }
    is Drawable.ColorLong -> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ColorDrawable(Color.valueOf(color).toArgb())
        } else {
            throw UnsupportedOperationException(
                "Converting to \"Drawable\" is begin supported from Android O or later."
            )
        }
    }
    is Drawable.ColorStateList -> {
        colors
    }
    is Drawable.RawColorStateList -> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ColorStateListDrawable(colors)
        } else {
            throw UnsupportedOperationException(
                "Converting to \"Drawable\" is being supported from Android Q or later."
            )
        }
    }
    is Drawable.RawNinePatch -> {
        NinePatchDrawable(context.resources, ninePatch)
    }
    is Drawable.None -> null
}
