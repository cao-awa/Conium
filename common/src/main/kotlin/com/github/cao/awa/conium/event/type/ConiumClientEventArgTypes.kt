package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ITEM_PLACEMENT_CONTEXT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.PLAYER
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import com.github.cao.awa.conium.kotlin.extent.innate.asIt
import com.github.cao.awa.conium.parameter.dynamic.type.DynamicArgType
import com.github.cao.awa.conium.parameter.dynamic.builder.DynamicArgsBuilder.Companion.transform
import com.github.cao.awa.conium.parameter.dynamic.type.builder.DynamicArgTypeBuilder.arg
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.player.LocalPlayer
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level

@Environment(EnvType.CLIENT)
object ConiumClientEventArgTypes {
    @JvmField
    val CLIENT_WORLD: DynamicArgType<ClientLevel>

    @JvmField
    val CLIENT_PLAYER: DynamicArgType<LocalPlayer>

    init {
        CLIENT_WORLD = arg(
            "client_world",
            transform(::WORLD) { world: Level -> world as? ClientLevel },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement: BlockPlaceContext -> placement.level as? ClientLevel }
        )

        CLIENT_PLAYER = arg(
            "client_player",
            transform(::PLAYER) { player: Player -> player as? LocalPlayer },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement: BlockPlaceContext -> placement.player as? LocalPlayer }
        )
    }

    fun onClientInitialized() {
        if (!Conium.isClient) {
            throw IllegalStateException("Client event types cannot load on not client environment")
        }

        PLAYER.appendArgs(transform(ConiumClientEventArgTypes::CLIENT_PLAYER, LocalPlayer::asIt))
        WORLD.appendArgs(transform( ConiumClientEventArgTypes::CLIENT_WORLD, ClientLevel::asIt))
    }
}