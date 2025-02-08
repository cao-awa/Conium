@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import com.github.cao.awa.conium.mapping.yarn.*

/**
 * See the mapping [Block](https://mappings.dev/1.21.4/net/minecraft/world/level/block/Block.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val Block.name: Text by Block::name
val Block.blastResistance: Float by Block::blastResistance
val Block.defaultState: BlockState by Block::defaultState
val Block.jumpVelocityMultiplier: Float by Block::jumpVelocityMultiplier
val Block.slipperiness: Float by Block::slipperiness
val Block.stateManager: StateManager<Block, BlockState> by Block::stateManager
val Block.velocityMultiplier: Float by Block::velocityMultiplier

