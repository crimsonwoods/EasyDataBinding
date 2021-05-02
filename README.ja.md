EasyDataBinding [![Check](https://github.com/crimsonwoods/EasyDataBinding/actions/workflows/check.yml/badge.svg?branch=main)](https://github.com/crimsonwoods/EasyDataBinding/actions/workflows/check.yml)
----

この小さなライブラリはシンプルなデータモデルとBindingAdapterを提供します。

# 目的
Androidの `ViewModel` は `Context` を持ちません。また一般に `Context` への参照を持たないように設計することが推奨されています。
このため、 `ViewModel` の実装に際してリソース（drawble, color, string, etc.）を扱うことが困難な場合があります。
この小さなライブラリはこの問題に対する解決策を提供します。
`Context` を要求しないシンプルなデータモデルを使ってDataBindingを実装することができます。

# 利用方法

## セットアップ

`build.gradle` ファイルに下記の記述を追加してください。

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

下記の依存関係も追加します。

```groovy
dependencies {
    implementation 'com.github.crimsonwoods:EasyDataBinding:0.0.8'
}
```

## コード

`ViewModel`:
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

`my_activity.xml`:
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

__サポート済み__:
- Animation
- Bool
- Color
- ColorStateList
- Dimension
- Drawable
- Fraction
- Integer
- String
- TextAppearance

__未サポート__:
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
