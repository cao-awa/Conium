package com.github.cao.awa.conium.bedrock.system

import com.github.cao.awa.catheter.receptacle.IntegerReceptacle

abstract class AbstractBedrockSystem {
    abstract fun runInterval(action: () -> Unit, interval: Int): Int

    abstract fun clearRun(id: IntegerReceptacle)
}
