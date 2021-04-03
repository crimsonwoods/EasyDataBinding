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
class IntegerTest {
    private lateinit var resources: Resources

    @BeforeTest
    fun setUp() {
        resources = ApplicationProvider.getApplicationContext<Context>().resources
    }

    @Test
    fun testToInt_Res() {
        assertEquals(0, Integer.of(R.integer.test_0).toInt(resources))
        assertEquals(1, Integer.of(R.integer.test_1).toInt(resources))
        assertEquals(-1, Integer.of(R.integer.test_negative_1).toInt(resources))
    }

    @Test
    fun testToInt_Value() {
        assertEquals(0, Integer.wrap(0).toInt(resources))
        assertEquals(1, Integer.wrap(1).toInt(resources))
        assertEquals(-1, Integer.wrap(-1).toInt(resources))
        assertEquals(Int.MAX_VALUE, Integer.wrap(Int.MAX_VALUE).toInt(resources))
        assertEquals(Int.MIN_VALUE, Integer.wrap(Int.MIN_VALUE).toInt(resources))
    }
}
