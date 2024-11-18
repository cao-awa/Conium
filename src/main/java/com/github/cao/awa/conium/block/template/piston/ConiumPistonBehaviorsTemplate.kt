package com.github.cao.awa.conium.block.template.piston

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Block.PISTON_BEHAVIOR
import com.google.gson.JsonElement
import net.minecraft.block.AbstractBlock
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.registry.RegistryWrapper.WrapperLookup

open class ConiumPistonBehaviorsTemplate(private val behavior: PistonBehavior, name: String = PISTON_BEHAVIOR) : ConiumBlockTemplate(name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumPistonBehaviorsTemplate = ConiumPistonBehaviorsTemplate(createBehaviors(element.asString))

        @JvmStatic
        fun createBehaviors(name: String): PistonBehavior {
            return when (name.lowercase()) {
                "normal" -> PistonBehavior.NORMAL
                "destroy" -> PistonBehavior.DESTROY
                "block" -> PistonBehavior.BLOCK
                "push_only" -> PistonBehavior.PUSH_ONLY
                "ignore" -> throw IllegalArgumentException("Block cannot use piston behavior 'IGNORE'")
                else -> throw IllegalArgumentException("No piston behavior: '$name'")
            }
        }
    }

    override fun settings(settings: AbstractBlock.Settings) {
        // Set piston behavior.
        settings.pistonBehavior(this.behavior)
    }
}
