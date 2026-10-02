package com.github.cao.awa.conium.client.entity.renderer

import com.github.cao.awa.conium.entity.ConiumEntity
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.world.entity.EntityType

@Environment(EnvType.CLIENT)
object ConiumEntityRenderers {
    @JvmField
    val renderers: MutableMap<EntityType<ConiumEntity>, EntityRendererProvider<ConiumEntity>> = HashMap()

    @JvmStatic
    fun clearRenderers() = this.renderers.clear()
}
