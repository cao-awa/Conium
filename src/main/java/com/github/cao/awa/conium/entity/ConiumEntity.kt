package com.github.cao.awa.conium.entity

import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettingsWithTypeBuilder
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.kotlin.extent.entity.dimensions
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.world.World

class ConiumEntity(entityType: EntityType<ConiumEntity>, world: World, private val settings: ConiumEntitySettings) : Entity(entityType, world) {
    companion object {
        @JvmStatic
        fun createType(builder: ConiumEntityBuilder, settings: ConiumEntitySettingsWithTypeBuilder): EntityType.Builder<ConiumEntity> {
            builder.templates.forEach {
                it.prepare(settings)
            }

            return settings.builder
        }
    }

    init {
        // Do not use dimensions in entity type.
        this.dimensions = this.settings.dimensions
    }

    fun applyTemplates(templates: List<ConiumEntityTemplate>) {
        templates.forEach {
            it.attach(this)
        }

        templates.forEach {
            it.complete(this)
        }

        this.customName = Text.of("Test conium entity")
        this.isCustomNameVisible = true
    }

    override fun tick() {
        super.tick()
        this.boundingBox = calculateBoundingBox()
    }

    override fun isPushable(): Boolean = this.settings.pushable && super.isPushable()

    override fun initDataTracker(builder: DataTracker.Builder?) {

    }

    override fun damage(world: ServerWorld, source: DamageSource, amount: Float): Boolean = false

    override fun readCustomDataFromNbt(nbt: NbtCompound) {

    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {

    }
}
