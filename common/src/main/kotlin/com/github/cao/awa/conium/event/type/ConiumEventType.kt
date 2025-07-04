package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.mixin.block.BlockStateMixin
import com.github.cao.awa.conium.mixin.client.interaction.ClientPlayerInteractionManagerMixin
import com.github.cao.awa.conium.mixin.server.interaction.ServerPlayerInteractionManagerMixin
import com.mojang.authlib.minecraft.client.MinecraftClient
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.fluid.Fluid
import net.minecraft.item.Item
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationNetworkHandler
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.chunk.WorldChunk
import kotlin.reflect.KClass
import net.minecraft.util.Unit as MinecraftUnit

class ConiumEventType<I : Any>(val name: String, val identityType: KClass<I>) {
    companion object {
        @JvmField
        val RANDOM: ConiumEventType<MinecraftUnit> = ConiumEventType("random", MinecraftUnit::class)

        @JvmField
        val SERVER_RANDOM: ConiumEventType<MinecraftServer> = ConiumEventType("server_random", MinecraftServer::class)

        @JvmField
        // Unsupported now.
        val CLIENT_RANDOM: ConiumEventType<MinecraftClient> = ConiumEventType("client_random", MinecraftClient::class)

        @JvmField
        val SERVER_TICK: ConiumEventType<MinecraftServer> = ConiumEventType("server_tick", MinecraftServer::class)

        @JvmField
        val SERVER_TICK_TAIL: ConiumEventType<MinecraftServer> = ConiumEventType("server_tick_tail", MinecraftServer::class)

        @JvmField
        val ITEM_USAGE_TICK: ConiumEventType<Item> = ConiumEventType("item_pre_usage_tick", Item::class)

        @JvmField
        val ITEM_USAGE_TICKED: ConiumEventType<Item> = ConiumEventType("item_usage_tick", Item::class)

        @JvmField
        val ITEM_INVENTORY_TICK: ConiumEventType<Item> = ConiumEventType("item_inventory_tick", Item::class)

        @JvmField
        val ITEM_INVENTORY_TICKED: ConiumEventType<Item> = ConiumEventType("item_inventory_ticked", Item::class)

        @JvmField
        val ITEM_STACK_CLICK: ConiumEventType<Item> = ConiumEventType("item_stack_click", Item::class)

        @JvmField
        val ITEM_STACK_CLICKED: ConiumEventType<Item> = ConiumEventType("item_stack_clicked", Item::class)

        @JvmField
        val ITEM_USE: ConiumEventType<Item> = ConiumEventType("item_use", Item::class)

        @JvmField
        val ITEM_USED: ConiumEventType<Item> = ConiumEventType("item_used", Item::class)

        @JvmField
        val ITEM_USE_ON_BLOCK: ConiumEventType<Item> = ConiumEventType("item_use_on_block", Item::class)

        @JvmField
        val ITEM_USED_ON_BLOCK: ConiumEventType<Item> = ConiumEventType("item_used_on_block", Item::class)

        @JvmField
        val ITEM_USE_ON_ENTITY: ConiumEventType<Item> = ConiumEventType("item_use_on_entity", Item::class)

        @JvmField
        val ITEM_USED_ON_ENTITY: ConiumEventType<Item> = ConiumEventType("item_used_on_entity", Item::class)

        // Block.
        /**
         * The event where that block is be mining.
         *
         * This event is cancelable.
         *
         * @see BlockStateMixin.breakingBlock
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmField
        val BREAKING_BLOCK: ConiumEventType<Block> = ConiumEventType("breaking_block", Block::class)

        /**
         * The event where that block is be breaking.
         *
         * This event is cancelable.
         *
         * ``This event is only for server side``
         *
         * @see ServerPlayerInteractionManagerMixin.tryBreakBlock
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmField
        val BREAK_BLOCK: ConiumEventType<Block> = ConiumEventType("break_block", Block::class)

        /**
         * The event where that block is be mined.
         *
         * This event is not cancelable.
         *
         * @see ServerPlayerInteractionManagerMixin.tryBreakBlock
         * @see ClientPlayerInteractionManagerMixin.breakBlock
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmField
        val BROKEN_BLOCK: ConiumEventType<Block> = ConiumEventType("broken_block", Block::class)

        @JvmField
        val PLACE_BLOCK: ConiumEventType<Block> = ConiumEventType("place_block", Block::class)

        @JvmField
        val PLACED_BLOCK: ConiumEventType<Block> = ConiumEventType("placed_block", Block::class)

        @JvmField
        val USE_BLOCK: ConiumEventType<Block> = ConiumEventType("use_block", Block::class)

        @JvmField
        val USED_BLOCK: ConiumEventType<Block> = ConiumEventType("used_block", Block::class)

        @JvmField
        val SHULKER_BOX_OPENING: ConiumEventType<Block> = ConiumEventType("shulker_box_opening", Block::class)

        @JvmField
        val SHULKER_BOX_OPENED: ConiumEventType<Block> = ConiumEventType("shulker_box_opened", Block::class)

        @JvmField
        val SHULKER_BOX_CLOSING: ConiumEventType<Block> = ConiumEventType("shulker_box_closing", Block::class)

        @JvmField
        val SHULKER_BOX_CLOSED: ConiumEventType<Block> = ConiumEventType("shulker_box_closed", Block::class)

        @JvmField
        val CHEST_OPENING: ConiumEventType<Block> = ConiumEventType("chest_opening", Block::class)

        @JvmField
        val CHEST_OPENED: ConiumEventType<Block> = ConiumEventType("chest_opened", Block::class)

        @JvmField
        val CHEST_CLOSING: ConiumEventType<Block> = ConiumEventType("chest_closing", Block::class)

        @JvmField
        val CHEST_CLOSED: ConiumEventType<Block> = ConiumEventType("chest_closed", Block::class)

        @JvmField
        val TRAPPED_CHEST_OPENING: ConiumEventType<Block> = ConiumEventType("trapped_chest_opening", Block::class)

        @JvmField
        val TRAPPED_CHEST_OPENED: ConiumEventType<Block> = ConiumEventType("trapped_chest_opened", Block::class)

        @JvmField
        val TRAPPED_CHEST_CLOSING: ConiumEventType<Block> = ConiumEventType("trapped_chest_closing", Block::class)

        @JvmField
        val TRAPPED_CHEST_CLOSED: ConiumEventType<Block> = ConiumEventType("trapped_chest_closed", Block::class)

        @JvmField
        val ENTITY_TICK: ConiumEventType<EntityType<*>> = ConiumEventType("entity_tick", EntityType::class)

        @JvmField
        val ENTITY_TICKED: ConiumEventType<EntityType<*>> = ConiumEventType("entity_tick_ticked", EntityType::class)

        // Entity.
        @JvmField
        val ENTITY_DAMAGE: ConiumEventType<EntityType<*>> = ConiumEventType("entity_damage", EntityType::class)

        @JvmField
        val ENTITY_DAMAGED: ConiumEventType<EntityType<*>> = ConiumEventType("entity_damaged", EntityType::class)

        /**
         * The event where that entity was dying.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DIE: ConiumEventType<EntityType<*>> = ConiumEventType("entity_die", EntityType::class)

        /**
         * The event where that entity was died.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DEAD: ConiumEventType<EntityType<*>> = ConiumEventType("entity_dead", EntityType::class)

        /**
         * The event where that entity trying to sleep.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_TRY_SLEEP: ConiumEventType<EntityType<*>> = ConiumEventType("entity_try_sleep", EntityType::class)

        /**
         * The event where that entity sleeps.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_SLEEP: ConiumEventType<EntityType<*>> = ConiumEventType("entity_sleep", EntityType::class)

        /**
         * The event where that entity trying to wake up.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_WAKE_UP: ConiumEventType<EntityType<*>> = ConiumEventType("entity_wake_up", EntityType::class)

        /**
         * The event where that entity waked up.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_WAKED_UP: ConiumEventType<EntityType<*>> = ConiumEventType("entity_waked_up", EntityType::class)

        @JvmField
        val ENTITY_SPRINT: ConiumEventType<EntityType<*>> = ConiumEventType("entity_sprint", EntityType::class)

        @JvmField
        val ENTITY_SPRINTING: ConiumEventType<EntityType<*>> = ConiumEventType("entity_sprinting", EntityType::class)

        @JvmField
        val ENTITY_STOP_SPRINT: ConiumEventType<EntityType<*>> = ConiumEventType("entity_stop_sprint", EntityType::class)

        @JvmField
        val ENTITY_ON_FIRE: ConiumEventType<EntityType<*>> = ConiumEventType("entity_on_fire", EntityType::class)

        @JvmField
        val ENTITY_EXTINGUISH_FIRE: ConiumEventType<EntityType<*>> = ConiumEventType("entity_extinguish_fire", EntityType::class)

        @JvmField
        val ENTITY_EXTINGUISHED_FIRE: ConiumEventType<EntityType<*>> = ConiumEventType("entity_extinguished_fire", EntityType::class)

        @JvmField
        val BLOCK_SCHEDULE_TICK: ConiumEventType<Block> = ConiumEventType("block_schedule_tick", Block::class)

        @JvmField
        val BLOCK_SCHEDULE_TICKED: ConiumEventType<Block> = ConiumEventType("block_schedule_ticked", Block::class)

        @JvmField
        val FLUID_SCHEDULE_TICK: ConiumEventType<Fluid> = ConiumEventType("fluid_schedule_tick", Fluid::class)

        @JvmField
        val FLUID_SCHEDULE_TICKED: ConiumEventType<Fluid> = ConiumEventType("fluid_schedule_ticked", Fluid::class)

        @JvmField
        val RECEIVE_CHUNK: ConiumEventType<ChunkDataS2CPacket> = ConiumEventType("receive_chunk", ChunkDataS2CPacket::class)

        @JvmField
        val RECEIVED_CHUNK: ConiumEventType<WorldChunk> = ConiumEventType("received_chunk", WorldChunk::class)

        @JvmField
        val SERVER_CONFIGURATION_CONNECTION: ConiumEventType<ServerConfigurationNetworkHandler> = ConiumEventType("server_configuration_connection", ServerConfigurationNetworkHandler::class)
    }

    override fun toString(): String = StringBuilder().also { builder ->
        builder.append(this.name)
        builder.append("<")
        builder.append(this.identityType.simpleName)
        builder.append(">")
    }.toString()
}
