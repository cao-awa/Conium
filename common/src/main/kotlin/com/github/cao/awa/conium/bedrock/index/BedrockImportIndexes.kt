@file:Suppress("unused")

package com.github.cao.awa.conium.bedrock.index

// System
import com.github.cao.awa.conium.bedrock.system.AbstractBedrockSystem
import com.github.cao.awa.conium.bedrock.system.BedrockSystem

// Script APIs.
import com.github.cao.awa.conium.bedrock.script.BedrockScriptAnonymousObjectMap

// World.
import com.github.cao.awa.conium.bedrock.world.AbstractBedrockWorld

// Entity.
import com.github.cao.awa.conium.bedrock.entity.BedrockEntity
import com.github.cao.awa.conium.bedrock.entity.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.entity.player.delegate.BedrockPlayerDelegate
import com.github.cao.awa.conium.bedrock.entity.player.screen.BedrockOnScreenDisplay

// Event.
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.event.context.item.use.BedrockItemUseOnEventContext
import com.github.cao.awa.conium.bedrock.event.before.item.use.on.BedrockItemUseOnBeforeEvent
import com.github.cao.awa.conium.bedrock.event.after.item.use.on.BedrockItemUseOnAfterEvent

// Item.
import com.github.cao.awa.conium.bedrock.item.stack.BedrockItemStack

// DimensionLocation
import com.github.cao.awa.conium.bedrock.world.dimension.BedrockDimensionLocation

// System typealias.
typealias AbstractBedrockSystem = AbstractBedrockSystem
typealias BedrockSystem = BedrockSystem

// Script typealias.
typealias BedrockScriptAnonymousObjectMap = BedrockScriptAnonymousObjectMap

// World.
typealias AbstractBedrockWorld = AbstractBedrockWorld

// Entity typealias.
typealias BedrockEntity = BedrockEntity
typealias BedrockPlayer = BedrockPlayer
typealias BedrockPlayerDelegate = BedrockPlayerDelegate
typealias BedrockOnScreenDisplay = BedrockOnScreenDisplay

// Event typealias.
typealias BedrockEventContext = BedrockEventContext
typealias ItemUseOnBeforeEventSignal = BedrockItemUseOnBeforeEvent
typealias ItemUseOnAfterEventSignal = BedrockItemUseOnAfterEvent
typealias ItemUseOnBeforeEvent = BedrockItemUseOnEventContext
typealias ItemUseOnAfterEvent = BedrockItemUseOnEventContext

// Event context typealias.

// Item typealias.
typealias BedrockItemStack = BedrockItemStack

// DimensionLocation
typealias DimensionLocation = BedrockDimensionLocation
