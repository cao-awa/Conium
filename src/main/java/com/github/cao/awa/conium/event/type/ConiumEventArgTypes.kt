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
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult

object ConiumEventArgTypes {
    @JvmField
    val ITEM_USAGE_CONTEXT: DynamicArgType<ItemUsageContext>

    @JvmField
    val ITEM_PLACEMENT_CONTEXT: DynamicArgType<ItemPlacementContext>

    @JvmField
    val ITEM_STACK: DynamicArgType<ItemStack>

    @JvmField
    val ACTION_RESULT: DynamicArgType<ActionResult>

    @JvmField
    val SERVER: DynamicArgType<MinecraftServer>

    @JvmField
    val WORLD: DynamicArgType<World>

    @JvmField
    val SERVER_WORLD: DynamicArgType<ServerWorld>

    @JvmField
    var CLIENT_WORLD: DynamicArgType<ClientWorld>

    @JvmField
    val BLOCK_POS: DynamicArgType<BlockPos>

    @JvmField
    val BLOCK_STATE: DynamicArgType<AbstractBlockState>

    @JvmField
    val BLOCK_HIT_RESULT: DynamicArgType<BlockHitResult>

    @JvmField
    val LIVING_ENTITY: DynamicArgType<LivingEntity>

    @JvmField
    val PLAYER: DynamicArgType<PlayerEntity>

    @JvmField
    val SERVER_PLAYER: DynamicArgType<ServerPlayerEntity>

    @JvmField
    val CLIENT_PLAYER: DynamicArgType<ClientPlayerEntity>

    @JvmField
    val DAMAGE_SOURCE: DynamicArgType<DamageSource>

    @JvmField
    val INT: DynamicArgType<Int>

    @JvmField
    val LONG: DynamicArgType<Long>

    @JvmField
    val FLOAT: DynamicArgType<Float>

    @JvmField
    val DOUBLE: DynamicArgType<Double>

    init {
        ITEM_USAGE_CONTEXT = arg("item_usage_context")

        ITEM_PLACEMENT_CONTEXT = arg("item_placement_context")

        ITEM_STACK = arg(
            "item_stack",
            DynamicArgsBuilder.transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.stack }
        )

        ACTION_RESULT = arg("action_result")

        SERVER = arg("server")

        WORLD = arg("world")

        SERVER_WORLD = arg(
            "server_world",
            DynamicArgsBuilder.transform(::WORLD) { world -> world as? ServerWorld },
            DynamicArgsBuilder.transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.world as? ServerWorld }
        )

        CLIENT_WORLD = arg(
            "client_world",
            DynamicArgsBuilder.transform(::WORLD) { world -> world as? ClientWorld },
            DynamicArgsBuilder.transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.world as? ClientWorld }
        )

        BLOCK_POS = arg(
            "block_pos",
            DynamicArgsBuilder.transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.blockPos }
        )

        BLOCK_STATE = arg("block_state")

        BLOCK_HIT_RESULT = arg("block_hit_result")

        LIVING_ENTITY = arg("living_entity")

        PLAYER = arg(
            "player",
            DynamicArgsBuilder.transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.player }
        )

        SERVER_PLAYER = arg(
            "server_player",
            DynamicArgsBuilder.transform(::PLAYER) { player -> player as? ServerPlayerEntity },
            DynamicArgsBuilder.transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.player as? ServerPlayerEntity }
        )

        CLIENT_PLAYER = arg(
            "client_player",
            DynamicArgsBuilder.transform(::PLAYER) { player -> player as? ClientPlayerEntity },
            DynamicArgsBuilder.transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.player as? ClientPlayerEntity }
        )

        DAMAGE_SOURCE = arg("damage_source")

        INT = arg(
            "int",
            DynamicArgsBuilder.transform(::LONG, Long::toInt),
            DynamicArgsBuilder.transform(::FLOAT, Float::toInt),
            DynamicArgsBuilder.transform(::DOUBLE, Double::toInt),
        )

        LONG = arg(
            "long",
            DynamicArgsBuilder.transform(::INT, Int::toLong),
            DynamicArgsBuilder.transform(::FLOAT, Float::toLong),
            DynamicArgsBuilder.transform(::DOUBLE, Double::toLong),
        )

        FLOAT = arg(
            "float",
            DynamicArgsBuilder.transform(::INT, Int::toFloat),
            DynamicArgsBuilder.transform(::LONG, Long::toFloat),
            DynamicArgsBuilder.transform(::DOUBLE, Double::toFloat),
        )

        DOUBLE = arg(
            "double",
            DynamicArgsBuilder.transform(::INT, Int::toDouble),
            DynamicArgsBuilder.transform(::LONG, Long::toDouble),
            DynamicArgsBuilder.transform(::FLOAT, Float::toDouble),
        )
    }
}
