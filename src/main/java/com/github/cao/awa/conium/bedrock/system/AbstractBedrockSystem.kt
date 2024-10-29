package com.github.cao.awa.conium.bedrock.system

import com.github.cao.awa.catheter.receptacle.IntegerReceptacle
import com.github.cao.awa.conium.bedrock.BedrockScriptApiFacade

@BedrockScriptApiFacade("System")
abstract class AbstractBedrockSystem {
    abstract fun runInterval(action: () -> Unit, interval: Int): IntegerReceptacle

    abstract fun clearRun(id: IntegerReceptacle)
}
