package com.github.cao.awa.conium.block.template.piston

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.block.conium.ConiumBlockTemplates.PISTON_BEHAVIOR
import com.google.gson.JsonElement
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.PushReaction

open class ConiumBlockPistonBehaviorsTemplate(private val behavior: PushReaction, name: String = PISTON_BEHAVIOR) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumBlockPistonBehaviorsTemplate = ConiumBlockPistonBehaviorsTemplate(createBehaviors(element.asString))

        @JvmStatic
        fun createBehaviors(name: String): PushReaction {
            return when (name.lowercase()) {
                "normal" -> PushReaction.PUSH_PULL
                "destroy" -> PushReaction.POPPED
                "block" -> PushReaction.IMMOVEABLE
                "push_only" -> PushReaction.PUSH
                "ignore" -> throw IllegalArgumentException("Block cannot use piston behavior 'IGNORE'")
                else -> throw IllegalArgumentException("No piston behavior: '$name'")
            }
        }
    }

    override fun settings(settings: BlockBehaviour.Properties) {
        // Set piston behavior.
        settings.pushReaction(this.behavior)
    }
}
