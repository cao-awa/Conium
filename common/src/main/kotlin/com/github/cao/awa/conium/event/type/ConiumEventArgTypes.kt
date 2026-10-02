package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.blockentity.ConiumBlockEntity
import com.github.cao.awa.conium.kotlin.extent.innate.*
import com.github.cao.awa.conium.mapping.yarn.reference.server
import com.github.cao.awa.conium.parameter.dynamic.type.DynamicArgType
import com.github.cao.awa.conium.parameter.dynamic.builder.DynamicArgsBuilder.Companion.transform
import com.github.cao.awa.conium.parameter.dynamic.type.builder.DynamicArgTypeBuilder.arg
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.ContainerOpenersCounter
import net.minecraft.world.Container
import net.minecraft.world.entity.ContainerUser
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.network.protocol.game.ClientboundLevelChunkPacketData
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.network.protocol.game.ClientboundLightUpdatePacketData
import net.minecraft.world.inventory.Slot
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationPacketListenerImpl
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionResult
import net.minecraft.world.inventory.ClickAction as ClickType
import net.minecraft.world.InteractionHand
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.chunk.ChunkAccess
import net.minecraft.world.level.chunk.LevelChunk
import net.minecraft.world.level.ScheduledTickAccess
import net.minecraft.util.Unit as MinecraftUnit

object ConiumEventArgTypes {
    @JvmField
    val MINECRAFT_UNIT: DynamicArgType<MinecraftUnit>

    @JvmField
    val UNIT: DynamicArgType<Unit>

    @JvmField
    val ITEM: DynamicArgType<Item>

    @JvmField
    val ITEM_USAGE_CONTEXT: DynamicArgType<UseOnContext>

    @JvmField
    val ITEM_PLACEMENT_CONTEXT: DynamicArgType<BlockPlaceContext>

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
    val HAND: DynamicArgType<InteractionHand>

    @JvmField
    val REMAINING_USE_TICKS: DynamicArgType<Int>

    @JvmField
    val ACTION_RESULT: DynamicArgType<InteractionResult>

    @JvmField
    val VIEWER_COUNT_MANAGER: DynamicArgType<ContainerOpenersCounter>

    @JvmField
    val RANDOM: DynamicArgType<RandomSource>

    @JvmField
    val SERVER: DynamicArgType<MinecraftServer>

    @JvmField
    val SCHEDULED_TICK_VIEW: DynamicArgType<ScheduledTickAccess>

    @JvmField
    val WORLD: DynamicArgType<Level>

    @JvmField
    val SERVER_WORLD: DynamicArgType<ServerLevel>

    @JvmField
    val CHUNK: DynamicArgType<ChunkAccess>

    @JvmField
    val WORLD_CHUNK: DynamicArgType<LevelChunk>

    @JvmField
    val CHUNK_DATA: DynamicArgType<ClientboundLevelChunkPacketData>

    @JvmField
    val CHUNK_DATA_S2C_PACKET: DynamicArgType<ClientboundLevelChunkWithLightPacket>

    @JvmField
    val LIGHT_DATA: DynamicArgType<ClientboundLightUpdatePacketData>

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
    val PLAYER: DynamicArgType<Player>

    @JvmField
    val SERVER_PLAYER: DynamicArgType<ServerPlayer>

    @JvmField
    val CONTAINER_USER: DynamicArgType<ContainerUser>

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
    val SERVER_CONFIGURATION_NETWORK_HANDLER: DynamicArgType<ServerConfigurationPacketListenerImpl>

    init {
        MINECRAFT_UNIT = arg("minecraft_unit")

        UNIT = arg(
            "unit",
            transform(::MINECRAFT_UNIT) { }
        )

        ITEM = arg(
            "item",
            transform(::ITEM_STACK, ItemStack::getItem)
        )

        ITEM_USAGE_CONTEXT = arg("item_usage_context")

        ITEM_PLACEMENT_CONTEXT = arg("item_placement_context")

        ITEM_STACK = arg(
            "item_stack",
            transform(::ITEM_USAGE_CONTEXT, UseOnContext::getItemInHand),
            transform(::ITEM_PLACEMENT_CONTEXT, BlockPlaceContext::getItemInHand)
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
            transform(::ITEM_USAGE_CONTEXT, UseOnContext::getHand)
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
            transform(::WORLD, Level::getRandom)
        )

        SERVER = arg(
            "server",
            transform(::SERVER_PLAYER) { player: ServerPlayer ->
                player.server
            },
            transform(::SERVER_WORLD, ServerLevel::getServer)
        )

        SCHEDULED_TICK_VIEW = arg(
            "schedule_tick_view",
            transform(::WORLD, Level::asIt)
        )

        WORLD = arg(
            "world",
            transform(::PLAYER, Player::level),
            transform(::SERVER_WORLD, ServerLevel::asIt),
        )

        SERVER_WORLD = arg(
            "server_world",
            transform(::WORLD) { world: Level -> world as? ServerLevel },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement -> placement.level as? ServerLevel }
        )

        CHUNK = arg(
            "chunk"
        )

        WORLD_CHUNK = arg(
            "world_chunk",
            transform(::CHUNK) { chunk: ChunkAccess -> chunk as LevelChunk }
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
            transform(::BLOCK_STATE, BlockState::getBlock)
        )

        BLOCK_POS = arg(
            "block_pos",
            transform(::ITEM_PLACEMENT_CONTEXT, BlockPlaceContext::getClickedPos)
        )

        BLOCK_ENTITY = arg(
            "block_entity",
            transform(::WORLD, ::BLOCK_POS, Level::getBlockEntity)
        )

        C_BLOCK_ENTITY = arg(
            "c_block_entity",
            transform(::WORLD, ::BLOCK_POS) { world, pos -> world.getBlockEntity(pos) as? ConiumBlockEntity }
        )

        BLOCK_STATE = arg(
            "block_state",
            transform(::FLUID_STATE, FluidState::createLegacyBlock)
        )

        FLUID_STATE = arg("fluid_state")

        FLUID = arg(
            "fluid",
            transform(::FLUID_STATE, FluidState::getType)
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
            transform(::PLAYER, Player::asIt)
        )

        PLAYER = arg(
            "player",
            transform(::ITEM_PLACEMENT_CONTEXT, BlockPlaceContext::getPlayer),
            transform(::ITEM_USAGE_CONTEXT, UseOnContext::getPlayer),
            transform(::LIVING_ENTITY) { entity: LivingEntity -> entity as? Player },
            transform(::SERVER_PLAYER, ServerPlayer::asIt),
        )

        SERVER_PLAYER = arg(
            "server_player",
            transform(::PLAYER) { player: Player -> player as? ServerPlayer },
            transform(::ITEM_PLACEMENT_CONTEXT) { placement: BlockPlaceContext -> placement.player as? ServerPlayer }
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
