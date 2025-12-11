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

/**
 * Conium block templates register.
 *
 * Ordering with alphabet order.
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
object ConiumBlockTemplates {
    const val BLOCK_ENTITY_PRESETS: String = "block_entity_presets"
    const val COLLISION: String = "collision"
    const val DATA: String = "data"
    const val ENABLE_BLOCK_ENTITY: String = "enable_block_entity"
    const val EMITS_REDSTONE_POWER: String = "emits_redstone_power"
    const val EMITS_STRONG_REDSTONE_POWER: String = "emits_strong_redstone_power"
    const val EMITS_WEAK_REDSTONE_POWER: String = "emits_weak_redstone_power"
    const val EXPLOSION_RESISTANCE: String = "explosion_resistance"
    const val HARDNESS: String = "hardness"
    const val INSTRUMENT: String = "instrument"
    const val JUMP_VELOCITY: String = "jump_velocity"
    const val LUMINANCE: String = "luminance"
    const val MAP_COLOR: String = "map_color"
    const val MOVEMENT_VELOCITY: String = "movement_velocity"
    const val PATH_FIND_THROUGH: String = "path_find_through"
    const val PISTON_BEHAVIOR: String = "piston_behavior"
    const val REPLACEABLE: String = "replaceable"
    const val WALK_VELOCITY: String = "walk_velocity"

    fun initBlockTemplates() {
        ConiumTemplate.registerBlock(
            BLOCK_ENTITY_PRESETS,
            ConiumBlockEntityPresetsTemplate::create
        )

        ConiumTemplate.registerBlock(
            COLLISION,
            ConiumBlockCollisionTemplate::create
        )

        ConiumTemplate.registerBlock(
            DATA,
            ConiumBlockDataTemplate::create
        )

        ConiumTemplate.registerBlock(
            ENABLE_BLOCK_ENTITY,
            ConiumEnableBlockEntityTemplate::create
        )

        ConiumTemplate.registerBlock(
            EMITS_REDSTONE_POWER,
            ConiumBlockEmitsRedstonePowerTemplate::create
        )

        ConiumTemplate.registerBlock(
            EMITS_STRONG_REDSTONE_POWER,
            ConiumBlockEmitsStrongRedstonePowerTemplate::create
        )

        ConiumTemplate.registerBlock(
            EMITS_WEAK_REDSTONE_POWER,
            ConiumBlockEmitsWeakRedstonePowerTemplate::create
        )

        ConiumTemplate.registerBlock(
            EXPLOSION_RESISTANCE,
            ConiumExplosionResistanceTemplate::create
        )

        ConiumTemplate.registerBlock(
            HARDNESS,
            ConiumHardnessTemplate::create
        )

        ConiumTemplate.registerBlock(
            INSTRUMENT,
            ConiumBlockInstrumentTemplate::create
        )

        ConiumTemplate.registerBlock(
            JUMP_VELOCITY,
            ConiumBlockJumpVelocityTemplate::create
        )

        ConiumTemplate.registerBlock(
            LUMINANCE,
            ConiumLuminanceTemplate::create
        )

        ConiumTemplate.registerBlock(
            MAP_COLOR,
            ConiumMapColorTemplate::create
        )

        ConiumTemplate.registerBlock(
            MOVEMENT_VELOCITY,
            ConiumBlockMovementVelocityTemplate::create
        )

        ConiumTemplate.registerBlock(
            PATH_FIND_THROUGH,
            ConiumBlockPathFindThroughTemplate::create
        )

        ConiumTemplate.registerBlock(
            PISTON_BEHAVIOR,
            ConiumBlockPistonBehaviorsTemplate::create
        )

        ConiumTemplate.registerBlock(
            REPLACEABLE,
            ConiumBlockReplaceableTemplate::create
        )

        ConiumTemplate.registerBlock(
            WALK_VELOCITY,
            ConiumBlockWalkVelocityTemplate::create
        )
    }
}