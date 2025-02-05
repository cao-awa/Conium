package com.github.cao.awa.conium.bedrock.entity.player.screen.title

import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.bedrock.script.toDynamicArgs
import com.github.cao.awa.conium.parameter.DynamicArgsBuilder
import com.github.cao.awa.conium.parameter.type.DynamicArgTypeBuilder.arg
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.hud.InGameHud
import net.minecraft.text.Text

class BedrockScreenDisplayTitle(private var title: String, private var subtitle: String, private var active: Boolean = false) {
    companion object {
        private val UNIT: Any = Any()
    }

    fun setTitle(title: String, properties: BedrockScriptAnonymousObjectMap) {
        MinecraftClient.getInstance().inGameHud.let { inGameHud: InGameHud ->
            properties["subtitle"]?.let {
                inGameHud.setSubtitle(Text.of(it as String))
            }

            DynamicArgsBuilder.requires<Unit, Int, Int, Int>(
                arg("fadeInDuration", Int::class.java),
                arg("stayDuration", Int::class.java),
                arg("fadeOutDuration", Int::class.java),
            ).arising(UNIT, properties.toDynamicArgs()) { _: Any, fadeInTicks: Int, stayTicks: Int, fadeOutTicks: Int ->
                inGameHud.setTitleTicks(fadeInTicks, stayTicks, fadeOutTicks)
                inGameHud.setTitle(Text.of(title))
            }
        }
    }

    fun updateSubtitle(title: String): Unit = MinecraftClient.getInstance().inGameHud.setSubtitle(Text.of(title))

    fun setActionBar(title: String): Unit = MinecraftClient.getInstance().inGameHud.setOverlayMessage(Text.of(title), false)
}
