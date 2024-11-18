package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.parameter.DynamicArgType
import com.github.cao.awa.conium.parameter.DynamicArgsBuilder.Companion.transform
import com.github.cao.awa.conium.parameter.type.DynamicArgTypeBuilder.arg
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.block.Block
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object ConiumEventArgTypes {
    @JvmField
    val ITEM: DynamicArgType<Item>

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
    val BLOCK: DynamicArgType<Block>

    @JvmField
    val BLOCK_POS: DynamicArgType<BlockPos>

    @JvmField
    val BLOCK_STATE: DynamicArgType<AbstractBlockState>

    @JvmField
    val BLOCK_HIT_RESULT: DynamicArgType<BlockHitResult>

    @JvmField
    val ENTITY_TYPE: DynamicArgType<EntityType<*>>

    @JvmField
    val ENTITY: DynamicArgType<Entity>

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
        ITEM = arg(
            "item",
            transform(::ITEM_STACK, ItemStack::getItem)
        )

        ITEM_USAGE_CONTEXT = arg("item_usage_context")

        ITEM_PLACEMENT_CONTEXT = arg("item_placement_context")

        ITEM_STACK = arg(
            "item_stack",
            transform(::ITEM_USAGE_CONTEXT, ItemUsageContext::getStack),
            transform(::ITEM_PLACEMENT_CONTEXT, ItemPlacementContext::getStack)
        )

        ACTION_RESULT = arg("action_result")

        SERVER = arg(
            "server",
            transform(::SERVER_PLAYER, ServerPlayerEntity::server),
            transform(::SERVER_WORLD, ServerWorld::getServer)
        )

        WORLD = arg(
            "world",
            transform(::PLAYER, PlayerEntity::getWorld)
        )

        SERVER_WORLD = arg(
            "server_world",
            transform(::WORLD) { world -> world as? ServerWorld },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.world as? ServerWorld }
        )

        CLIENT_WORLD = arg(
            "client_world",
            transform(::WORLD) { world -> world as? ClientWorld },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.world as? ClientWorld }
        )

        BLOCK = arg(
            "block",
            transform(::BLOCK_STATE, AbstractBlockState::getBlock)
        )

        BLOCK_POS = arg(
            "block_pos",
            transform(::ITEM_PLACEMENT_CONTEXT, ItemPlacementContext::getBlockPos)
        )

        BLOCK_STATE = arg("block_state")

        BLOCK_HIT_RESULT = arg("block_hit_result")

        ENTITY_TYPE = arg(
            "entity_type",
            transform(::ENTITY, Entity::getType)
        )

        ENTITY = arg(
            "entity",
            transform(::LIVING_ENTITY) { entity -> entity }
        )

        LIVING_ENTITY = arg(
            "living_entity",
            transform(::ENTITY) { entity -> entity as? LivingEntity },
            transform(::PLAYER) { player -> player }
        )

        PLAYER = arg(
            "player",
            transform(::ITEM_PLACEMENT_CONTEXT, ItemPlacementContext::getPlayer),
            transform(::LIVING_ENTITY) { entity -> entity as? PlayerEntity },
            transform(::SERVER_PLAYER) { player -> player },
            transform(::CLIENT_PLAYER) { player -> player }
        )

        SERVER_PLAYER = arg(
            "server_player",
            transform(::PLAYER) { player -> player as? ServerPlayerEntity },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.player as? ServerPlayerEntity }
        )

        CLIENT_PLAYER = arg(
            "client_player",
            transform(::PLAYER) { player -> player as? ClientPlayerEntity },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.player as? ClientPlayerEntity }
        )

        DAMAGE_SOURCE = arg("damage_source")

        INT = arg(
            "int",
            transform(::LONG, Long::toInt),
            transform(::FLOAT, Float::toInt),
            transform(::DOUBLE, Double::toInt),
        )

        LONG = arg(
            "long",
            transform(::INT, Int::toLong),
            transform(::FLOAT, Float::toLong),
            transform(::DOUBLE, Double::toLong),
        )

        FLOAT = arg(
            "float",
            transform(::INT, Int::toFloat),
            transform(::LONG, Long::toFloat),
            transform(::DOUBLE, Double::toFloat),
        )

        DOUBLE = arg(
            "double",
            transform(::INT, Int::toDouble),
            transform(::LONG, Long::toDouble),
            transform(::FLOAT, Float::toDouble),
        )
    }
}
