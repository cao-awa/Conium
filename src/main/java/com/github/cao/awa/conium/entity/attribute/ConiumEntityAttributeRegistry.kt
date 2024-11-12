package com.github.cao.awa.conium.entity.attribute

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer

object ConiumEntityAttributeRegistry {
    @JvmField
    val attributes: MutableMap<EntityType<out LivingEntity>, DefaultAttributeContainer> = CollectionFactor.hashMap()

    @JvmStatic
    fun resetAttributes() {
        this.attributes.clear()
    }
}
