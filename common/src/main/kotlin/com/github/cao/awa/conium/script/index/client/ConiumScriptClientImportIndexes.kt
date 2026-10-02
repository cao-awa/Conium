package com.github.cao.awa.conium.script.index.client

import com.github.cao.awa.conium.event.type.ConiumClientEventArgTypes
import com.github.cao.awa.conium.parameter.dynamic.type.DynamicArgType
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.player.LocalPlayer

val CLIENT_WORLD: DynamicArgType<ClientLevel> = ConiumClientEventArgTypes.CLIENT_WORLD
val CLIENT_PLAYER: DynamicArgType<LocalPlayer> = ConiumClientEventArgTypes.CLIENT_PLAYER
