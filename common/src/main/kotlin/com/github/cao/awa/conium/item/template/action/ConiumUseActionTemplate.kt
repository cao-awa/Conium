package com.github.cao.awa.conium.item.template.action

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.component.withComponentProvides
import com.github.cao.awa.conium.kotlin.extent.component.withComputeUseAction
import com.github.cao.awa.conium.kotlin.extent.component.withCreateConsumable
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.USE_ACTION
import com.google.gson.JsonElement
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.consume.UseAction
import com.github.cao.awa.conium.item.template.bedrock.animation.BedrockUseAnimationComponent

/**
 * This template used to setting a animation action when the item using.
 *
 * @see BedrockUseAnimationComponent
 *
 * @author cao_awa
 * @author草二号机
 *
 * @since 1.0.0
 */
class ConiumUseActionTemplate(private val useAction: UseAction) : ConiumItemTemplate(name = USE_ACTION) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumUseActionTemplate = ConiumUseActionTemplate(createUseAction(element.asString))
    }

    override fun settings(settings: Item.Settings) {
        // Set use action.
        settings.components.withComponentProvides(
            DataComponentTypes.CONSUMABLE,
            withCreateConsumable(),
            withComputeUseAction(),
            this::useAction
        )
    }

    override fun complete(target: ConiumItem) {
        // Set use action.
        target.useAction = this.useAction
    }
}
