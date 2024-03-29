package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.content.res.Resources
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import net.crimsonwoods.easydatabinding.testing.R
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FractionTest {
    private lateinit var resources: Resources

    @BeforeTest
    fun setUp() {
        resources = ApplicationProvider.getApplicationContext<Context>().resources
    }

    @Test
    fun testToFloat_Res() {
        assertEquals(0.5f, Fraction.of(R.fraction.test_fraction).toFloat(resources))
        assertEquals(0.5f, Fraction.of(R.fraction.test_fraction_p).toFloat(resources))
        assertEquals(
            1.0f,
            Fraction.of(R.fraction.test_fraction, base = 2, parentBase = 1).toFloat(resources)
        )
        assertEquals(
            1.0f,
            Fraction.of(R.fraction.test_fraction_p, base = 1, parentBase = 2).toFloat(resources)
        )
    }

    @Test
    fun testToFloat_Value() {
        assertEquals(0.0f, Fraction.ZERO.toFloat(resources))
        assertEquals(kotlin.Float.MIN_VALUE, Fraction.of(kotlin.Float.MIN_VALUE).toFloat(resources))
        assertEquals(kotlin.Float.MAX_VALUE, Fraction.of(kotlin.Float.MAX_VALUE).toFloat(resources))
        assertEquals(kotlin.Float.NaN, Fraction.of(kotlin.Float.NaN).toFloat(resources))
        assertEquals(
            kotlin.Float.NEGATIVE_INFINITY,
            Fraction.of(kotlin.Float.NEGATIVE_INFINITY).toFloat(resources)
        )
        assertEquals(
            kotlin.Float.POSITIVE_INFINITY,
            Fraction.of(kotlin.Float.POSITIVE_INFINITY).toFloat(resources)
        )
    }
}
