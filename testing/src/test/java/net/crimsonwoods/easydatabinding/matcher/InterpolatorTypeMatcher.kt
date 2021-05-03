package net.crimsonwoods.easydatabinding.matcher

import android.view.View
import android.view.animation.Interpolator
import androidx.test.espresso.matcher.BoundedMatcher
import kotlin.reflect.KClass
import org.hamcrest.Description

class InterpolatorTypeMatcher<T : View, U : Interpolator>(
    viewClass: KClass<T>,
    private val interpolatorClass: KClass<U>,
    private val interpolatorProvider: T.() -> Interpolator?
) : BoundedMatcher<View, T>(viewClass.java) {
    override fun describeTo(description: Description) {
        description.appendText("with interpolator instance of ${interpolatorClass.simpleName}")
    }

    override fun matchesSafely(item: T): Boolean {
        return item.interpolatorProvider()?.javaClass === interpolatorClass.java
    }
}
