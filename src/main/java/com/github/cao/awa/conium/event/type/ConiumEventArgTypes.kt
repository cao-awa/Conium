package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.parameter.DynamicArgsBuilder
import net.minecraft.block.BlockState
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import com.github.cao.awa.conium.parameter.type.DynamicArgTypeBuilder.arg
import net.minecraft.server.MinecraftServer

object ConiumEventArgTypes {
    @JvmField
    var SERVER: DynamicArgType<MinecraftServer> = arg("server")

    @JvmField
    var WORLD: DynamicArgType<World> = arg("world")

    @JvmField
    var SERVER_WORLD: DynamicArgType<ServerWorld> = arg(
        "server_world",
        DynamicArgsBuilder.transform(WORLD) { _, world ->
            world as? ServerWorld
        }
    )

    @JvmField
    var CLIENT_WORLD: DynamicArgType<ClientWorld> = arg(
        "client_world",
        DynamicArgsBuilder.transform(WORLD) { _, world ->
            world as? ClientWorld
        }
    )

    @JvmField
    var BLOCK_POS: DynamicArgType<BlockPos> = arg("block_pos")

    @JvmField
    var BLOCK_STATE: DynamicArgType<BlockState> = arg("block_state")

    @JvmField
    var PLAYER: DynamicArgType<PlayerEntity> = arg("player")

    @JvmField
    var SERVER_PLAYER: DynamicArgType<ServerPlayerEntity> = arg(
        "server_player",
        DynamicArgsBuilder.transform(PLAYER) { player ->
            if (player is ServerPlayerEntity) player else null
        }
    )

    @JvmField
    var CLIENT_PLAYER: DynamicArgType<ClientPlayerEntity> = arg(
        "client_player",
        DynamicArgsBuilder.transform(PLAYER) { player ->
            if (player is ClientPlayerEntity) player else null
        }
    )

    // Items
    @JvmField
    var ITEM_USAGE_CONTEXT: DynamicArgType<ItemUsageContext> = arg("item_usage_context")
}
