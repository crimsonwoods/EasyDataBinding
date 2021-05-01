package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import net.crimsonwoods.easydatabinding.testing.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimationTest {
    private lateinit var context: Context

    @BeforeTest
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testToAnimation_Res() {
        val target = Animation.of(R.anim.test_animation_translate)
        assertTrue(target is Animation.Res)
        assertNotNull(target.toAnimation(context))
    }

    @Test
    fun testToAnimation_Value() {
        val target = Animation.of(AnimationUtils.makeInAnimation(context, true))
        assertTrue(target is Animation.Value)
        assertNotNull(target.toAnimation(context))
    }

    @Test
    fun testToAnimation_None() {
        val target = Animation.none()
        assertTrue(target is Animation.None)
        assertNull(target.toAnimation(context))
    }
}
