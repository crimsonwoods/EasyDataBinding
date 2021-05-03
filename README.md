EasyDataBinding [![Check](https://github.com/crimsonwoods/EasyDataBinding/actions/workflows/check.yml/badge.svg?branch=main)](https://github.com/crimsonwoods/EasyDataBinding/actions/workflows/check.yml)
----

[In Japanese](README.ja.md) / [日本語版](README.ja.md)

This small library provides simple data model and some BindingAdapters.

# Motive
Android `ViewModel` has no `Context`. It makes difficult to handle resources (drawable, color, string, etc.).
This small library helps you to solve this issue.
You can use simple data model that requires no `Context` and bind it to `View` attributes via DataBinding.

# Usage

## Setup

Adds below configuration into your `build.gradle`.

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

And adds a dependency

```groovy
dependencies {
    implementation 'com.github.crimsonwoods:EasyDataBinding:0.0.9'
}
```

## Code

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
- Animation
- Bool
- Color
- ColorStateList
- Dimension
- Drawable
- Fraction
- Integer
- Interpolator
- String
- TextAppearance

__Not Supported__:
- Animator
- Array (String, Integer, etc.)
- Attr
- Font
- ID
- Layout
- Menu
- Raw
- Style/Theme

## BindingAdapter

### View

|XML attribute              |data model|
|:--------------------------|:---------|
|android:alpha              |Fraction  |
|android:background         |Background|
|android:backgroundTint     |Tint      |
|android:clickable          |Bool      |
|android:contentDescription |Text      |
|android:enabled            |Bool      |
|android:focusable          |Bool      |
|android:longClickable      |Bool      |
|android:minHeight          |Dimension |
|android:minWidth           |Dimension |
|android:padding            |Dimension |
|android:paddingLeft        |Dimension |
|android:paddingStart       |Dimension |
|android:paddingRight       |Dimension |
|android:paddingEnd         |Dimension |
|android:paddingTop         |Dimension |
|android:paddingBottom      |Dimension |
|android:soundEffectsEnabled|Bool      |
|android:visibility         |Integer   |

### ViewGroup

|XML attribute         |data model|
|:---------------------|:---------|
|android:clipChildren  |Bool      |
|android:clipToPadding |Bool      |

### TextView

|XML attribute         |data model    |
|:---------------------|:-------------|
|android:text          |Text          |
|android:textAppearance|TextAppearance|
|android:textSize      |Dimension     |
|android:textColor     |Color         |
|android:hint          |Text          |
|android:textColorHint |Color         |

### CompoundButton

|XML attribute     |data model|
|:-----------------|:---------|
|android:checked   |Bool      |
|android:button    |Drawable  |
|android:buttonTint|Tint      |

### ToggleButton

|XML attribute  |data model|
|:--------------|:---------|
|android:textOff|Text      |
|android:textOn |Text      |

### ImageView

|XML attribute        |data model|
|:--------------------|:---------|
|android:cropToPadding|Bool      |
|android:maxHeight    |Dimension |
|android:maxWidth     |Dimension |
|android:scaleType    |Integer   |
|android:src          |Image     |
|app:tint             |Tint      |

### AbsListView

|XML attribute            |data model|
|:------------------------|:---------|
|android:fastScrollEnabled|Bool      |
|android:smoothScrollbar  |Bool      |
|android:stackFromBottom  |Bool      |
|android:textFilterEnabled|Bool      |

### ListView

|XML attribute                |data model|
|:----------------------------|:---------|
|android:divider              |Drawable  |
|android:dividerHeight        |Dimension |
|android:footerDividersEnabled|Bool      |
|android:headerDividersEnabled|Bool      |

### ProgressBar

|XML attribute                |data model   |
|:----------------------------|:------------|
|android:indeterminate         |Bool        |
|android:indeterminateDrawable |Drawable    |
|android:indeterminateTint     |Tint        |
|android:interpolator          |Interpolator|
|android:max                   |Integer     |
|android:maxHeight             |Dimension   |
|android:maxWidth              |Dimension   |
|android:min                   |Integer     |
|android:minHeight             |Dimension   |
|android:minWidth              |Dimension   |
|android:progress              |Integer     |
|android:progressBackgroundTint|Tint        |
|android:progressDrawable      |Drawable    |
|android:progressTint          |Tint        |
|android:secondaryProgress     |Integer     |
|android:secondaryProgressTint |Tint        |

### SearchView

|XML attribute             |data model|
|:-------------------------|:---------|
|android:iconifiedByDefault|Bool      |
|android:maxWidth          |Dimension |
|android:queryHint         |Text      |

### ScrollView

|XML attribute       |data model|
|:-------------------|:---------|
|android:fillViewport|Bool      |

### ViewAnimator

|XML attribute       |data model|
|:-------------------|:---------|
|android:inAnimation |Animation |
|android:outAnimation|Animation |

### FrameLayout

|XML attribute             |data model|
|:-------------------------|:---------|
|android:measureAllChildren|Bool      |

### LinearLayout

|XML attribute                    |data model|
|:--------------------------------|:---------|
|android:baselineAligned          |Bool      |
|android:baselineAlignedChildIndex|Integer   |
|android:measureWithLargestChild  |Bool      |
|android:dividerPadding           |Dimension |

# License
MIT License
