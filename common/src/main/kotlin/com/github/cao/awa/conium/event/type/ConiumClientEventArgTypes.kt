package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ITEM_PLACEMENT_CONTEXT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.PLAYER
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import com.github.cao.awa.conium.kotlin.extend.innate.asIt
import com.github.cao.awa.conium.parameter.DynamicArgType
import com.github.cao.awa.conium.parameter.DynamicArgsBuilder.Companion.transform
import com.github.cao.awa.conium.parameter.type.DynamicArgTypeBuilder.arg
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.world.World

@Environment(EnvType.CLIENT)
object ConiumClientEventArgTypes {
    @JvmField
    val CLIENT_WORLD: DynamicArgType<ClientWorld>

    @JvmField
    val CLIENT_PLAYER: DynamicArgType<ClientPlayerEntity>

    init {
        CLIENT_WORLD = arg(
            "client_world",
            transform(::WORLD) { world: World -> world as? ClientWorld },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement: ItemPlacementContext -> placement.world as? ClientWorld }
        )

        CLIENT_PLAYER = arg(
            "client_player",
            transform(::PLAYER) { player: PlayerEntity -> player as? ClientPlayerEntity },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement: ItemPlacementContext -> placement.player as? ClientPlayerEntity }
        )
    }

    fun onClientInitialized() {
        if (!Conium.isClient) {
            throw IllegalStateException("Client event types cannot load on not client environment")
        }

        PLAYER.appendArgs(transform(ConiumClientEventArgTypes::CLIENT_PLAYER, ClientPlayerEntity::asIt))
        WORLD.appendArgs(transform( ConiumClientEventArgTypes::CLIENT_WORLD, ClientWorld::asIt))
    }
}