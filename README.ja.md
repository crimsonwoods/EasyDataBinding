EasyDataBinding [![Check](https://github.com/crimsonwoods/EasyDataBinding/actions/workflows/check.yml/badge.svg?branch=main)](https://github.com/crimsonwoods/EasyDataBinding/actions/workflows/check.yml)
----

この小さなライブラリはシンプルなデータモデルとBindingAdapterを提供します。


__注意:__

このプロジェクトにはまだ安定板やリリース版の成果物は存在しません。


# 目的
Androidの `ViewModel` は `Context` を持ちません。また一般に `Context` への参照を持たないように設計することが推奨されています。
このため、 `ViewModel` の実装に際してリソース（drawble, color, string, etc.）を扱うことが困難な場合があります。
この小さなライブラリはこの問題に対する解決策を提供します。
`Context` を要求しないシンプルなデータモデルを使ってDataBindingを実装することができます。

# 利用方法
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
- Bool
- Color
- Dimension
- Drawable
- String

__未サポート__:
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
