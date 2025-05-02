package com.github.cao.awa.conium.item.template.bedrock.destory

import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrBoolean
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.CAN_DESTROY_IN_CREATIVE
import com.google.gson.JsonElement
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumBedrockCanDestroyInCreativeTemplate(private val canDestroy: Boolean) : ConiumItemTemplate(name = CAN_DESTROY_IN_CREATIVE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumBedrockCanDestroyInCreativeTemplate = element.objectOrBoolean(
            {
                // Bedrock schema is:
                // "minecraft:can_destroy_in_creative": {
                //     "value": <bool>
                // }
                ConiumBedrockCanDestroyInCreativeTemplate(it["value"].asBoolean)
            },
            // Conium additional supporting schema:
            // "minecraft:can_destroy_in_creative": <bool>
            ::ConiumBedrockCanDestroyInCreativeTemplate
        )!!
    }

    override fun settings(settings: ConiumItemSettings) {
        settings.canMinePredicate = { _: Item, _: BlockState, _: World, _: BlockPos, player: PlayerEntity -> !player.isCreative || this.canDestroy }
    }
}
