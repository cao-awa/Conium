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
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult

object ConiumEventArgTypes {
    @JvmField
    val ITEM_USAGE_CONTEXT: DynamicArgType<ItemUsageContext> = arg("item_usage_context")

    @JvmField
    val ITEM_PLACEMENT_CONTEXT: DynamicArgType<ItemPlacementContext> = arg("item_placement_context")

    @JvmField
    val ITEM_STACK: DynamicArgType<ItemStack> = arg(
        "item_stack",
        DynamicArgsBuilder.transform(ITEM_PLACEMENT_CONTEXT) { placement -> placement.stack }
    )

    @JvmField
    val ACTION_RESULT: DynamicArgType<ActionResult> = arg("action_result")

    @JvmField
    val SERVER: DynamicArgType<MinecraftServer> = arg("server")

    @JvmField
    val WORLD: DynamicArgType<World> = arg("world")

    @JvmField
    val SERVER_WORLD: DynamicArgType<ServerWorld> = arg(
        "server_world",
        DynamicArgsBuilder.transform(WORLD) { world -> world as? ServerWorld },
        DynamicArgsBuilder.transform(ITEM_PLACEMENT_CONTEXT) { placement -> placement.world as? ServerWorld }
    )

    @JvmField
    val CLIENT_WORLD: DynamicArgType<ClientWorld> = arg(
        "client_world",
        DynamicArgsBuilder.transform(WORLD) { world -> world as? ClientWorld },
        DynamicArgsBuilder.transform(ITEM_PLACEMENT_CONTEXT) { placement -> placement.world as? ClientWorld }
    )

    @JvmField
    val BLOCK_POS: DynamicArgType<BlockPos> = arg(
        "block_pos",
        DynamicArgsBuilder.transform(ITEM_PLACEMENT_CONTEXT) { placement -> placement.blockPos }
    )

    @JvmField
    val BLOCK_STATE: DynamicArgType<AbstractBlockState> = arg(
        "block_state"
    )

    @JvmField
    val BLOCK_HIT_RESULT: DynamicArgType<BlockHitResult> = arg("block_hit_result")

    @JvmField
    val PLAYER: DynamicArgType<PlayerEntity> = arg(
        "player",
        DynamicArgsBuilder.transform(ITEM_PLACEMENT_CONTEXT) { placement -> placement.player }
    )

    @JvmField
    val LIVING_ENTITY: DynamicArgType<LivingEntity> = arg("living_entity")

    @JvmField
    val SERVER_PLAYER: DynamicArgType<ServerPlayerEntity> = arg(
        "server_player",
        DynamicArgsBuilder.transform(PLAYER) { player -> player as? ServerPlayerEntity },
        DynamicArgsBuilder.transform(ITEM_PLACEMENT_CONTEXT) { placement -> placement.player as? ServerPlayerEntity }
    )

    @JvmField
    val CLIENT_PLAYER: DynamicArgType<ClientPlayerEntity> = arg(
        "client_player",
        DynamicArgsBuilder.transform(PLAYER) { player -> player as? ClientPlayerEntity },
        DynamicArgsBuilder.transform(ITEM_PLACEMENT_CONTEXT) { placement -> placement.player as? ClientPlayerEntity }
    )
}
