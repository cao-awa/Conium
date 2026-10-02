package com.github.cao.awa.conium.entity.setting

import com.github.cao.awa.conium.entity.renderer.ConiumEntityRenderer
import com.github.cao.awa.conium.entity.renderer.model.ConiumEntityModel
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.world.level.material.PushReaction
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.model.EntityModel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.LivingEntity
import net.minecraft.resources.Identifier

object ConiumEntitySettingsValue {
    /**
     * Default value of ``dimensions``.
     *
     * @see ConiumEntitySettings.dimensions
     * @see Entity.dimensions
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    val dimensions: EntityDimensions = EntityDimensions.scalable(0.0F, 0.0F)

    /**
     * Default value of ``pushable``.
     *
     * @see ConiumEntitySettings.pushable
     * @see Entity.isPushable
     * @see LivingEntity.isPushable
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    val pushable: Boolean = true

    /**
     * Default value of ``pushableByPiston``.
     *
     * @see ConiumEntitySettings.pushableByPiston
     * @see PushReaction
     * @see Entity.getPistonBehavior
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    val pushableByPiston: Boolean = true

    /**
     * Default value of ``pushableByFluids``.
     *
     * @see ConiumEntitySettings.pushableByFluids
     * @see Entity.isPushedByFluids
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    val pushableByFluids: Boolean = true
}

@Environment(EnvType.CLIENT)
object ConiumClientEntitySettingsValue {
    /**
     * Default value of ``clientModel``.
     *
     * @see EntityModel
     * @see ConiumEntityModel
     * @see ConiumEntityRenderer
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    @Environment(EnvType.CLIENT)
    val clientModel: (EntityRendererProvider.Context) -> ConiumEntityModel = { ConiumEntityModel.emptyModel }

    /**
     * Default value of ``clientModelTexture``.
     *
     * @see EntityModel
     * @see ConiumEntityModel
     * @see ConiumEntityRenderer
     * @see Identifier
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    @Environment(EnvType.CLIENT)
    val clientModelTexture: Identifier = Identifier.withDefaultNamespace("textures/misc/white.png")
}
