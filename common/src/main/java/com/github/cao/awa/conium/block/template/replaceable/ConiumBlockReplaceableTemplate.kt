package com.github.cao.awa.conium.block.template.replaceable

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Block.REPLACEABLE
import com.google.gson.JsonElement
import net.minecraft.block.AbstractBlock
import net.minecraft.registry.RegistryWrapper

open class ConiumBlockReplaceableTemplate(private val replaceable: Boolean, name: String = REPLACEABLE) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumBlockReplaceableTemplate = ConiumBlockReplaceableTemplate(element.asBoolean)
    }

    override fun settings(settings: AbstractBlock.Settings) {
        // Set block replaceable.
        if (this.replaceable) {
            settings.replaceable()
        }
    }
}
