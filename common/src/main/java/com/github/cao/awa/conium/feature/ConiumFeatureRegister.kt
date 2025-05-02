package com.github.cao.awa.conium.feature

import net.minecraft.util.Identifier

abstract class ConiumFeatureRegister {
    companion object {
        @JvmField
        var IMPL: ConiumFeatureRegister = object : ConiumFeatureRegister() {
            override fun placedFeature(id: Identifier?) {
                // Nothing here.
            }
        }
    }

    abstract fun placedFeature(id: Identifier?)
}