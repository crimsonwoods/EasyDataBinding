package net.crimsonwoods.easydatabinding.binding

import android.widget.ViewAnimator
import androidx.databinding.BindingAdapter
import net.crimsonwoods.easydatabinding.models.Animation

@BindingAdapter("android:inAnimation")
fun ViewAnimator.setInAnimation(value: Animation) {
    inAnimation = value.toAnimation()
}

@BindingAdapter("android:outAnimation")
fun ViewAnimator.setOutAnimation(value: Animation) {
    outAnimation = value.toAnimation()
}
