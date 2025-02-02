package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction

class LongNumberHandler : NumberHandler<Long>() {
    override fun doHandle(first: Long, second: Long, action: ItemPropertyInjectAction): Long {
        return when (action) {
            ItemPropertyInjectAction.ADD -> first + second
            ItemPropertyInjectAction.MINUS -> first - second
            ItemPropertyInjectAction.DIVIDE -> first / second
            ItemPropertyInjectAction.MULTIPLY -> first * second
            else -> first
        }
    }
}
