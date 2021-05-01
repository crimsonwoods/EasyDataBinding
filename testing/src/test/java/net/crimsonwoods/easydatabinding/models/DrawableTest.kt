package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.NinePatchDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import net.crimsonwoods.easydatabinding.testing.R
import org.junit.runner.RunWith

@ExperimentalContracts
@RunWith(AndroidJUnit4::class)
class DrawableTest {
    private lateinit var context: Context

    @BeforeTest
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testToDrawable_Res_Bitmap() {
        val target = Drawable.of(R.drawable.test_drawable_bitmap)
        assertTrue(target is Drawable.Res)
        assertTypeOf<BitmapDrawable>(target.toDrawable(context))
    }

    @Test
    fun testToDrawable_Res_NinePatch() {
        val target = Drawable.of(R.drawable.test_drawable_9patch)
        assertTrue(target is Drawable.Res)
        assertTypeOf<NinePatchDrawable>(target.toDrawable(context))
    }

    @Test
    fun testToDrawable_Res_Shape() {
        val target = Drawable.of(R.drawable.test_drawable_oval)
        assertTrue(target is Drawable.Res)
        assertTypeOf<GradientDrawable>(target.toDrawable(context))
    }

    @Test
    fun testToDrawable_Res_StateList() {
        val target = Drawable.of(R.drawable.test_drawable_statelist)
        assertTrue(target is Drawable.Res)
        assertTypeOf<StateListDrawable>(target.toDrawable(context))
    }

    @Test
    fun testToDrawable_Value() {
        val target = Drawable.of(ShapeDrawable(RectShape()))
        assertTrue(target is Drawable.Value)
        assertTypeOf<ShapeDrawable>(target.toDrawable(context))
    }

    @Test
    fun testToDrawable_Animation() {
        val target = Drawable.of(AnimationDrawable())
        assertTrue(target is Drawable.Animation)
        assertTypeOf<AnimationDrawable>(target.toDrawable(context))
    }

    @Test
    fun testToDrawable_Bitmap() {
        val target = Drawable.of(
            BitmapDrawable(
                context.resources,
                Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
            )
        )
        assertTrue(target is Drawable.Bitmap)
        assertTypeOf<BitmapDrawable>(target.toDrawable(context))
    }

    @Test
    fun testToDrawable_Color() {
        val target = Drawable.of(ColorDrawable(Color.RED))
        assertTrue(target is Drawable.Color)
        val actual = target.toDrawable(context)
        assertTypeOf<ColorDrawable>(actual)
        assertEquals(Color.RED, actual.color)
    }

    @Test
    fun testToDrawable_NinePatch() {
        val target = Drawable.of(
            NinePatchDrawable(
                context.resources,
                Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888),
                byteArrayOf(),
                Rect(),
                ""
            )
        )
        assertTrue(target is Drawable.NinePatch)
        assertTypeOf<NinePatchDrawable>(target.toDrawable(context))
    }

    // TODO Adds more tests...

    @Test
    fun testToDrawable_None() {
        val target = Drawable.none()
        assertTrue(target is Drawable.None)
        assertNull(target.toDrawable(context))
    }

    private inline fun <reified T> assertTypeOf(actual: Any?, message: String? = null) {
        contract {
            returns() implies (actual != null && actual is T)
        }

        assertNotNull(
            actual,
            message ?: "Expected value not to be null."
        )
        assertEquals(
            T::class.java as Class<*>,
            actual.javaClass,
            message ?: "Expected type of value to be ${T::class.java.simpleName}"
        )
    }
}
