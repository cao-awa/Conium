@file:Suppress("unchecked_cast")

package com.github.cao.awa.conium.kotlin.extent.innate

inline fun <reified T> T.asIt(): T = this

inline fun <reified T> T.isIt(): Boolean {
    return asIt() != null && T::class.java.isInstance(this)
}
