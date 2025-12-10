package com.github.cao.awa.conium.template

import com.github.cao.awa.conium.template.block.bedrock.BedrockBlockComponents
import com.github.cao.awa.conium.template.block.conium.ConiumBlockTemplates
import com.github.cao.awa.conium.template.blockentity.conium.ConiumBlockEntityTemplates
import com.github.cao.awa.conium.template.entity.bedrock.BedrockEntityComponents
import com.github.cao.awa.conium.template.entity.conium.ConiumEntityTemplates
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates
import com.github.cao.awa.conium.template.recipe.bedrock.BedrockRecipeComponents

object ConiumTemplates {
    fun init() {
        // Conium templates.
        ConiumItemTemplates.initItemTemplates()
        ConiumBlockTemplates.initBlockTemplates()
        ConiumBlockEntityTemplates.initBlockEntityTemplates()
        ConiumEntityTemplates.initEntityTemplates()

        // Bedrock components (by 'template' way).
        BedrockItemComponents.initBedrockItemComponents()
        BedrockBlockComponents.initBedrockBlockComponents()
        BedrockEntityComponents.initBedrockEntityComponents()
        BedrockRecipeComponents.initBedrockRecipeComponents()
    }
}
