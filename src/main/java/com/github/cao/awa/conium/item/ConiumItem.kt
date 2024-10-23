package com.github.cao.awa.conium.item

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.ConiumEventArgType
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.ToolMaterial
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult

class ConiumItem(settings: Settings) : Item(settings) {
    companion object {
        fun create(builder: ConiumItemBuilder): ConiumItem {
            val settings = Settings()

            builder.templates.forEach {
                it.settings(settings)
            }

            val item = ConiumItem(settings)

            builder.templates.forEach {
                it.attach(item)
            }

            builder.templates.forEach {
                it.complete(item)
            }

            return item
        }
    }

    var material: ToolMaterial? = null

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (context.world.isClient) {
            return ActionResult.PASS
        }

        ConiumEvent.request(ConiumEventType.ITEM_USE_ON_BLOCK).let {
            it.put(ConiumEventArgType.SERVER_WORLD, context.world as ServerWorld)
            it.put(ConiumEventArgType.ITEM_USAGE_CONTEXT, context)

            if (it.fire(this)) {
                return ActionResult.SUCCESS
            }
        }
        return ActionResult.PASS
    }


    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        return this.material != null
    }

    override fun postDamageEntity(stack: ItemStack, target: LivingEntity?, attacker: LivingEntity?) {
        if (this.material != null) {
            stack.damage(2, attacker, EquipmentSlot.MAINHAND)
        }
    }

    override fun getEnchantability(): Int {
        if (this.material == null) {
            return 0
        }
        return this.material!!.enchantability
    }

    override fun canRepair(stack: ItemStack?, ingredient: ItemStack?): Boolean {
        if (this.material == null) {
            return false
        }
        return this.material!!.repairIngredient.test(ingredient) || super.canRepair(stack, ingredient)
    }
}
