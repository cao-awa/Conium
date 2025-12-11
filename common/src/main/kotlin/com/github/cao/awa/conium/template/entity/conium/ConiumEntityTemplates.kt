package com.github.cao.awa.conium.template.entity.conium

import com.github.cao.awa.conium.entity.template.dimension.ConiumEntityDimensionTemplate
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
    const val MODEL: String = "model"
    const val PUSHABLE: String = "pushable"

    fun initEntityTemplates() {
        ConiumTemplate.registerEntity(
            DIMENSION,
            ConiumEntityDimensionTemplate::create
        )

        ConiumTemplate.registerEntity(
            MODEL,
            ConiumEntityModelTemplate::create
        )

        ConiumTemplate.registerEntity(
            PUSHABLE,
            ConiumEntityPushableTemplate::create
        )
    }
}