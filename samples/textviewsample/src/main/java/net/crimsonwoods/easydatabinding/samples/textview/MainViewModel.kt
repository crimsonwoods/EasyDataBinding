package net.crimsonwoods.easydatabinding.samples.textview

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.crimsonwoods.easydatabinding.models.Color
import net.crimsonwoods.easydatabinding.models.Text
import net.crimsonwoods.easydatabinding.models.TextSize

class MainViewModel : ViewModel() {
    private val _text = MutableLiveData(Text.of(R.string.text_hello_world))
    private val _textSize = MutableLiveData(TextSize.of(R.dimen.text_size_normal))
    private val _textColor = MutableLiveData(Color.of(android.R.color.white))
    private val _hintTextColor = MutableLiveData(Color.of(android.R.color.darker_gray))

    val text: LiveData<Text> = _text
    val textSize: LiveData<TextSize> = _textSize
    val textColor: LiveData<Color> = _textColor
    val hintTextColor: LiveData<Color> = _hintTextColor

    val onClickButton: View.OnClickListener = View.OnClickListener {
        _text.value = listOf(
            Text.of(R.string.text_hello_world),
            Text.of(R.string.text_hello_android),
            Text.of(R.string.text_hello_databinding),
            Text.of(""),
        ).random()

        _textSize.value = listOf(
            TextSize.of(R.dimen.text_size_normal),
            TextSize.of(R.dimen.text_size_medium),
            TextSize.of(R.dimen.text_size_large),
        ).random()

        _textColor.value = listOf(
            Color.of(R.color.purple_200),
            Color.of(R.color.purple_500),
            Color.of(R.color.purple_700),
        ).random()

        _hintTextColor.value = listOf(
            Color.of(R.color.teal_200),
            Color.of(R.color.teal_700),
        ).random()
    }
}
