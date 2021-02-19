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
}
