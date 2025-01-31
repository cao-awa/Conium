package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction
import java.math.BigInteger

class BigIntegerNumberHandler : NumberHandler<BigInteger>() {
    override fun doHandle(first: BigInteger, second: BigInteger, action: ItemPropertyInjectAction): BigInteger {
        return when (action) {
            ItemPropertyInjectAction.ADD -> first.add(second)
            ItemPropertyInjectAction.MINUS -> first.subtract(second)
            ItemPropertyInjectAction.DIVIDE -> first.divide(second)
            ItemPropertyInjectAction.MULTIPLY -> first.multiply(second)
            else -> first
        }
    }
}
