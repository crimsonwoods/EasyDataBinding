package net.crimsonwoods.easydatabinding.models

import androidx.annotation.DrawableRes

sealed class Image {
    data class Res(
        @DrawableRes
        val resId: Int
    ) : Image()

    data class Drawable(
        val rawValue: android.graphics.drawable.Drawable?
    ) : Image()

    data class Bitmap(
        val rawValue: android.graphics.Bitmap?
    ) : Image()

    data class Uri(
        val rawValue: android.net.Uri?
    ) : Image()

    object None : Image()

    companion object {
        @JvmStatic
        fun of(@DrawableRes resId: Int): Image = Res(resId)

        @JvmStatic
        fun of(drawable: android.graphics.drawable.Drawable?): Image = Drawable(drawable)

        @JvmStatic
        fun of(bitmap: android.graphics.Bitmap?): Image = Bitmap(bitmap)

        @JvmStatic
        fun of(uri: android.net.Uri?): Image = Uri(uri)

        @JvmStatic
        fun none(): Image = None
    }
}
