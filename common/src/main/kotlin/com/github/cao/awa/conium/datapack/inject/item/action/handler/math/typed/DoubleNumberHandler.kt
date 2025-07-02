package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction

class DoubleNumberHandler : NumberHandler<Double>() {
    override fun doHandle(first: Double, second: Double, action: ItemPropertyInjectAction): Double {
        return when (action) {
            ItemPropertyInjectAction.ADD -> first + second
            ItemPropertyInjectAction.MINUS -> first - second
            ItemPropertyInjectAction.DIVIDE -> first / second
            ItemPropertyInjectAction.MULTIPLY -> first * second
            else -> first
        }
    }
}
