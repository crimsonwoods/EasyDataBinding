package net.crimsonwoods.easydatabinding.models

import android.content.Context
import android.content.res.Resources
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import net.crimsonwoods.easydatabinding.testing.R
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BoolTest {
    private lateinit var resources: Resources

    @BeforeTest
    fun setUp() {
        resources = ApplicationProvider.getApplicationContext<Context>().resources
    }

    @Test
    fun testToBoolean_Res() {
        assertTrue(Bool.of(R.bool.test_positive).toBoolean(resources))
        assertFalse(Bool.of(R.bool.test_negative).toBoolean(resources))
    }

    @Test
    fun testToBoolean_Value() {
        assertTrue(Bool.TRUE.toBoolean(resources))
        assertFalse(Bool.FALSE.toBoolean(resources))
    }
}
