package net.crimsonwoods.easydatabinding.models

import androidx.annotation.BoolRes

sealed class Bool {
    data class Res(
        @BoolRes val resId: Int
    ) : Bool()

    data class Value(
        val rawValue: Boolean
    ) : Bool()

    companion object {
        @JvmField
        val TRUE: Bool = Value(true)

        @JvmField
        val FALSE: Bool = Value(false)

        @JvmStatic
        fun of(@BoolRes resId: Int): Bool = Res(resId)

        @JvmStatic
        fun of(value: Boolean): Bool = Value(value)
    }
}
