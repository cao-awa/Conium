package com.github.cao.awa.conium.item.template.destory

import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.CAN_DESTROY_IN_CREATIVE
import com.google.gson.JsonElement
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

open class ConiumCanDestroyInCreativeTemplate(private val canDestroy: Boolean) : ConiumItemTemplate(name = CAN_DESTROY_IN_CREATIVE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumCanDestroyInCreativeTemplate = ConiumCanDestroyInCreativeTemplate(element.asBoolean)

        fun createPredicate(template: ConiumCanDestroyInCreativeTemplate) = { _: ItemStack, _: BlockState, _: Level, _: BlockPos, entity: LivingEntity ->
            if (entity is Player) {
                !entity.isCreative
            }
            template.canDestroy
        }
    }

    override fun settings(settings: ConiumItemSettings) {
        settings.canMinePredicate = createPredicate(this)
    }
}
