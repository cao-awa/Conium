package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.event.context.ConiumEventContext
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

object ConiumEventArgTypes {
    @JvmField
    var WORLD: DynamicArgType<World> = DynamicArgType("world")

    @JvmField
    var SERVER_WORLD: DynamicArgType<ServerWorld> = DynamicArgType(
        "server_world",
        DynamicArgsBuilder.transform(WORLD) { _, world ->
            if (!world.isClient()) world as ServerWorld else null
        }
    )

    @JvmField
    var CLIENT_WORLD: DynamicArgType<ClientWorld> = DynamicArgType(
        "client_world",
        DynamicArgsBuilder.transform(WORLD) { _, world ->
            if (world.isClient()) world as ClientWorld else null
        }
    )

    @JvmField
    var BLOCK_POS: DynamicArgType<BlockPos> = DynamicArgType("block_pos")

    @JvmField
    var BLOCK_STATE: DynamicArgType<BlockState> = DynamicArgType("block_state")

    @JvmField
    var PLAYER: DynamicArgType<PlayerEntity> = DynamicArgType("player")

    @JvmField
    var SERVER_PLAYER: DynamicArgType<ServerPlayerEntity> = DynamicArgType(
        "server_player",
        DynamicArgsBuilder.transform(PLAYER) { player ->
            if (player is ServerPlayerEntity) player else null
        }
    )

    @JvmField
    var CLIENT_PLAYER: DynamicArgType<ClientPlayerEntity> = DynamicArgType(
        "client_player",
        DynamicArgsBuilder.transform(PLAYER) { player ->
            if (player is ClientPlayerEntity) player else null
        }
    )

    // Items
    @JvmField
    var ITEM_USAGE_CONTEXT: DynamicArgType<ItemUsageContext> = DynamicArgType("item_usage_context")
}
