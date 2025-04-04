package com.github.cao.awa.conium.datapack.inject.item.action.handler.math

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction
import com.github.cao.awa.conium.datapack.inject.item.action.handler.ItemPropertyInjectHandler
import com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed.NumberHandler

class ItemPropertyInjectMinusHandler<T : Number?> : ItemPropertyInjectHandler<T>() {
    override fun doHandle(source: T, value: T): T {
        if (source is Number && value is Number) {
            return NumberHandler.doHandles(source, value, ItemPropertyInjectAction.MINUS)
        }
        return source
    }
}
