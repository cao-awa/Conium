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

inline val Any?.bool: Boolean get() = this as Boolean
inline val Number?.int: Int get() = this as Int
inline val Number?.long: Long get() = this as Long
inline val Number?.float: Float get() = this as Float
inline val Number?.double: Double get() = this as Double

