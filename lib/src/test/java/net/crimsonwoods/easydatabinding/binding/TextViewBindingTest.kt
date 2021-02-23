package net.crimsonwoods.easydatabinding.binding

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.Test
import net.crimsonwoods.easydatabinding.fragment.TestFragment
import net.crimsonwoods.easydatabinding.models.Text
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class TextViewBindingTest {
    @Test
    fun testBinding_setText_for_CharSequence() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        shadowOf(context.packageManager).apply {
            val componentName = ComponentName(
                context,
                "androidx.fragment.app.testing.FragmentScenario\$EmptyFragmentActivity"
            )
            addActivityIfNotPresent(
                componentName
            )
            addIntentFilterForActivity(
                componentName,
                IntentFilter(Intent.ACTION_MAIN).apply {
                    addCategory(Intent.CATEGORY_LAUNCHER)
                }
            )
        }
        val scenario = launchFragmentInContainer<TestFragment>()
            .moveToState(Lifecycle.State.RESUMED)
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("")))
        scenario.onFragment { fragment ->
            fragment.requireView().findViewById<TextView>(android.R.id.text1)
                .setText(Text.of("test"))
        }
        onView(withId(android.R.id.text1)).check(ViewAssertions.matches(withText("test")))
    }
}
