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

inline fun <reified T, reified R> T?.orGet(value: R, op: (T) -> R): R {
    if (this == null) {
        return value
    }
    return op(this)
}

inline fun <reified T, reified R> T?.orGetAuto(value: R, op: (T) -> Any?): R {
    if (this == null) {
        return value
    }
    return op(this) as R
}

inline val Number.int: Int get() = toInt()
inline val Number.long: Long get() = toLong()
inline val Number.float: Float get() = toFloat()
inline val Number.double: Double get() = toDouble()
inline val Number.char: Char get() = this.int.toChar()
inline val Number.byte: Byte get() = toByte()