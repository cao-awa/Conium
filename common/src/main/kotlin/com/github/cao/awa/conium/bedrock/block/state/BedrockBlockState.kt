package com.github.cao.awa.conium.bedrock.block.state

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import net.minecraft.block.BlockState

@BedrockScriptApi
@BedrockScriptApiFacade("Block")
class BedrockBlockState(private val delegate: BlockState)

val BlockState.bedrock: BedrockBlockState get() = BedrockBlockState(this)