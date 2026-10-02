package com.github.cao.awa.conium.block.template.replaceable

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.block.conium.ConiumBlockTemplates.REPLACEABLE
import com.google.gson.JsonElement
import net.minecraft.world.level.block.state.BlockBehaviour

open class ConiumBlockReplaceableTemplate(private val replaceable: Boolean, name: String = REPLACEABLE) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumBlockReplaceableTemplate = ConiumBlockReplaceableTemplate(element.asBoolean)
    }

    override fun settings(settings: BlockBehaviour.Properties) {
        // Set block replaceable.
        if (this.replaceable) {
            settings.replaceable()
        }
    }
}
