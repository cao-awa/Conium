package com.github.cao.awa.conium.datapack.inject.item.action.handler.math.typed

import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import java.math.BigInteger

abstract class NumberHandler<T : Number> {
    companion object {
        private val handlers: Map<Class<out Number>, NumberHandler<out Number>> = Manipulate.make(CollectionFactor.hashMap()) { handlers: HashMap<Class<out Number>, NumberHandler<out Number>> ->
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
                return Manipulate.cast(this.handlers[firstClass]!!.doHandle(Manipulate.cast(first), Manipulate.cast(second), action))
            } else {
                throw IllegalArgumentException("Unsupported number type: $firstClass and $secondClass")
            }
        }
    }

    abstract fun doHandle(first: T, second: T, action: ItemPropertyInjectAction): T
}
