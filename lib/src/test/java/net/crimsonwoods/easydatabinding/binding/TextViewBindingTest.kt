package net.crimsonwoods.easydatabinding.binding

import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import java.util.Locale
import kotlin.test.Test
import net.crimsonwoods.easydatabinding.R
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.models.Text
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
class TextViewBindingTest {
    @Test
    fun testBinding_setText_for_Res() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(android.R.string.ok))
        }
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("OK")))
    }

    @Test
    fun testBinding_setText_for_ResWithArgs() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(R.string.character_counter_pattern, 1, 2))
        }
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("1/2")))
    }

    @Test
    fun testBinding_setText_for_CharSequence() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of("test"))
        }
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("test")))
    }

    @Test
    @Config(qualifiers = "ja-rJP")
    fun testBinding_setText_for_Multilingual_jaJP() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(Locale.JAPAN to "テスト", Locale.US to "Test"))
        }
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("テスト")))
    }

    @Test
    @Config(qualifiers = "en-rUS")
    fun testBinding_setText_for_Multilingual_jaJP_Fallback() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(mapOf(Locale.JAPAN to "テスト"), "test"))
        }
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("test")))
    }

    @Test
    @Config(qualifiers = "en-rUS")
    fun testBinding_setText_for_Multilingual_jaJP_FallbackRes() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(mapOf(Locale.JAPAN to "テスト"), android.R.string.ok))
        }
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("OK")))
    }

    @Test
    @Config(qualifiers = "en-rUS")
    fun testBinding_setText_for_Multilingual_enUS() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(Locale.JAPAN to "テスト", Locale.US to "Test"))
        }
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("Test")))
    }
}
