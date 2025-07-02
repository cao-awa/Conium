package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction

class IntegerNumberHandler : NumberHandler<Int>() {
    override fun doHandle(first: Int, second: Int, action: ItemPropertyInjectAction): Int {
        return when (action) {
            ItemPropertyInjectAction.ADD -> first + second
            ItemPropertyInjectAction.MINUS -> first - second
            ItemPropertyInjectAction.DIVIDE -> first / second
            ItemPropertyInjectAction.MULTIPLY -> first * second
            else -> first
        }
    }
}
