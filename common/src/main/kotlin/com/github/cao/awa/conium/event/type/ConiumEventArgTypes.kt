package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.block.entity.ConiumBlockEntity
import com.github.cao.awa.conium.kotlin.extent.innate.*
import com.github.cao.awa.conium.mapping.yarn.reference.server
import com.github.cao.awa.conium.parameter.dynamic.type.DynamicArgType
import com.github.cao.awa.conium.parameter.dynamic.builder.DynamicArgsBuilder.Companion.transform
import com.github.cao.awa.conium.parameter.dynamic.type.builder.DynamicArgTypeBuilder.arg
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.ViewerCountManager
import net.minecraft.entity.ContainerUser
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.item.Item
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.network.packet.s2c.play.ChunkData
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket
import net.minecraft.network.packet.s2c.play.LightData
import net.minecraft.screen.slot.Slot
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.ClickType
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.World
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.chunk.WorldChunk
import net.minecraft.world.tick.ScheduledTickView
import net.minecraft.util.Unit as MinecraftUnit

object ConiumEventArgTypes {
    @JvmField
    val MINECRAFT_UNIT: DynamicArgType<MinecraftUnit>

    @JvmField
    val UNIT: DynamicArgType<Unit>

    @JvmField
    val ITEM: DynamicArgType<Item>

    @JvmField
    val ITEM_USAGE_CONTEXT: DynamicArgType<ItemUsageContext>

    @JvmField
    val ITEM_PLACEMENT_CONTEXT: DynamicArgType<ItemPlacementContext>

    @JvmField
    val ITEM_STACK: DynamicArgType<ItemStack>

    @JvmField
    val CURSOR_STACK: DynamicArgType<ItemStack>

    @JvmField
    val CLICK_TYPE: DynamicArgType<ClickType>

    @JvmField
    val SLOT: DynamicArgType<Slot>

    @JvmField
    val EQUIPMENT_SLOT: DynamicArgType<EquipmentSlot>

    @JvmField
    val SLOT_NUMBER: DynamicArgType<Int>

    @JvmField
    val SELECT_STATUS: DynamicArgType<Boolean>

    @JvmField
    val HAND: DynamicArgType<Hand>

    @JvmField
    val REMAINING_USE_TICKS: DynamicArgType<Int>

    @JvmField
    val ACTION_RESULT: DynamicArgType<ActionResult>

    @JvmField
    val VIEWER_COUNT_MANAGER: DynamicArgType<ViewerCountManager>

    @JvmField
    val RANDOM: DynamicArgType<Random>

    @JvmField
    val SERVER: DynamicArgType<MinecraftServer>

    @JvmField
    val SCHEDULED_TICK_VIEW: DynamicArgType<ScheduledTickView>

    @JvmField
    val WORLD: DynamicArgType<World>

    @JvmField
    val SERVER_WORLD: DynamicArgType<ServerWorld>

    @JvmField
    val CHUNK: DynamicArgType<Chunk>

    @JvmField
    val WORLD_CHUNK: DynamicArgType<WorldChunk>

    @JvmField
    val CHUNK_DATA: DynamicArgType<ChunkData>

    @JvmField
    val CHUNK_DATA_S2C_PACKET: DynamicArgType<ChunkDataS2CPacket>

    @JvmField
    val LIGHT_DATA: DynamicArgType<LightData>

    @JvmField
    val BLOCK: DynamicArgType<Block>

    @JvmField
    val BLOCK_POS: DynamicArgType<BlockPos>

    @JvmField
    val BLOCK_ENTITY: DynamicArgType<BlockEntity>

    @JvmField
    val C_BLOCK_ENTITY: DynamicArgType<ConiumBlockEntity>

    @JvmField
    val BLOCK_STATE: DynamicArgType<BlockState>

    @JvmField
    val FLUID_STATE: DynamicArgType<FluidState>

    @JvmField
    val FLUID: DynamicArgType<Fluid>

    @JvmField
    val BLOCK_HIT_RESULT: DynamicArgType<BlockHitResult>

    @JvmField
    val ENTITY_TYPE: DynamicArgType<EntityType<*>>

    @JvmField
    val ENTITY: DynamicArgType<Entity>

    @JvmField
    val LIVING_ENTITY: DynamicArgType<LivingEntity>

    @JvmField
    val CONTAINER_USER: DynamicArgType<ContainerUser>

    @JvmField
    val PLAYER: DynamicArgType<PlayerEntity>

    @JvmField
    val SERVER_PLAYER: DynamicArgType<ServerPlayerEntity>

    @JvmField
    val DAMAGE_SOURCE: DynamicArgType<DamageSource>

    @JvmField
    val DAMAGE_AMOUNT: DynamicArgType<Float>

    @JvmField
    val INT: DynamicArgType<Int>

    @JvmField
    val LONG: DynamicArgType<Long>

    @JvmField
    val FLOAT: DynamicArgType<Float>

    @JvmField
    val DOUBLE: DynamicArgType<Double>

    @JvmField
    val SERVER_CONFIGURATION_NETWORK_HANDLER: DynamicArgType<ServerConfigurationNetworkHandler>

    init {
        MINECRAFT_UNIT = arg("minecraft_unit")

        UNIT = arg("unit")

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

        CURSOR_STACK = arg("cursor_stack")

        CLICK_TYPE = arg("click_type")

        SLOT = arg("slot")

        EQUIPMENT_SLOT = arg("equipment_slot")

        SLOT_NUMBER = arg(
            "slot_number",
            transform(::EQUIPMENT_SLOT, EquipmentSlot::getIndex)
        )

        SELECT_STATUS = arg("select_status")

        HAND = arg(
            "hand",
            transform(::ITEM_USAGE_CONTEXT, ItemUsageContext::getHand)
        )

        REMAINING_USE_TICKS = arg("remaining_use_ticks")

        ACTION_RESULT = arg("action_result")

        VIEWER_COUNT_MANAGER = arg(
            "view_count_manager"
        )

        CONTAINER_USER = arg(
            "container_user"
        )

        RANDOM = arg(
            "random",
            transform(::WORLD, World::getRandom)
        )

        SERVER = arg(
            "server",
            transform(::SERVER_PLAYER) { player: ServerPlayerEntity ->
                player.server
            },
            transform(::SERVER_WORLD, ServerWorld::getServer)
        )

        SCHEDULED_TICK_VIEW = arg(
            "schedule_tick_view",
            transform(::WORLD, World::asIt)
        )

        WORLD = arg(
            "world",
            transform(::PLAYER, PlayerEntity::getEntityWorld),
            transform(::SERVER_WORLD, ServerWorld::asIt),
        )

        SERVER_WORLD = arg(
            "server_world",
            transform(::WORLD) { world: World -> world as? ServerWorld },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.world as? ServerWorld }
        )

        CHUNK = arg(
            "chunk"
        )

        WORLD_CHUNK = arg(
            "world_chunk",
            transform(::CHUNK) { chunk: Chunk -> chunk as WorldChunk }
        )

        CHUNK_DATA = arg(
            "chunk_data"
        )

        CHUNK_DATA_S2C_PACKET = arg(
            "chunk_data_s2c_packet"
        )

        LIGHT_DATA = arg(
            "light_data"
        )

        BLOCK = arg(
            "block",
            transform(::BLOCK_STATE, AbstractBlockState::getBlock)
        )

        BLOCK_POS = arg(
            "block_pos",
            transform(::ITEM_PLACEMENT_CONTEXT, ItemPlacementContext::getBlockPos)
        )

        BLOCK_ENTITY = arg(
            "block_entity",
            transform(::WORLD, ::BLOCK_POS, World::getBlockEntity)
        )

        C_BLOCK_ENTITY = arg(
            "c_block_entity",
            transform(::WORLD, ::BLOCK_POS) { world, pos -> world.getBlockEntity(pos) as? ConiumBlockEntity }
        )

        BLOCK_STATE = arg(
            "block_state",
            transform(::FLUID_STATE, FluidState::getBlockState)
        )

        FLUID_STATE = arg("fluid_state")

        FLUID = arg(
            "fluid",
            transform(::FLUID_STATE, FluidState::getFluid)
        )

        BLOCK_HIT_RESULT = arg("block_hit_result")

        ENTITY_TYPE = arg(
            "entity_type",
            transform(::ENTITY, Entity::getType)
        )

        ENTITY = arg(
            "entity",
            transform(::LIVING_ENTITY, LivingEntity::asIt)
        )

        LIVING_ENTITY = arg(
            "living_entity",
            transform(::ENTITY) { entity: Entity -> entity as? LivingEntity },
            transform(::PLAYER, PlayerEntity::asIt)
        )

        PLAYER = arg(
            "player",
            transform(::ITEM_PLACEMENT_CONTEXT, ItemPlacementContext::getPlayer),
            transform(::ITEM_USAGE_CONTEXT, ItemUsageContext::getPlayer),
            transform(::LIVING_ENTITY) { entity: LivingEntity -> entity as? PlayerEntity },
            transform(::SERVER_PLAYER, ServerPlayerEntity::asIt),
        )

        SERVER_PLAYER = arg(
            "server_player",
            transform(::PLAYER) { player: PlayerEntity -> player as? ServerPlayerEntity },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement: ItemPlacementContext -> placement.player as? ServerPlayerEntity }
        )

        DAMAGE_SOURCE = arg("damage_source")

        DAMAGE_AMOUNT = arg("damage_amount")

        INT = arg(
            "int",
            transform(::LONG, Long::int),
            transform(::FLOAT, Float::int),
            transform(::DOUBLE, Double::int),
            transform(::REMAINING_USE_TICKS, Int::asIt)
        )

        LONG = arg(
            "long",
            transform(::INT, Int::long),
            transform(::FLOAT, Float::long),
            transform(::DOUBLE, Double::long),
        )

        FLOAT = arg(
            "float",
            transform(::INT, Int::float),
            transform(::LONG, Long::float),
            transform(::DOUBLE, Double::float),
        )

        DOUBLE = arg(
            "double",
            transform(::INT, Int::double),
            transform(::LONG, Long::double),
            transform(::FLOAT, Float::double),
        )

        SERVER_CONFIGURATION_NETWORK_HANDLER = arg(
            "server_configuration_network_handler"
        )
    }
}
