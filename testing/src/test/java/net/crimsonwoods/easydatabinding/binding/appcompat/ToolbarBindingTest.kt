package net.crimsonwoods.easydatabinding.binding.appcompat

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.NinePatchDrawable
import android.os.Build
import android.view.View
import androidx.annotation.Px
import androidx.annotation.RequiresApi
import androidx.annotation.StyleRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import kotlin.test.Test
import net.crimsonwoods.easydatabinding.fragment.AppCompatTestFragment
import net.crimsonwoods.easydatabinding.matcher.DrawableTypeMatcher
import net.crimsonwoods.easydatabinding.models.Color
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Drawable
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextAppearance
import net.crimsonwoods.easydatabinding.models.Theme
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToolbarBindingTest {
    private lateinit var scenario: FragmentScenario<AppCompatTestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<AppCompatTestFragment>(
            themeResId = R.style.FragmentScenarioEmptyFragmentAppCompatActivityTheme
        ).moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setCollapseContentDescription() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setCollapseContentDescription(Text.of(R.string.test_text))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withCollapseContentDescription("Testing")))
    }

    @Test
    fun testBinding_setCollapseIcon() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setCollapseIcon(Drawable.of(R.drawable.test_drawable_9patch))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withCollapseIconDrawableType<NinePatchDrawable>()))
    }

    @Test
    fun testBinding_setContentInsetEnd() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setContentInsetEnd(Dimension.px(100f))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withContentInsetEnd(100)))
    }

    @Test
    fun testBinding_setContentInsetEndWithActions() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setContentInsetEndWithActions(Dimension.px(100f))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withContentInsetEndWithActions(100)))
    }

    @Test
    fun testBinding_setContentInsetLeft() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setContentInsetLeft(Dimension.px(100f))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withContentInsetLeft(100)))
    }

    @Test
    fun testBinding_setContentInsetRight() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setContentInsetRight(Dimension.px(100f))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withContentInsetRight(100)))
    }

    @Test
    fun testBinding_setContentInsetStart() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setContentInsetStart(Dimension.px(100f))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withContentInsetStart(100)))
    }

    @Test
    fun testBinding_setContentInsetStartWithNavigation() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setContentInsetStartWithNavigation(Dimension.px(100f))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withContentInsetStartWithNavigation(100)))
    }

    @Test
    fun testBinding_setLogo() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setLogo(Drawable.of(R.drawable.test_drawable_oval))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withLogoDrawableType<GradientDrawable>()))
    }

    @Test
    fun testBinding_setLogoDescription() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setLogoDescription(Text.of(R.string.test_text))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withLogoDescription("Testing")))
    }

    @Test
    fun testBinding_setNavigationContentDescription() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setNavigationContentDescription(Text.of(R.string.test_text))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withNavigationContentDescription("Testing")))
    }

    @Test
    fun testBinding_setNavigationIcon() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setNavigationIcon(Drawable.of(R.drawable.test_drawable_9patch))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withNavigationIconDrawableTypeOf<NinePatchDrawable>()))
    }

    @Test
    fun testBinding_setPopupTheme() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setPopupTheme(Theme.of(R.style.ThemeOverlay_AppCompat_Dark_ActionBar))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withPopupTheme(R.style.ThemeOverlay_AppCompat_Dark_ActionBar)))
    }

    @Test
    fun testBinding_setSubtitle() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setSubtitle(Text.of(R.string.test_text))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withSubtitle("Testing")))
    }

    @Test
    fun testBinding_setSubtitleTextAppearance() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setSubtitleTextAppearance(TextAppearance.of(R.style.TextAppearance_Testing_Test))
        }
        // Currently, text appearance of subtitle cannot be verified from outside.
    }

    @Test
    fun testBinding_setSubtitleTextColor() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setSubtitleTextColor(Color.of(R.color.test_color))
        }
        // Currently, text appearance of subtitle cannot be verified from outside.
    }

    @Test
    fun testBinding_setTitle() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setTitle(Text.of(R.string.test_text))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withTitle("Testing")))
    }

    @Test
    fun testBinding_setTitleMargin() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setTitleMargin(Dimension.px(100f))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withTitleMargin(100)))
    }

    @Test
    fun testBinding_setTitleMarginBottom() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setTitleMarginBottom(Dimension.px(100f))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withTitleMarginBottom(100)))
    }

    @Test
    fun testBinding_setTitleMarginEnd() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setTitleMarginEnd(Dimension.px(100f))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withTitleMarginEnd(100)))
    }

    @Test
    fun testBinding_setTitleMarginStart() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setTitleMarginStart(Dimension.px(100f))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withTitleMarginStart(100)))
    }

    @Test
    fun testBinding_setTitleMarginTop() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setTitleMarginTop(Dimension.px(100f))
        }
        onView(withId(R.id.toolbar))
            .check(matches(withTitleMarginTop(100)))
    }

    @Test
    fun testBinding_setTitleTextAppearance() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setTitleTextAppearance(TextAppearance.of(R.style.TextAppearance_Testing_Test))
        }
        // Currently, text appearance of title cannot be verified from outside.
    }

    @Test
    fun testBinding_setTitleTextColor() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<Toolbar>(R.id.toolbar)
                .setTitleTextColor(Color.of(R.color.test_color))
        }
        // Currently, text appearance of title cannot be verified from outside.
    }

    @Suppress("SameParameterValue")
    private fun withCollapseContentDescription(value: CharSequence?): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with collapse content description $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.collapseContentDescription == value
            }
        }
    }

    private inline fun <reified T : android.graphics.drawable.Drawable> withCollapseIconDrawableType(): Matcher<View> {
        return DrawableTypeMatcher(Toolbar::class, T::class) {
            collapseIcon
        }
    }

    @Suppress("SameParameterValue")
    private fun withContentInsetEnd(@Px value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with content inset end $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.contentInsetEnd == value
            }
        }
    }

    @RequiresApi(value = Build.VERSION_CODES.N)
    @Suppress("SameParameterValue")
    private fun withContentInsetEndWithActions(@Px value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with content inset end with actions $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.contentInsetEndWithActions == value
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withContentInsetLeft(@Px value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with content inset left $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.contentInsetLeft == value
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withContentInsetRight(@Px value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with content inset right $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.contentInsetRight == value
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withContentInsetStart(@Px value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with content inset start $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.contentInsetStart == value
            }
        }
    }

    @RequiresApi(value = Build.VERSION_CODES.N)
    @Suppress("SameParameterValue")
    private fun withContentInsetStartWithNavigation(@Px value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with content inset start with navigation $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.contentInsetStartWithNavigation == value
            }
        }
    }

    private inline fun <reified T : android.graphics.drawable.Drawable> withLogoDrawableType(): Matcher<View> {
        return DrawableTypeMatcher(Toolbar::class, T::class) {
            logo
        }
    }

    @Suppress("SameParameterValue")
    private fun withLogoDescription(value: CharSequence?): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with logo description $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.logoDescription == value
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withNavigationContentDescription(value: CharSequence?): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with navigation content description $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.navigationContentDescription == value
            }
        }
    }

    private inline fun <reified T : android.graphics.drawable.Drawable> withNavigationIconDrawableTypeOf(): Matcher<View> {
        return DrawableTypeMatcher(Toolbar::class, T::class) {
            navigationIcon
        }
    }

    private fun withPopupTheme(@StyleRes value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with popup theme $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.popupTheme == value
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withSubtitle(value: CharSequence?): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with subtitle $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.subtitle == value
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withTitle(value: CharSequence?): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with title $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.title == value
            }
        }
    }

    @RequiresApi(value = Build.VERSION_CODES.N)
    @Suppress("SameParameterValue")
    private fun withTitleMargin(@Px value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with title margin $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.titleMarginTop == value
                        && item.titleMarginBottom == value
                        && item.titleMarginStart == value
                        && item.titleMarginEnd == value
            }
        }
    }

    @RequiresApi(value = Build.VERSION_CODES.N)
    @Suppress("SameParameterValue")
    private fun withTitleMarginBottom(@Px value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with title margin bottom $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.titleMarginBottom == value
            }
        }
    }

    @RequiresApi(value = Build.VERSION_CODES.N)
    @Suppress("SameParameterValue")
    private fun withTitleMarginEnd(@Px value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with title margin end $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.titleMarginEnd == value
            }
        }
    }

    @RequiresApi(value = Build.VERSION_CODES.N)
    @Suppress("SameParameterValue")
    private fun withTitleMarginStart(@Px value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with title margin start $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.titleMarginStart == value
            }
        }
    }

    @RequiresApi(value = Build.VERSION_CODES.N)
    @Suppress("SameParameterValue")
    private fun withTitleMarginTop(@Px value: Int): Matcher<View> {
        return object : ToolbarMatcher() {
            override fun describeTo(description: Description) {
                description.appendText("with title margin top $value")
            }

            override fun matchesSafely(item: Toolbar): Boolean {
                return item.titleMarginTop == value
            }
        }
    }

    private abstract class ToolbarMatcher : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
        abstract override fun describeTo(description: Description)
        abstract override fun matchesSafely(item: Toolbar): Boolean
    }
}
