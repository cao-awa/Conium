package com.github.cao.awa.conium.bedrock.system

import com.github.cao.awa.catheter.receptacle.IntegerReceptacle
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade

@BedrockScriptApiFacade("System")
abstract class AbstractBedrockSystem {
    abstract fun runInterval(callback: () -> Unit, tickInterval: Int): IntegerReceptacle

    abstract fun clearRun(runId: IntegerReceptacle)
}
