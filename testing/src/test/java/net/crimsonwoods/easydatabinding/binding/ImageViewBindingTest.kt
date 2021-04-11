package net.crimsonwoods.easydatabinding.binding

import android.content.ContentResolver
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
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
import kotlin.test.assertFailsWith
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Image
import net.crimsonwoods.easydatabinding.models.Integer.Companion.wrap
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageViewBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setCropToPadding() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setCropToPadding(Bool.TRUE)
        }
        onView(withId(android.R.id.icon)).check(matches(isCropToPadding()))
    }

    @Test
    fun testBinding_setMaxHeight() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setMaxHeight(Dimension.px(100f))
        }
        onView(withId(android.R.id.icon)).check(matches(withMaxHeight(100)))
    }

    @Test
    fun testBinding_setMaxWidth() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setMaxWidth(Dimension.px(100f))
        }
        onView(withId(android.R.id.icon)).check(matches(withMaxWidth(100)))
    }

    @Test
    fun testBinding_setScaleType() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setScaleType(wrap(1))
        }
        onView(withId(android.R.id.icon)).check(matches(withScaleType(ImageView.ScaleType.FIT_XY)))
    }

    @Test
    fun testBinding_setScaleType_FailedByIllegalArgumentException() {
        scenario.onFragment { fragment ->
            assertFailsWith<IllegalArgumentException> {
                fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                    .setScaleType(wrap(8))
            }
        }
        onView(withId(android.R.id.icon)).check(matches(withScaleType(ImageView.ScaleType.FIT_CENTER)))
    }

    @Test
    fun testBinding_setImage_Res() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setImage(Image.Res(android.R.mipmap.sym_def_app_icon))
        }
        onView(withId(android.R.id.icon))
            .check(matches(withDrawable<BitmapDrawable>()))
    }

    @Test
    fun testBinding_setImage_Res_Zero() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setImage(Image.Res(0))
        }
        onView(withId(android.R.id.icon))
            .check(matches(noDrawable()))
    }

    @Test
    fun testBinding_setImage_Drawable() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setImage(Image.Drawable(ColorDrawable(Color.RED)))
        }
        onView(withId(android.R.id.icon))
            .check(matches(withDrawable<ColorDrawable>()))
    }

    @Test
    fun testBinding_setImage_Drawable_Null() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setImage(Image.Drawable(null))
        }
        onView(withId(android.R.id.icon))
            .check(matches(noDrawable()))
    }

    @Test
    fun testBinding_setImage_Bitmap() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setImage(Image.Bitmap(Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)))
        }
        onView(withId(android.R.id.icon))
            .check(matches(withDrawable<BitmapDrawable>()))
    }

    @Test
    fun testBinding_setImage_Uri() {
        scenario.onFragment { fragment ->
            val resources = fragment.requireContext().resources
            val resId = R.color.test_color
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setImage(Image.Uri(resources.toResourceUri(resId)))
        }
        onView(withId(android.R.id.icon))
            .check(matches(withDrawable<ColorDrawable>()))
    }

    @Test
    fun testBinding_setImage_None() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setImage(Image.Res(android.R.drawable.ic_btn_speak_now))
        }
        onView(withId(android.R.id.icon))
            .check(matches(withDrawable<BitmapDrawable>()))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<ImageView>(android.R.id.icon)
                .setImage(Image.None)
        }
        onView(withId(android.R.id.icon))
            .check(matches(noDrawable()))
    }

    private fun Resources.toResourceUri(resId: Int): Uri {
        val scheme = ContentResolver.SCHEME_ANDROID_RESOURCE
        val packageName = getResourcePackageName(resId)
        val typeName = getResourceTypeName(resId)
        val entryName = getResourceEntryName(resId)
        return Uri.parse("${scheme}://${packageName}/${typeName}/${entryName}")
    }

    private inline fun <reified T : Drawable> withDrawable(): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has image resource with ${T::class.java.simpleName}")
            }

            override fun matchesSafely(item: ImageView): Boolean {
                val actual = item.drawable ?: return false
                return actual.javaClass == T::class.java
            }
        }
    }

    private fun noDrawable(): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has no image resource")
            }

            override fun matchesSafely(item: ImageView): Boolean {
                return item.drawable == null
            }
        }
    }

    private fun isCropToPadding(): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("is cropped to fit within its padding")
            }

            override fun matchesSafely(item: ImageView): Boolean {
                return item.cropToPadding
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withMaxHeight(value: Int): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with max height ($value)")
            }

            override fun matchesSafely(item: ImageView): Boolean {
                return item.maxHeight == value
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withMaxWidth(value: Int): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with max width ($value)")
            }

            override fun matchesSafely(item: ImageView): Boolean {
                return item.maxWidth == value
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun withScaleType(value: ImageView.ScaleType): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with scale type ($value)")
            }

            override fun matchesSafely(item: ImageView): Boolean {
                return item.scaleType == value
            }
        }
    }
}
