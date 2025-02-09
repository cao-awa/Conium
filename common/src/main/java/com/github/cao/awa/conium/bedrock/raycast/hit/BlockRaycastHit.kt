package com.github.cao.awa.conium.bedrock.raycast.hit

import com.github.cao.awa.conium.bedrock.block.state.BedrockBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

data class BlockRaycastHit(val block: BedrockBlockState, val face: Direction, val faceLocation: BlockPos) {
}