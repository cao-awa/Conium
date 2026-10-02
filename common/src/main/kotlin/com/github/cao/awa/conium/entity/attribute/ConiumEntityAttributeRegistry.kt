package com.github.cao.awa.conium.entity.attribute

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import java.util.HashMap

object ConiumEntityAttributeRegistry {
    @JvmField
    val attributes: MutableMap<EntityType<out LivingEntity>, AttributeSupplier> =  HashMap()

    @JvmStatic
    fun resetAttributes() = this.attributes.clear()
}
