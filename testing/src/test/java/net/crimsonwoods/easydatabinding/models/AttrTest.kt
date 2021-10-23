package net.crimsonwoods.easydatabinding.models

import androidx.appcompat.view.ContextThemeWrapper
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import net.crimsonwoods.easydatabinding.testing.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AttrTest {
    private val context = ContextThemeWrapper(
        ApplicationProvider.getApplicationContext(),
        R.style.TestingTheme
    )

    @Test
    fun isColor() {
        assertTrue(Attr.of(R.attr.colorPrimary).isColor(context))
        assertTrue(Attr.of(R.attr.colorPrimaryDark).isColor(context))
        assertTrue(Attr.of(R.attr.colorTesting).isColor(context))

        assertFalse(Attr.of(R.attr.alpha).isColor(context))
        assertFalse(Attr.of(R.attr.dividerVertical).isColor(context))
        assertFalse(Attr.of(R.attr.windowFixedWidthMajor).isColor(context))
    }

    @Test
    fun asColor() {
        assertEquals(
            Color.argb(context.getColor(androidx.appcompat.R.color.primary_material_dark)),
            Attr.of(R.attr.colorPrimary).asColor(context)
        )
        assertEquals(
            Color.argb(context.getColor(androidx.appcompat.R.color.primary_dark_material_dark)),
            Attr.of(R.attr.colorPrimaryDark).asColor(context)
        )
        assertEquals(
            Color.argb(context.getColor(android.R.color.darker_gray)),
            Attr.of(R.attr.colorTesting).asColor(context)
        )

        assertNull(Attr.of(R.attr.alpha).asColor(context))
        assertNull(Attr.of(R.attr.dividerVertical).asColor(context))
        assertNull(Attr.of(R.attr.windowFixedWidthMajor).asColor(context))
    }

    @Test
    fun isDrawable() {
        assertTrue(Attr.of(R.attr.dividerVertical).isDrawable(context))
        assertTrue(Attr.of(R.attr.actionBarDivider).isDrawable(context))
        assertTrue(Attr.of(R.attr.selectableItemBackground).isDrawable(context))

        assertFalse(Attr.of(R.attr.colorPrimary).isDrawable(context))
        assertFalse(Attr.of(R.attr.alpha).isDrawable(context))
        assertFalse(Attr.of(R.attr.windowFixedWidthMajor).isDrawable(context))
    }

    @Test
    fun asDrawable() {
        assertNotNull(Attr.of(R.attr.dividerVertical).asDrawable(context))
        assertNotNull(Attr.of(R.attr.actionBarDivider).asDrawable(context))
        assertNotNull(Attr.of(R.attr.selectableItemBackground).asDrawable(context))

        assertNull(Attr.of(R.attr.colorPrimary).asDrawable(context))
        assertNull(Attr.of(R.attr.alpha).asDrawable(context))
        assertNull(Attr.of(R.attr.windowFixedWidthMajor).asDrawable(context))
    }

    @Test
    fun isDimension() {
        assertTrue(Attr.of(R.attr.listPreferredItemHeight).isDimension(context))
        assertTrue(Attr.of(R.attr.actionBarSize).isDimension(context))

        assertFalse(Attr.of(R.attr.colorPrimary).isDimension(context))
        assertFalse(Attr.of(R.attr.alpha).isDimension(context))
        assertFalse(Attr.of(R.attr.selectableItemBackground).isDimension(context))
        assertFalse(Attr.of(R.attr.windowFixedWidthMajor).isDimension(context))
    }

    @Test
    fun asDimension() {
        assertEquals(
            // same as "androidx.appcompat.R.dimen.abc_list_item_height_material"
            Dimension.dp(64f),
            Attr.of(R.attr.listPreferredItemHeight).asDimension(context)
        )
        assertEquals(
            // same as "androidx.appcompat.R.dimen.abc_action_bar_default_height_material"
            Dimension.dp(56f),
            Attr.of(R.attr.actionBarSize).asDimension(context)
        )

        assertNull(Attr.of(R.attr.colorPrimary).asDimension(context))
        assertNull(Attr.of(R.attr.alpha).asDimension(context))
        assertNull(Attr.of(R.attr.selectableItemBackground).asDimension(context))
        assertNull(Attr.of(R.attr.windowFixedWidthMajor).asDimension(context))
    }
}
