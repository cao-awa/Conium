package com.github.cao.awa.conium.item.template.bedrock.destory

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrBoolean
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.CAN_DESTROY_IN_CREATIVE
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.math.BlockPos
import net.minecraft.item.Item
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.world.World

class ConiumBedrockCanDestroyInCreativeTemplate(private val canDestroy: Boolean) : ConiumItemTemplate(name = CAN_DESTROY_IN_CREATIVE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockCanDestroyInCreativeTemplate = element.objectOrBoolean(
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

    override fun complete(target: ConiumItem) {
        target.canMinePredicate = { _: Item, _: BlockState, _: World, _: BlockPos, player: PlayerEntity -> !player.isCreative || this.canDestroy }
    }
}
