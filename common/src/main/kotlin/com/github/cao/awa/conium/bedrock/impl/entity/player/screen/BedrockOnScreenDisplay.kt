package com.github.cao.awa.conium.bedrock.impl.entity.player.screen

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.impl.entity.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.impl.entity.player.screen.title.BedrockScreenDisplayTitle
import com.github.cao.awa.conium.bedrock.impl.script.BedrockScriptAnonymousObjectMap

@BedrockScriptApi
@BedrockScriptApiFacade("ScreenDisplay")
class BedrockOnScreenDisplay(val player: BedrockPlayer) {
    private val screenDisplayTitle: BedrockScreenDisplayTitle = BedrockScreenDisplayTitle("", "")

    @BedrockScriptApiFacade("ScreenDisplay", "setTitle")
    fun setTitle(title: String, properties: BedrockScriptAnonymousObjectMap) = this.screenDisplayTitle.setTitle(title, properties)

    @BedrockScriptApiFacade("ScreenDisplay", "updateTitle")
    fun updateSubtitle(title: String) = this.screenDisplayTitle.updateSubtitle(title)

    @BedrockScriptApiFacade("ScreenDisplay", "setActionBar")
    fun setActionBar(title: String) = this.screenDisplayTitle.setActionBar(title)
}
