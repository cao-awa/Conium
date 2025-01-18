package com.github.cao.awa.conium.block.template.entity

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.entity.ConiumBlockEntity
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.RegistryWrapper

class ConiumEnableBlockEntityTemplate(private val identifier: String): ConiumBlockTemplate(name = ConiumTemplates.Block.ENABLE_BLOCK_ENTITY) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumEnableBlockEntityTemplate = ConiumEnableBlockEntityTemplate(element.asString)
    }

    override fun settings(settings: ConiumBlockSettings) {
        settings.enableBlockEntity = true
    }

    override fun complete(target: ConiumBlock) {
        target.setting.blockEntity.let { blockEntitySettings ->
            blockEntitySettings.type = BlockEntityType.create(
                this.identifier,
                { pos, state ->
                    ConiumBlockEntity(
                        blockEntitySettings,
                        pos,
                        state
                    )
                },
                target
            )
        }
    }
}
