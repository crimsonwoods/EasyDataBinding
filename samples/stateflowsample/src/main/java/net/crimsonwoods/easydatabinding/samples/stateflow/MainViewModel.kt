package net.crimsonwoods.easydatabinding.samples.stateflow

import android.view.View
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.crimsonwoods.easydatabinding.models.Image
import net.crimsonwoods.easydatabinding.models.Text

class MainViewModel : ViewModel() {
    data class ViewState(
        val icon: Image,
        val iconVisibility: Int,
        val text: Text,
    ) {
        companion object {
            fun initial(): ViewState = ViewState(
                icon = Image.None,
                iconVisibility = View.GONE,
                text = Text.of("Tap anywhere."),
            )
        }
    }

    private val random = Random
    private val images = listOf(
        Image.of(R.drawable.ic_bolt),
        Image.of(R.drawable.ic_sentiment_satisfied),
        Image.of(R.drawable.ic_star),
    )
    private val texts = listOf(
        Text.of(R.string.text1),
        Text.of(R.string.text2),
        Text.of(R.string.text3),
    )
    private val _viewState = MutableStateFlow(ViewState.initial())

    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    @MainThread
    fun update() {
        val imageIndex = random.nextInt(0, 3)
        val textIndex = random.nextInt(0, 3)
        _viewState.value = ViewState(
            icon = images[imageIndex],
            iconVisibility = View.VISIBLE,
            text = texts[textIndex],
        )
    }
}
