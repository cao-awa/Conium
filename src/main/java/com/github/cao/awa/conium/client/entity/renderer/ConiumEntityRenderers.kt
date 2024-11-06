package com.github.cao.awa.conium.client.entity.renderer

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.entity.EntityType

object ConiumEntityRenderers {
    @JvmField
    val renderers: MutableMap<EntityType<ConiumEntity>, EntityRendererFactory<ConiumEntity>> = CollectionFactor.hashMap()
}
