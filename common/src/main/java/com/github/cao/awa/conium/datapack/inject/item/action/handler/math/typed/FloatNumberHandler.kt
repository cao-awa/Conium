package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction

class FloatNumberHandler : NumberHandler<Float>() {
    override fun doHandle(first: Float, second: Float, action: ItemPropertyInjectAction): Float {
        return when (action) {
            ItemPropertyInjectAction.ADD -> first + second
            ItemPropertyInjectAction.MINUS -> first - second
            ItemPropertyInjectAction.DIVIDE -> first / second
            ItemPropertyInjectAction.MULTIPLY -> first * second
            else -> first
        }
    }
}
