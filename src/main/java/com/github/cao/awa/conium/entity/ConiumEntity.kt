package com.github.cao.awa.conium.entity

import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettingsWithTypeBuilder
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.kotlin.extent.entity.dimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.text.Text
import net.minecraft.util.Arm
import net.minecraft.world.World
import java.util.*

class ConiumEntity(entityType: EntityType<ConiumEntity>, world: World, private val settings: ConiumEntitySettings) : LivingEntity(entityType, world) {
    companion object {
        @JvmStatic
        fun createType(builder: ConiumEntityBuilder, settings: ConiumEntitySettingsWithTypeBuilder): EntityType.Builder<ConiumEntity> {
            builder.distinct()

            builder.forEachTemplate { it.prepare(settings) }

            return settings.builder
        }
    }

    init {
        // Do not use dimensions in entity type.
        this.dimensions = this.settings.dimensions
    }

    fun applyTemplates(templates: List<ConiumEntityTemplate>) {
        templates.forEach { it.attach(this) }

        templates.forEach { it.complete(this) }

        this.customName = Text.of("Test conium entity(${Registries.ENTITY_TYPE.getId(this.type)})")
        this.isCustomNameVisible = true
    }

    override fun tick() {
        super.tick()
        this.boundingBox = calculateBoundingBox()
    }

    override fun isPushable(): Boolean = this.settings.pushable && super.isPushable()

//    override fun damage(world: ServerWorld, source: DamageSource, amount: Float): Boolean = false

    override fun getArmorItems(): MutableIterable<ItemStack> = Collections.emptySet()

    override fun getEquippedStack(slot: EquipmentSlot): ItemStack = ItemStack.EMPTY

    override fun equipStack(slot: EquipmentSlot, stack: ItemStack) {
        // TODO equip stack.
    }

    override fun getMainArm(): Arm = Arm.RIGHT
}
