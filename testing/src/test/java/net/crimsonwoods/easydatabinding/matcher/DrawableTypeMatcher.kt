package net.crimsonwoods.easydatabinding.matcher

import android.graphics.drawable.Drawable
import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import kotlin.reflect.KClass
import org.hamcrest.Description

class DrawableTypeMatcher<T : View, U : Drawable>(
    viewClass: KClass<T>,
    private val drawableClass: KClass<U>,
    private val drawableProvider: T.() -> Drawable?,
) : BoundedMatcher<View, T>(viewClass.java) {
    override fun describeTo(description: Description) {
        description.appendText("with drawable instance of ${drawableClass.simpleName}")
    }

    override fun matchesSafely(item: T): Boolean {
        val target = item.drawableProvider()
        return if (target == null) {
            false
        } else {
            target.javaClass === drawableClass.java
        }
    }
}
