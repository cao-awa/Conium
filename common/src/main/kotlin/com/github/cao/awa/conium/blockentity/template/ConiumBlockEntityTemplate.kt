package com.github.cao.awa.conium.blockentity.template

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.blockentity.setting.ConiumBlockEntitySettings
import com.github.cao.awa.conium.template.ConiumTemplate

abstract class ConiumBlockEntityTemplate(isClient: Boolean = false, name: String) : ConiumTemplate<ConiumBlock, ConiumBlockEntitySettings>(isClient, name) {
    override fun attach(target: ConiumBlock) {
        // Do nothing.
    }

    override fun complete(target: ConiumBlock) {
        // Do nothing.
    }

    override fun prepare(target: ConiumBlockEntitySettings) {
        settings(target)
    }

    // Do not call settings directly.
    // Use 'prepare'.
    open fun settings(settings: ConiumBlockEntitySettings) {
        // Do nothing.
    }
}
