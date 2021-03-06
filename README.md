EasyDataBinding [![Check](https://github.com/crimsonwoods/EasyDataBinding/actions/workflows/check.yml/badge.svg?branch=main)](https://github.com/crimsonwoods/EasyDataBinding/actions/workflows/check.yml)
----

[In Japanese](README.ja.md) / [日本語版](README.ja.md)

This small library provides simple data model and some BindingAdapters.


__NOTICE:__

This project has NO stable/released version yet.


# Motive
Android `ViewModel` has no `Context`. It makes difficult to handle resources (drawable, color, string, etc.).
This small library helps you to solve this issue.
You can use simple data model that requires no `Context` and bind it to `View` attributes via DataBinding.

# Usage
In your `ViewModel`:
```Kotlin
class MyActivityViewModel : ViewModel() {
    private val _message = MutableLiveData(Text.of(R.string.message))

    val message: LiveData<Text> = _message

    val onClickButton: View.OnClickListener
        get() = View.OnClickListener {
            // Context is NOT necessary 
            _message.value = Text.of(R.string.clicked)
        }
}
```

In your `my_activity.xml`:
```XML
<?xml version="1.0" encoding="utf-8"?>
<layout>
  <data>
    ...
    
    <variable
        name="viewModel"
        type="MyActivityViewModel"/>
  </data>
  
  ...
  
  <TextView
      ...
      android:text="@{viewModel.message}"
      .../>
  
  ...
  
  <Button
      ...
      android:onClick="@{viewModel.onClickButton}"
      .../>
</layout>
```

## Resource Types

__Supported__:
- Bool
- Color
- Dimension
- Drawable
- String

__Not Supported__:
- Animation
- Array (String, Integer, etc.)
- Attr
- ColorStateList (一部サポート)
- Font
- ID
- Integer
- Layout
- Menu
- Raw
- Style/Theme

## BindingAdapter

### View

|XML attribute        |data model|
|:--------------------|:---------|
|android:background   |Background|
|android:clickable    |Bool      |
|android:focusable    |Bool      |
|android:minHeight    |Dimension |
|android:minWidth     |Dimension |
|android:padding      |Dimension |
|android:paddingLeft  |Dimension |
|android:paddingStart |Dimension |
|android:paddingRight |Dimension |
|android:paddingEnd   |Dimension |
|android:paddingTop   |Dimension |
|android:paddingBottom|Dimension |

### TextView

|XML attribute        |data model|
|:--------------------|:---------|
|android:text         |Text      |
|android:textSize     |Dimension |
|android:textColor    |Color     |
|android:hintTextColor|Color     |

### ImageView

|XML attribute|data model|
|:-----------:|:---------|
|android:src  |Image     |

# License
MIT License
