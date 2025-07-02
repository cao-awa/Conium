package com.github.cao.awa.conium.script.index.client

import com.github.cao.awa.conium.event.type.ConiumClientEventArgTypes
import com.github.cao.awa.conium.mapping.yarn.ClientPlayerEntity
import com.github.cao.awa.conium.mapping.yarn.ClientWorld
import com.github.cao.awa.conium.parameter.DynamicArgType

val CLIENT_WORLD: DynamicArgType<ClientWorld> = ConiumClientEventArgTypes.CLIENT_WORLD
val CLIENT_PLAYER: DynamicArgType<ClientPlayerEntity> = ConiumClientEventArgTypes.CLIENT_PLAYER
