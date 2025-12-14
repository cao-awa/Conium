package com.github.cao.awa.conium.bedrock.impl.raycast.hit

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.annotation.script.javascript.ScriptReadonly
import com.github.cao.awa.conium.bedrock.impl.block.state.BedrockBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

@BedrockScriptApi
@BedrockScriptApiFacade("BlockRaycastHit")
data class BlockRaycastHit(
    @ScriptReadonly
    @BedrockScriptApiFacade("BlockRaycastHit", "#block")
    val block: BedrockBlockState,
    @ScriptReadonly
    @BedrockScriptApiFacade("BlockRaycastHit", "#face")
    val face: Direction,
    @ScriptReadonly
    @BedrockScriptApiFacade("BlockRaycastHit", "#faceLocation")
    val faceLocation: BlockPos
)
