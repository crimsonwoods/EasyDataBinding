package net.crimsonwoods.easydatabinding.binding

import android.os.Build
import android.view.View
import android.widget.SearchView
import androidx.annotation.Px
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.BeforeTest
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.models.Bool
import net.crimsonwoods.easydatabinding.models.Dimension
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.testing.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchViewBindingTest {
    private lateinit var scenario: FragmentScenario<TestFragment>

    @BeforeTest
    fun setUp() {
        scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testBinding_setIconifiedByDefault() {
        scenario.onFragment { fragment ->
            // iconifiedByDefault is true by default.
            fragment.requireView().findViewById<SearchView>(R.id.search)
                .setIconifiedByDefault(Bool.FALSE)
        }
        onView(withId(R.id.search))
            .check(matches(not(isIconifiedByDefault())))
    }

    @Test
    fun testBinding_setMaxWidth() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SearchView>(R.id.search)
                .setMaxWidth(Dimension.px(100f))
        }
        onView(withId(R.id.search))
            .check(matches(withMaxWidth(100)))
    }

    @Test
    fun testBinding_setQueryHint() {
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<SearchView>(R.id.search)
                .setQueryHint(Text.of(R.string.test_text))
        }
        onView(withId(R.id.search))
            .check(matches(withQueryHint("Testing")))
    }

    private fun isIconifiedByDefault(): Matcher<View> {
        return object : BoundedMatcher<View, SearchView>(SearchView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("is iconified-by-default")
            }

            override fun matchesSafely(item: SearchView): Boolean {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    item.isIconifiedByDefault
                } else {
                    @Suppress("DEPRECATION")
                    item.isIconfiedByDefault
                }
            }
        }
    }

    private fun withMaxWidth(@Px value: Int): Matcher<View> {
        return object : BoundedMatcher<View, SearchView>(SearchView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with max width $value")
            }

            override fun matchesSafely(item: SearchView): Boolean {
                return item.maxWidth == value
            }
        }
    }

    private fun withQueryHint(value: CharSequence?): Matcher<View> {
        return object : BoundedMatcher<View, SearchView>(SearchView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with query-hint $value")
            }

            override fun matchesSafely(item: SearchView): Boolean {
                return item.queryHint == value
            }
        }
    }
}
