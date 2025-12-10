package com.github.cao.awa.conium.template.block.conium

import com.github.cao.awa.conium.block.template.collision.ConiumBlockCollisionTemplate
import com.github.cao.awa.conium.block.template.data.ConiumBlockDataTemplate
import com.github.cao.awa.conium.block.template.entity.ConiumEnableBlockEntityTemplate
import com.github.cao.awa.conium.block.template.explosion.resistance.ConiumExplosionResistanceTemplate
import com.github.cao.awa.conium.block.template.instrument.ConiumBlockInstrumentTemplate
import com.github.cao.awa.conium.block.template.luminance.ConiumLuminanceTemplate
import com.github.cao.awa.conium.block.template.map.ConiumMapColorTemplate
import com.github.cao.awa.conium.block.template.mining.ConiumHardnessTemplate
import com.github.cao.awa.conium.block.template.path.through.ConiumBlockPathFindThroughTemplate
import com.github.cao.awa.conium.block.template.piston.ConiumBlockPistonBehaviorsTemplate
import com.github.cao.awa.conium.block.template.preset.ConiumBlockEntityPresetsTemplate
import com.github.cao.awa.conium.block.template.redstone.ConiumBlockEmitsRedstonePowerTemplate
import com.github.cao.awa.conium.block.template.redstone.ConiumBlockEmitsStrongRedstonePowerTemplate
import com.github.cao.awa.conium.block.template.redstone.ConiumBlockEmitsWeakRedstonePowerTemplate
import com.github.cao.awa.conium.block.template.replaceable.ConiumBlockReplaceableTemplate
import com.github.cao.awa.conium.block.template.velocity.ConiumBlockMovementVelocityTemplate
import com.github.cao.awa.conium.block.template.velocity.jump.ConiumBlockJumpVelocityTemplate
import com.github.cao.awa.conium.block.template.velocity.walk.ConiumBlockWalkVelocityTemplate
import com.github.cao.awa.conium.template.ConiumTemplate

object ConiumBlockTemplates {
    // Destructible.
    const val EXPLOSION_RESISTANCE: String = "explosion_resistance"
    const val HARDNESS: String = "hardness"

    // Map color.
    const val MAP_COLOR: String = "map_color"

    // Luminance.
    const val LUMINANCE: String = "luminance"

    // Collision.
    const val COLLISION: String = "collision"

    // Replaceable.
    const val REPLACEABLE: String = "replaceable"

    // Velocities.
    const val MOVEMENT_VELOCITY: String = "movement_velocity"
    const val WALK_VELOCITY: String = "walk_velocity"
    const val JUMP_VELOCITY: String = "jump_velocity"

    // Piston behavior.
    const val PISTON_BEHAVIOR: String = "piston_behavior"

    // Note block instrument.
    const val INSTRUMENT: String = "instrument"

    // Path find.
    const val PATH_FIND_THROUGH: String = "path_find_through"

    // Block entity.
    const val ENABLE_BLOCK_ENTITY: String = "enable_block_entity"

    // Block data.
    const val DATA: String = "data"

    // Block entity presets.
    const val BLOCK_ENTITY_PRESETS: String = "block_entity_presets"

    // Emits redstone power.
    const val EMITS_REDSTONE_POWER: String = "emits_redstone_power"
    const val EMITS_WEAK_REDSTONE_POWER: String = "emits_weak_redstone_power"
    const val EMITS_STRONG_REDSTONE_POWER: String = "emits_strong_redstone_power"

    fun initBlockTemplates() {
        // Destructible.
        ConiumTemplate.registerBlock(
            EXPLOSION_RESISTANCE,
            ConiumExplosionResistanceTemplate::create
        )
        ConiumTemplate.registerBlock(
            HARDNESS,
            ConiumHardnessTemplate::create
        )

        // Map color.
        ConiumTemplate.registerBlock(
            MAP_COLOR,
            ConiumMapColorTemplate::create
        )

        // Luminance.
        ConiumTemplate.registerBlock(
            LUMINANCE,
            ConiumLuminanceTemplate::create
        )

        // Collision.
        ConiumTemplate.registerBlock(
            COLLISION,
            ConiumBlockCollisionTemplate::create
        )

        // Replaceable.
        ConiumTemplate.registerBlock(
            REPLACEABLE,
            ConiumBlockReplaceableTemplate::create
        )

        // Movement velocity multiplier.
        ConiumTemplate.registerBlock(
            MOVEMENT_VELOCITY,
            ConiumBlockMovementVelocityTemplate::create
        )
        ConiumTemplate.registerBlock(
            WALK_VELOCITY,
            ConiumBlockWalkVelocityTemplate::create
        )
        ConiumTemplate.registerBlock(
            JUMP_VELOCITY,
            ConiumBlockJumpVelocityTemplate::create
        )

        // Piston behavior.
        ConiumTemplate.registerBlock(
            PISTON_BEHAVIOR,
            ConiumBlockPistonBehaviorsTemplate::create
        )

        // Note block instrument.
        ConiumTemplate.registerBlock(
            INSTRUMENT,
            ConiumBlockInstrumentTemplate::create
        )

        // Path through.
        ConiumTemplate.registerBlock(
            PATH_FIND_THROUGH,
            ConiumBlockPathFindThroughTemplate::create
        )

        // Block entity.
        ConiumTemplate.registerBlock(
            ENABLE_BLOCK_ENTITY,
            ConiumEnableBlockEntityTemplate::create
        )

        // Block data.
        ConiumTemplate.registerBlock(
            DATA,
            ConiumBlockDataTemplate::create
        )

        // Block entity presets.
        ConiumTemplate.registerBlock(
            BLOCK_ENTITY_PRESETS,
            ConiumBlockEntityPresetsTemplate::create
        )

        // Emits redstone power.
        ConiumTemplate.registerBlock(
            EMITS_REDSTONE_POWER,
            ConiumBlockEmitsRedstonePowerTemplate::create
        )
        ConiumTemplate.registerBlock(
            EMITS_WEAK_REDSTONE_POWER,
            ConiumBlockEmitsWeakRedstonePowerTemplate::create
        )
        ConiumTemplate.registerBlock(
            EMITS_STRONG_REDSTONE_POWER,
            ConiumBlockEmitsStrongRedstonePowerTemplate::create
        )
    }
}