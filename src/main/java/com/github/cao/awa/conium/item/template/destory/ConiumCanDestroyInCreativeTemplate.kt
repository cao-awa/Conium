package com.github.cao.awa.conium.item.template.destory

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Item.CAN_DESTROY_IN_CREATIVE
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.item.Item
import net.minecraft.block.BlockState
import net.minecraft.world.World
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos

class ConiumCanDestroyInCreativeTemplate(private val canDestroy: Boolean) : ConiumItemTemplate(name = CAN_DESTROY_IN_CREATIVE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumCanDestroyInCreativeTemplate = ConiumCanDestroyInCreativeTemplate(element.asBoolean)
    }

    override fun complete(target: ConiumItem) {
        target.canMinePredicate = { _: Item, _: BlockState, _: World, _: BlockPos, player: PlayerEntity -> !player.isCreative || this.canDestroy }
    }
}
