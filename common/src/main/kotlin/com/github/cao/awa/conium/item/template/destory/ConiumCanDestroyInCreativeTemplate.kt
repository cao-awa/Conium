package com.github.cao.awa.conium.item.template.destory

import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Item.CAN_DESTROY_IN_CREATIVE
import com.google.gson.JsonElement
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class ConiumCanDestroyInCreativeTemplate(private val canDestroy: Boolean) : ConiumItemTemplate(name = CAN_DESTROY_IN_CREATIVE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumCanDestroyInCreativeTemplate = ConiumCanDestroyInCreativeTemplate(element.asBoolean)

        fun createPredicate(template: ConiumCanDestroyInCreativeTemplate) = { _: ItemStack, _: BlockState, _: World, _: BlockPos, entity: LivingEntity ->
            if (entity is PlayerEntity) {
                !entity.isCreative
            }
            template.canDestroy
        }
    }

    override fun settings(settings: ConiumItemSettings) {
        settings.canMinePredicate = createPredicate(this)
    }
}
