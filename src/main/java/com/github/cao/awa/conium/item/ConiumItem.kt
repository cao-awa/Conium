package com.github.cao.awa.conium.item

import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial

class ConiumItem(settings: Settings) : Item(settings) {
    companion object {
        fun create(builder: ConiumItemBuilder, settings: Settings): ConiumItem {
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

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        return this.material != null
    }

    override fun postDamageEntity(stack: ItemStack, target: LivingEntity?, attacker: LivingEntity?) {
        if (this.material != null) {
            stack.damage(2, attacker, EquipmentSlot.MAINHAND)
        }
    }
}
