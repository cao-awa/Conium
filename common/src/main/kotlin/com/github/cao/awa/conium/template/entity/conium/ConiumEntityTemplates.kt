package com.github.cao.awa.conium.template.entity.conium

import com.github.cao.awa.conium.entity.template.dimension.ConiumEntityDimensionTemplate
import com.github.cao.awa.conium.entity.template.pushable.ConiumEntityPushableTemplate
import com.github.cao.awa.conium.entity.template.renderer.model.ConiumEntityModelTemplate
import com.github.cao.awa.conium.template.ConiumTemplate

object ConiumEntityTemplates {
    // Dimension.
    const val DIMENSION: String = "dimension"

    // Pushable.
    const val PUSHABLE: String = "pushable"

    // Model.
    const val MODEL: String = "model"

    fun initEntityTemplates() {
        // Dimension.
        ConiumTemplate.registerEntity(
            DIMENSION,
            ConiumEntityDimensionTemplate::create
        )

        // Pushable.
        ConiumTemplate.registerEntity(
            PUSHABLE,
            ConiumEntityPushableTemplate::create
        )

        // Model.
        ConiumTemplate.registerEntity(
            MODEL,
            ConiumEntityModelTemplate::create
        )
    }
}