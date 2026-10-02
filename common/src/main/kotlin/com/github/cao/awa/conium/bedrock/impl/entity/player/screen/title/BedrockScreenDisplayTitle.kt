package com.github.cao.awa.conium.bedrock.impl.entity.player.screen.title

import com.github.cao.awa.conium.bedrock.impl.script.BedrockScriptAnonymousObjectMap
import com.github.cao.awa.conium.bedrock.impl.script.toDynamicArgs
import com.github.cao.awa.conium.parameter.dynamic.builder.DynamicArgsBuilder
import com.github.cao.awa.conium.parameter.dynamic.type.builder.DynamicArgTypeBuilder.arg
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Hud
import net.minecraft.network.chat.Component

class BedrockScreenDisplayTitle(private var title: String, private var subtitle: String, private var active: Boolean = false) {
    companion object {
        private val UNIT: Any = Any()
    }

    fun setTitle(title: String, properties: BedrockScriptAnonymousObjectMap) {
        Minecraft.getInstance().gui.hud.let { hud: Hud ->
            hud.setTitle(Component.literal(title))

            properties.getAs<String>("subtitle")?.let {
                hud.setSubtitle(Component.literal(it))
            }
        }
    }

    fun updateSubtitle(title: String): Unit = Minecraft.getInstance().gui.hud.setSubtitle(Component.literal(title))

    fun setActionBar(title: String): Unit = Minecraft.getInstance().gui.hud.setOverlayMessage(Component.literal(title), false)
}
