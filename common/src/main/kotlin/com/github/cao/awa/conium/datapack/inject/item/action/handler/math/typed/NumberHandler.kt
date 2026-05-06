package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction
import com.github.cao.awa.conium.extent.manipulate.cast
import com.github.cao.awa.translator.structuring.cast.Caster
import java.math.BigInteger

abstract class NumberHandler<T : Number> {
    companion object {
        private val handlers: Map<Class<out Number>, NumberHandler<out Number>> = HashMap<Class<out Number>, NumberHandler<out Number>>().also { handlers ->
            handlers[Int::class.java] = IntegerNumberHandler()
            handlers[Long::class.java] = LongNumberHandler()
            handlers[Float::class.java] = FloatNumberHandler()
            handlers[Double::class.java] = DoubleNumberHandler()
            handlers[BigInteger::class.java] = BigIntegerNumberHandler()
        }

        fun <X : Number> doHandles(first: X, second: X, action: ItemPropertyInjectAction): X {
            val firstClass: Class<X> = first.javaClass
            val secondClass: Class<X> = second.javaClass

            if (firstClass == secondClass && this.handlers.containsKey(firstClass)) {
                return this.handlers[firstClass]!!.doHandle(Caster.cast(first), Caster.cast(second), action).cast()
            } else {
                throw IllegalArgumentException("Unsupported number type: $firstClass and $secondClass")
            }
        }
    }

    abstract fun doHandle(first: T, second: T, action: ItemPropertyInjectAction): T
}
