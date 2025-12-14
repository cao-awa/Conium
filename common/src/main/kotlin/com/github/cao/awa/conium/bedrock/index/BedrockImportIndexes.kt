@file:Suppress("unused")

package com.github.cao.awa.conium.bedrock.index

// System
import com.github.cao.awa.conium.bedrock.impl.system.AbstractBedrockSystem
import com.github.cao.awa.conium.bedrock.impl.system.BedrockSystem

// Script APIs.
import com.github.cao.awa.conium.bedrock.impl.script.BedrockScriptAnonymousObjectMap

// World.
import com.github.cao.awa.conium.bedrock.impl.world.AbstractBedrockWorld

// Entity.
import com.github.cao.awa.conium.bedrock.impl.entity.BedrockEntity
import com.github.cao.awa.conium.bedrock.impl.entity.player.BedrockPlayer
import com.github.cao.awa.conium.bedrock.impl.entity.player.delegate.BedrockPlayerDelegate
import com.github.cao.awa.conium.bedrock.impl.entity.player.screen.BedrockOnScreenDisplay

// Event.
import com.github.cao.awa.conium.bedrock.impl.event.context.BedrockEventContext
import com.github.cao.awa.conium.bedrock.impl.event.before.item.use.on.context.BedrockItemUseOnEventMetadata
import com.github.cao.awa.conium.bedrock.impl.event.before.item.use.on.BedrockItemUseOnBeforeEvent
import com.github.cao.awa.conium.bedrock.impl.event.after.item.use.on.BedrockItemUseOnAfterEvent

// Item.
import com.github.cao.awa.conium.bedrock.impl.item.stack.BedrockItemStack

// DimensionLocation
import com.github.cao.awa.conium.bedrock.impl.world.dimension.BedrockDimensionLocation

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
typealias BedrockEventContext<I, M> = BedrockEventContext<I, M>
typealias ItemUseOnBeforeEventSignal = BedrockItemUseOnBeforeEvent
typealias ItemUseOnAfterEventSignal = BedrockItemUseOnAfterEvent
typealias ItemUseOnBeforeEvent = BedrockItemUseOnEventMetadata
typealias ItemUseOnAfterEvent = BedrockItemUseOnEventMetadata

// Event context typealias.

// Item typealias.
typealias BedrockItemStack = BedrockItemStack

// DimensionLocation
typealias DimensionLocation = BedrockDimensionLocation
