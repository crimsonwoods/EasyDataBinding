package net.crimsonwoods.easydatabinding.models

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.Test
import kotlin.test.assertEquals
import net.crimsonwoods.easydatabinding.testing.R
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextTest {
    @Test
    fun plus() {
        data class Input(
            val t1: Text,
            val t2: Text,
            val expected: CharSequence,
            val description: String,
        )

        val inputs = listOf(
            Input(
                t1 = Text.of("abc"),
                t2 = Text.of("def"),
                expected = "abcdef",
                description = "Concat string",
            ),
            Input(
                t1 = Text.of(R.string.test_text),
                t2 = Text.of("!"),
                expected = "Testing!",
                description = "Concat resource",
            ),
            Input(
                t1 = Text.of(R.string.test_text_with_args, 0, 1),
                t2 = Text.of("!"),
                expected = "0/1!",
                description = "Concat resource with args",
            ),
        )
        val resources = ApplicationProvider.getApplicationContext<Context>().resources
        inputs.forEach { input ->
            assertEquals(
                input.expected,
                (input.t1 + input.t2).toCharSequence(resources).toString(),
                input.description,
            )
        }
    }
}
