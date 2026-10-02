@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.network.chat.Component

val Block.name: Component get() = this.name
val Block.blastResistance: Float get() = this.explosionResistance
val Block.defaultState: BlockState get() = this.defaultBlockState()
val Block.jumpVelocityMultiplier: Float get() = this.jumpFactor
val Block.slipperiness: Float get() = this.friction
val Block.stateManager: StateDefinition<Block, BlockState> get() = this.stateDefinition
val Block.velocityMultiplier: Float get() = this.speedFactor
