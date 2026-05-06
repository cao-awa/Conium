package com.github.cao.awa.conium.entity.attribute

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import java.util.HashMap

object ConiumEntityAttributeRegistry {
    @JvmField
    val attributes: MutableMap<EntityType<out LivingEntity>, DefaultAttributeContainer> =  HashMap()

    @JvmStatic
    fun resetAttributes() = this.attributes.clear()
}
