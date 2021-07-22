package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.content.res.Resources
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import net.crimsonwoods.easydatabinding.testing.R
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
class FloatTest {
    private lateinit var resources: Resources

    @BeforeTest
    fun setUp() {
        resources = ApplicationProvider.getApplicationContext<Context>().resources
    }

    @Config(sdk = [Build.VERSION_CODES.P, Build.VERSION_CODES.Q])
    @Test
    fun testToFloat_Res() {
        assertEquals(0.0f, Float.of(R.dimen.zero).toFloat(resources))
        assertEquals(0.5f, Float.of(R.dimen.zero_point_five).toFloat(resources))
        assertEquals(1.0f, Float.of(R.dimen.one_point_zero).toFloat(resources))
        assertEquals(0.5f, Float.of(R.dimen.half).toFloat(resources))
        assertEquals(1.0f, Float.of(R.dimen.full).toFloat(resources))
    }

    @Test
    fun testToFloat_Value() {
        assertEquals(0.0f, Float.of(0.0f).toFloat(resources))
        assertEquals(0.5f, Float.of(0.5f).toFloat(resources))
        assertEquals(1.0f, Float.of(1.0f).toFloat(resources))
    }

    @Config(sdk = [Build.VERSION_CODES.P, Build.VERSION_CODES.Q])
    @Test
    fun testFloat_Res_InvalidArgument() {
        assertFailsWith<Resources.NotFoundException> { Float.of(R.dimen.one_dp).toFloat(resources) }
        assertFailsWith<Resources.NotFoundException> { Float.of(R.dimen.one_sp).toFloat(resources) }
        assertFailsWith<Resources.NotFoundException> { Float.of(R.dimen.one_px).toFloat(resources) }
        assertFailsWith<Resources.NotFoundException> { Float.of(R.dimen.one_pt).toFloat(resources) }
    }
}
