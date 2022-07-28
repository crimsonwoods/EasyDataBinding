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
    implementation "com.github.crimsonwoods:EasyDataBinding:$latest_version"
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

        <variable name="viewModel" type="MyActivityViewModel" />
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
- Attr (experimental)
- Bool
- Color
- ColorStateList
- Dimension
- Drawable
- Float
- Fraction
- Integer
- Interpolator
- String
- TextAppearance
- Theme

__未サポート__:

- Animator
- Array (String, Integer, etc.)
- Font
- ID
- Layout
- Menu
- Raw
- Style

## BindingAdapter

### View

| XML attribute               | data model |
|:----------------------------|:-----------|
| android:alpha               | Float      |
| android:background          | Background |
| android:backgroundTint      | Tint       |
| android:clickable           | Bool       |
| android:contentDescription  | Text       |
| android:enabled             | Bool       |
| android:focusable           | Bool       |
| android:foreground          | Drawable   |
| android:foregroundTint      | Tint       |
| android:longClickable       | Bool       |
| android:minHeight           | Dimension  |
| android:minWidth            | Dimension  |
| android:padding             | Dimension  |
| android:paddingLeft         | Dimension  |
| android:paddingStart        | Dimension  |
| android:paddingRight        | Dimension  |
| android:paddingEnd          | Dimension  |
| android:paddingTop          | Dimension  |
| android:paddingBottom       | Dimension  |
| android:rotation            | Float      |
| android:rotationX           | Float      |
| android:rotationY           | Float      |
| android:scaleX              | Float      |
| android:scaleY              | Float      |
| android:soundEffectsEnabled | Bool       |
| android:visibility          | Integer    |

### ViewGroup

|XML attribute         |data model|
|:---------------------|:---------|
|android:clipChildren  |Bool      |
|android:clipToPadding |Bool      |

### TextView

|XML attribute                     |data model    |
|:---------------------------------|:-------------|
|android:cursorVisible             |Bool          |
|android:drawablePadding           |Dimension     |
|android:drawableTint              |Tint          |
|android:elegantTextHeight         |Bool          |
|android:ems                       |Integer       |
|android:fallbackLineSpacing       |Bool          |
|android:firstBaselineToTopHeight  |Dimension     |
|android:fontFeatureSettings       |Text          |
|android:fontVariationSettings     |Text          |
|android:freezesText               |Bool          |
|android:height                    |Dimension     |
|android:hint                      |Text          |
|android:includeFontPadding        |Bool          |
|android:lastBaselineToBottomHeight|Dimension     |
|android:lineHeight                |Dimension     |
|android:lineSpacingExtra          |Dimension     |
|android:lineSpacingMultiplier     |Float         |
|android:lines                     |Integer       |
|android:linksClickable            |Bool          |
|android:maxEms                    |Integer       |
|android:maxHeight                 |Dimension     |
|android:maxLines                  |Integer       |
|android:maxWidth                  |Dimension     |
|android:minEms                    |Integer       |
|android:minHeight                 |Dimension     |
|android:minLines                  |Integer       |
|android:minWidth                  |Dimension     |
|android:scrollHorizontally        |Bool          |
|android:selectAllOnFocus          |Bool          |
|android:text                      |Text          |
|android:textAllCaps               |Bool          |
|android:textAppearance            |TextAppearance|
|android:textColor                 |Color         |
|android:textColorHint             |Color         |
|android:textScaleX                |Float         |
|android:textSize                  |Dimension     |
|android:width                     |Dimension     |

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

### Switch

|XML attribute               |data model    |
|:---------------------------|:-------------|
|android:showText            |Bool          |
|android:splitTrack          |Bool          |
|android:switchMinWidth      |Dimension     |
|android:switchPadding       |Dimension     |
|android:switchTextAppearance|TextAppearance|
|android:textOff             |Text          |
|android:textOn              |Text          |
|android:thumb               |Drawable      |
|android:thumbTextPadding    |Dimension     |
|android:thumbTint           |Tint          |
|android:track               |Drawable      |
|android:trackTint           |Tint          |

### Toolbar

|XML attribute                          |data model    |
|:--------------------------------------|:-------------|
|android:collapseContentDescription     |Text          |
|android:collapseIcon                   |Drawable      |
|android:contentInsetEnd                |Dimension     |
|android:contentInsetEndWithActions     |Dimension     |
|android:contentInsetLeft               |Dimension     |
|android:contentInsetRight              |Dimension     |
|android:contentInsetStart              |Dimension     |
|android:contentInsetStartWithNavigation|Dimension     |
|android:logo                           |Drawable      |
|android:logoDescription                |Text          |
|android:navigationContentDescription   |Text          |
|android:navigationIcon                 |Drawable      |
|android:popupTheme                     |Theme         |
|android:subtitle                       |Text          |
|android:subtitleTextAppearance         |TextAppearance|
|android:subtitleTextColor              |Color         |
|android:title                          |Text          |
|android:titleMargin                    |Dimension     |
|android:titleMarginBottom              |Dimension     |
|android:titleMarginEnd                 |Dimension     |
|android:titleMarginStart               |Dimension     |
|android:titleMarginTop                 |Dimension     |
|android:titleTextAppearance            |TextAppearance|
|android:titleTextColor                 |Color         |

### ViewAnimator

|XML attribute       |data model|
|:-------------------|:---------|
|android:inAnimation |Animation |
|android:outAnimation|Animation |

### ViewFlipper

|XML attribute       |data model|
|:-------------------|:---------|
|android:autoStart   |Bool      |
|android:flipInterval|Integer   |

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

### AndroidX AppCompat

#### LinearLayoutCompat

|XML attribute                    |data model|
|:--------------------------------|:---------|
|android:baselineAligned          |Bool      |
|android:baselineAlignedChildIndex|Integer   |
|app:divider                      |Drawable  |
|app:measureWithLargestChild      |Bool      |
|app:dividerPadding               |Dimension |

#### SearchView

|XML attribute         |data model|
|:---------------------|:---------|
|android:maxWidth      |Dimension |
|app:iconifiedByDefault|Bool      |
|app:queryHint         |Text      |

#### SwitchCompat

|XML attribute           |data model    |
|:-----------------------|:-------------|
|android:textOff         |Text          |
|android:textOn          |Text          |
|android:thumb           |Drawable      |
|app:showText            |Bool          |
|app:splitTrack          |Bool          |
|app:switchMinWidth      |Dimension     |
|app:switchPadding       |Dimension     |
|app:switchTextAppearance|TextAppearance|
|app:thumbTextPadding    |Dimension     |
|app:thumbTint           |Tint          |
|app:track               |Drawable      |
|app:trackTint           |Tint          |

#### Toolbar

|XML attribute                      |data model    |
|:----------------------------------|:-------------|
|app:collapseContentDescription     |Text          |
|app:collapseIcon                   |Drawable      |
|app:contentInsetEnd                |Dimension     |
|app:contentInsetEndWithActions     |Dimension     |
|app:contentInsetLeft               |Dimension     |
|app:contentInsetRight              |Dimension     |
|app:contentInsetStart              |Dimension     |
|app:contentInsetStartWithNavigation|Dimension     |
|app:logo                           |Drawable      |
|app:logoDescription                |Text          |
|app:navigationContentDescription   |Text          |
|app:navigationIcon                 |Drawable      |
|app:popupTheme                     |Theme         |
|app:subtitle                       |Text          |
|app:subtitleTextAppearance         |TextAppearance|
|app:subtitleTextColor              |Color         |
|app:title                          |Text          |
|app:titleMargin                    |Dimension     |
|app:titleMarginBottom              |Dimension     |
|app:titleMarginEnd                 |Dimension     |
|app:titleMarginStart               |Dimension     |
|app:titleMarginTop                 |Dimension     |
|app:titleTextAppearance            |TextAppearance|
|app:titleTextColor                 |Color         |

# License

MIT License
