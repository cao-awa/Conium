package com.github.cao.awa.conium.datapack.inject.item.action.handler.set

import com.github.cao.awa.conium.datapack.inject.item.action.handler.ItemPropertyInjectHandler

class ItemPropertyInjectSetHandler<T> : ItemPropertyInjectHandler<T>() {
    override fun doHandle(source: T, value: T): T = value
}
