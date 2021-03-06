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
        val TRUE: Bool = Value(true)

        val FALSE: Bool = Value(false)

        fun of(@BoolRes resId: Int): Bool = Res(resId)

        fun of(value: Boolean): Bool = Value(value)
    }
}
