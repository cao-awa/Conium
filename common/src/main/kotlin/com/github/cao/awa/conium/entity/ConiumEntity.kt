package com.github.cao.awa.conium.entity

import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettingsWithTypeBuilder
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.kotlin.extent.entity.dimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.HumanoidArm
import net.minecraft.world.item.ItemStack
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.world.level.Level

class ConiumEntity(entityType: EntityType<ConiumEntity>, world: Level, private val settings: ConiumEntitySettings) : LivingEntity(entityType, world) {
    companion object {
        @JvmStatic
        fun createType(builder: ConiumEntityBuilder, settings: ConiumEntitySettingsWithTypeBuilder): EntityType.Builder<ConiumEntity> {
            builder.distinct()

            builder.forEachTemplate { it.prepare(settings) }

            return settings.builder
        }
    }

    init {
        // Do not use dimensions in entity type (entityType.dimensions),
        // use dimensions in conium settings.
        this.dimensions = this.settings.dimensions
    }

    fun applyTemplates(templates: List<ConiumEntityTemplate>) {
        templates.forEach { it.attach(this) }

        templates.forEach { it.complete(this) }

        this.customName = Component.literal("Test conium entity(${BuiltInRegistries.ENTITY_TYPE.getKey(this.type)})")
        this.isCustomNameVisible = true
    }

    override fun tick() {
        super.tick()
        this.boundingBox = makeBoundingBox()
    }

    override fun isPushable(): Boolean = this.settings.pushable && super.isPushable()

    override fun getItemBySlot(slot: EquipmentSlot): ItemStack = ItemStack.EMPTY

    override fun setItemSlot(slot: EquipmentSlot, stack: ItemStack) {
        // TODO equip stack.
    }

    override fun getMainArm(): HumanoidArm = HumanoidArm.RIGHT
}
