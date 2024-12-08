@file:Suppress("unchecked_cast")

package com.github.cao.awa.conium.kotlin.extent.innate

inline fun <reified T> T.asIt(): T = this

inline fun <reified T> T.isIt(): Boolean = asIt() != null && T::class.java.isInstance(this)

inline fun <reified T> T.changeIfIs(value: T, creator: (T) -> T): T {
    if (value == this) {
        return creator(value)
    }
    return this
}

inline val Number.int: Int get() = toInt()
inline val Number.long: Long get() = toLong()
inline val Number.float: Float get() = toFloat()
inline val Number.double: Double get() = toDouble()

