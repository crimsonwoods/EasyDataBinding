package net.crimsonwoods.easydatabinding.binding

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import java.util.Locale
import kotlin.test.Test
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Color
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
class TextViewBindingTest {
    @Test
    fun testBinding_setText_for_Res() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(R.string.test_text))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("Testing")))
    }

    @Test
    fun testBinding_setText_for_ResWithArgs() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(R.string.test_text_with_args, 1, 2))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("1/2")))
    }

    @Test
    fun testBinding_setText_for_CharSequence() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of("test"))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("test")))
    }

    @Test
    @Config(qualifiers = "ja-rJP")
    fun testBinding_setText_for_Multilingual_jaJP() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(Locale.JAPAN to "テスト", Locale.US to "Test"))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("テスト")))
    }

    @Test
    @Config(qualifiers = "en-rUS")
    fun testBinding_setText_for_Multilingual_jaJP_Fallback() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(mapOf(Locale.JAPAN to "テスト"), "test"))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("test")))
    }

    @Test
    @Config(qualifiers = "en-rUS")
    fun testBinding_setText_for_Multilingual_jaJP_FallbackRes() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(mapOf(Locale.JAPAN to "テスト"), android.R.string.ok))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("OK")))
    }

    @Test
    @Config(qualifiers = "en-rUS")
    fun testBinding_setText_for_Multilingual_enUS() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of(Locale.JAPAN to "テスト", Locale.US to "Test"))
        }
        onView(withId(android.R.id.text1)).check(matches(withText("Test")))
    }

    @Test
    fun testBinding_setTextAllCaps() {
        launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1)
                    .setTextAllCaps(Bool.TRUE)
            }
        onView(withId(android.R.id.text1)).check(matches(isAllCaps()))
    }

    @Test
    fun testBinding_setTextColor_for_Int() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
            }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextColor(Color.Int(0xff))
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0xff)))
    }

    @Test
    fun testBinding_setTextColor_for_Res() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
            }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextColor(Color.Res(android.R.color.white))
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(android.graphics.Color.WHITE)))
    }

    @Test
    fun testBinding_setTextColor_for_String() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
            }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextColor(Color.String("red"))
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(android.graphics.Color.RED)))
    }

    @Test
    fun testBinding_setTextColor_for_Drawable() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
            }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextColor(Color.Drawable(ColorDrawable(0x0000ff)))
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0x0000ff)))
    }

    @Test
    fun testBinding_setTextColor_for_StateList() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
            }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        val stateList = ColorStateList(
            arrayOf(intArrayOf(), intArrayOf(android.R.attr.state_selected)),
            intArrayOf(0x0000ff, 0x00ffff)
        )
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setTextColor(Color.StateList(stateList))
        }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(stateList)))
    }

    @Test
    fun testBinding_setHint() {
        launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED).onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1)
                    .setHint(Text.of(R.string.test_text))
            }
        onView(withId(android.R.id.text1)).check(matches(withHint("Testing")))
    }

    @Test
    fun testBinding_setHintTextColor_for_Int() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
            }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHintTextColor(Color.Int(0xff))
        }
        onView(withId(android.R.id.text1)).check(matches(withHintColor(0xff)))
    }

    @Test
    fun testBinding_setHintTextColor_for_Res() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
            }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHintTextColor(Color.Res(android.R.color.white))
        }
        onView(withId(android.R.id.text1)).check(matches(withHintColor(android.graphics.Color.WHITE)))
    }

    @Test
    fun testBinding_setHintTextColor_for_String() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
            }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHintTextColor(Color.String("red"))
        }
        onView(withId(android.R.id.text1)).check(matches(withHintColor(android.graphics.Color.RED)))
    }

    @Test
    fun testBinding_setHintTextColor_for_Drawable() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
            }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHintTextColor(Color.Drawable(ColorDrawable(0x0000ff)))
        }
        onView(withId(android.R.id.text1)).check(matches(withHintColor(0x0000ff)))
    }

    @Test
    fun testBinding_setHintTextColor_for_StateList() {
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
            .onFragment { fragment ->
                fragment.requireView().findViewById<TextView>(android.R.id.text1).setTextColor(0)
            }
        onView(withId(android.R.id.text1)).check(matches(hasTextColor(0)))
        val stateList = ColorStateList(
            arrayOf(intArrayOf(), intArrayOf(android.R.attr.state_selected)),
            intArrayOf(0x0000ff, 0x00ffff)
        )
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setHintTextColor(Color.StateList(stateList))
        }
        onView(withId(android.R.id.text1)).check(matches(withHintTint(stateList)))
    }

    private fun hasTextColor(@ColorInt color: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has color with integer $color")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.currentTextColor == color
            }
        }
    }

    private fun hasTextColor(stateList: ColorStateList): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has color with state-list $stateList")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.textColors.toString() == stateList.toString()
            }
        }
    }

    private fun withHint(value: CharSequence?): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with hint $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.hint == value
            }
        }
    }

    private fun withHintTint(value: ColorStateList?): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with hint text color $value")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.hintTextColors?.toString() == value?.toString()
            }
        }
    }

    private fun withHintColor(@ColorInt value: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with hint text color ${android.graphics.Color.valueOf(value)}")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.currentHintTextColor == value
            }
        }
    }

    private fun isAllCaps(): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("is all caps")
            }

            override fun matchesSafely(item: TextView): Boolean {
                return item.isAllCaps
            }
        }
    }
}
