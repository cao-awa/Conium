package com.github.cao.awa.conium.bedrock.entity.player.screen.title

import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.bedrock.script.toDynamicArgs
import com.github.cao.awa.conium.parameter.dynamic.builder.DynamicArgsBuilder
import com.github.cao.awa.conium.parameter.dynamic.type.builder.DynamicArgTypeBuilder.arg
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.hud.InGameHud
import net.minecraft.text.Text

class BedrockScreenDisplayTitle(private var title: String, private var subtitle: String, private var active: Boolean = false) {
    companion object {
        private val UNIT: Any = Any()
    }

    fun setTitle(title: String, properties: BedrockScriptAnonymousObjectMap) {
        MinecraftClient.getInstance().inGameHud.let { inGameHud: InGameHud ->
            DynamicArgsBuilder.requires<Unit, Int, Int, Int>(
                arg("fadeInDuration"),
                arg("stayDuration"),
                arg("fadeOutDuration"),
            ).transform(UNIT, properties.toDynamicArgs()) { _: Any, fadeInTicks: Int, stayTicks: Int, fadeOutTicks: Int ->
                inGameHud.setTitleTicks(fadeInTicks, stayTicks, fadeOutTicks)
            }

            inGameHud.setTitle(Text.of(title))

            properties.getAs<String>("subtitle").let {
                inGameHud.setSubtitle(Text.of(it))
            }
        }
    }

    fun updateSubtitle(title: String): Unit = MinecraftClient.getInstance().inGameHud.setSubtitle(Text.of(title))

    fun setActionBar(title: String): Unit = MinecraftClient.getInstance().inGameHud.setOverlayMessage(Text.of(title), false)
}
