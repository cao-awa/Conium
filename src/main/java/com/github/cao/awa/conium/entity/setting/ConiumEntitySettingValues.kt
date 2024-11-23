package com.github.cao.awa.conium.entity.setting

import com.github.cao.awa.conium.entity.renderer.ConiumEntityRenderer
import com.github.cao.awa.conium.entity.renderer.model.ConiumEntityModel
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.LivingEntity
import net.minecraft.util.Identifier

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
    val dimensions: EntityDimensions = EntityDimensions.changing(0.0F, 0.0F)

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
     * @see PistonBehavior
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
     * @see EntityRenderer
     * @see ConiumEntityRenderer
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    @Environment(EnvType.CLIENT)
    val clientModel: (EntityRendererFactory.Context) -> ConiumEntityModel = { ConiumEntityModel.emptyModel }

    /**
     * Default value of ``clientModelTexture``.
     *
     * @see EntityModel
     * @see ConiumEntityModel
     * @see EntityRenderer
     * @see ConiumEntityRenderer
     * @see Identifier
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    @Environment(EnvType.CLIENT)
    val clientModelTexture: Identifier = Identifier.ofVanilla("textures/misc/white.png")
}