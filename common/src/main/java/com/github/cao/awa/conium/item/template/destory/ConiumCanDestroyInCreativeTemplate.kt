package com.github.cao.awa.conium.item.template.destory

import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Item.CAN_DESTROY_IN_CREATIVE
import com.google.gson.JsonElement
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumCanDestroyInCreativeTemplate(private val canDestroy: Boolean) : ConiumItemTemplate(name = CAN_DESTROY_IN_CREATIVE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumCanDestroyInCreativeTemplate = ConiumCanDestroyInCreativeTemplate(element.asBoolean)
    }

    override fun settings(settings: ConiumItemSettings) {
        settings.canMinePredicate = { _: Item, _: BlockState, _: World, _: BlockPos, player: PlayerEntity -> this.canDestroy || !player.isCreative }
    }
}
