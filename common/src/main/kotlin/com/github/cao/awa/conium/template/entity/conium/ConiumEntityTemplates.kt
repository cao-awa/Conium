package com.github.cao.awa.conium.template.entity.conium

import com.github.cao.awa.conium.entity.template.dimension.ConiumEntityDimensionTemplate
import com.github.cao.awa.conium.entity.template.fire.ConiumEntityFireImmuneTemplate
import com.github.cao.awa.conium.entity.template.health.ConiumEntityHealthTemplate
import com.github.cao.awa.conium.entity.template.knockback.ConiumEntityKnockbackResistanceTemplate
import com.github.cao.awa.conium.entity.template.movement.ConiumEntityMovementTemplate
import com.github.cao.awa.conium.entity.template.pushable.ConiumEntityPushableTemplate
import com.github.cao.awa.conium.entity.template.renderer.model.ConiumEntityModelTemplate
import com.github.cao.awa.conium.template.ConiumTemplate

/**
 * Conium entity templates register.
 *
 * Ordering with alphabet order.
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
object ConiumEntityTemplates {
    const val DIMENSION: String = "dimension"
    const val FIRE_IMMUNE: String = "fire_immune"
    const val HEALTH: String = "health"
    const val KNOCKBACK_RESISTANCE: String = "knockback_resistance"
    const val MODEL: String = "model"
    const val MOVEMENT: String = "movement"
    const val MOVEMENT_SPEED: String = "movement_speed"
    const val PUSHABLE: String = "pushable"

    fun initEntityTemplates() {
        ConiumTemplate.registerEntity(
            DIMENSION,
            ConiumEntityDimensionTemplate::create
        )

        ConiumTemplate.registerEntity(
            FIRE_IMMUNE,
            ConiumEntityFireImmuneTemplate::create
        )

        ConiumTemplate.registerEntity(
            HEALTH,
            ConiumEntityHealthTemplate::create
        )

        ConiumTemplate.registerEntity(
            KNOCKBACK_RESISTANCE,
            ConiumEntityKnockbackResistanceTemplate::create
        )

        ConiumTemplate.registerEntity(
            MODEL,
            ConiumEntityModelTemplate::create
        )

        ConiumTemplate.registerEntity(
            MOVEMENT,
            ConiumEntityMovementTemplate::create
        )

        ConiumTemplate.registerEntity(
            MOVEMENT_SPEED,
            ConiumEntityMovementTemplate::create
        )

        ConiumTemplate.registerEntity(
            PUSHABLE,
            ConiumEntityPushableTemplate::create
        )
    }
}