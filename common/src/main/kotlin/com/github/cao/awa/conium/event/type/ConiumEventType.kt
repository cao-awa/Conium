package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.block.entity.event.chest.close.ConiumChestClosedEventMetadata
import com.github.cao.awa.conium.block.entity.event.chest.close.ConiumChestClosingEventMetadata
import com.github.cao.awa.conium.block.entity.event.chest.close.trapped.ConiumTrappedChestClosedEventMetadata
import com.github.cao.awa.conium.block.entity.event.chest.close.trapped.ConiumTrappedChestClosingEventMetadata
import com.github.cao.awa.conium.block.entity.event.chest.open.ConiumChestOpenedEventMetadata
import com.github.cao.awa.conium.block.entity.event.chest.open.ConiumChestOpeningEventMetadata
import com.github.cao.awa.conium.block.entity.event.chest.open.trapped.ConiumTrappedChestOpenedEventMetadata
import com.github.cao.awa.conium.block.entity.event.chest.open.trapped.ConiumTrappedChestOpeningEventMetadata
import com.github.cao.awa.conium.block.entity.event.shulker.close.ConiumShulkerBoxClosingEventMetadata
import com.github.cao.awa.conium.block.entity.event.shulker.close.ConiumShulkerBoxClosedEventMetadata
import com.github.cao.awa.conium.block.entity.event.shulker.open.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.block.entity.event.shulker.open.ConiumShulkerBoxOpeningEventMetadata
import com.github.cao.awa.conium.block.event.breaking.ConiumBreakBlockEventMetadata
import com.github.cao.awa.conium.block.event.breaking.ConiumBreakingBlockEventMetadata
import com.github.cao.awa.conium.block.event.breaking.ConiumBrokenBlockEventMetadata
import com.github.cao.awa.conium.block.event.place.ConiumPlaceBlockEventMetadata
import com.github.cao.awa.conium.block.event.place.ConiumPlacedBlockEventMetadata
import com.github.cao.awa.conium.block.event.tick.ConiumBlockScheduleTickEventMetadata
import com.github.cao.awa.conium.block.event.tick.ConiumBlockScheduleTickedEventMetadata
import com.github.cao.awa.conium.block.event.tick.fluid.ConiumFluidScheduleTickEventMetadata
import com.github.cao.awa.conium.block.event.tick.fluid.ConiumFluidScheduleTickedEventMetadata
import com.github.cao.awa.conium.block.event.use.ConiumUseBlockEventMetadata
import com.github.cao.awa.conium.block.event.use.ConiumUsedBlockEventMetadata
import com.github.cao.awa.conium.chunk.event.receive.ConiumReceiveChunkEventMetadata
import com.github.cao.awa.conium.chunk.event.receive.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.entity.event.damage.ConiumEntityDamageEventMetadata
import com.github.cao.awa.conium.entity.event.damage.ConiumEntityDamagedEventMetadata
import com.github.cao.awa.conium.entity.event.die.ConiumEntityDeadEventMetadata
import com.github.cao.awa.conium.entity.event.die.ConiumEntityDieEventMetadata
import com.github.cao.awa.conium.entity.event.fire.ConiumEntityOnFireEventMetadata
import com.github.cao.awa.conium.entity.event.fire.extinguish.ConiumEntityExtinguishFireEventMetadata
import com.github.cao.awa.conium.entity.event.fire.extinguish.ConiumEntityExtinguishedFireEventMetadata
import com.github.cao.awa.conium.entity.event.rest.sleep.ConiumEntitySleepEventMetadata
import com.github.cao.awa.conium.entity.event.rest.sleep.ConiumEntityTrySleepEventMetadata
import com.github.cao.awa.conium.entity.event.rest.wake.ConiumEntityWakeUpEventMetadata
import com.github.cao.awa.conium.entity.event.rest.wake.ConiumEntityWakedUpEventMetadata
import com.github.cao.awa.conium.entity.event.sprint.ConiumEntitySprintEventMetadata
import com.github.cao.awa.conium.entity.event.sprint.ConiumEntitySprintingEventMetadata
import com.github.cao.awa.conium.entity.event.sprint.ConiumEntityStopSprintEventMetadata
import com.github.cao.awa.conium.entity.event.tick.ConiumEntityTickEventMetadata
import com.github.cao.awa.conium.entity.event.tick.ConiumEntityTickedEventMetadata
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.item.event.stack.click.ConiumItemStackClickEventMetadata
import com.github.cao.awa.conium.item.event.stack.click.ConiumItemStackClickedEventMetadata
import com.github.cao.awa.conium.item.event.tick.inventory.ConiumItemInventoryTickEventMetadata
import com.github.cao.awa.conium.item.event.tick.inventory.ConiumItemInventoryTickedEventMetadata
import com.github.cao.awa.conium.item.event.use.ConiumItemUseEventMetadata
import com.github.cao.awa.conium.item.event.use.ConiumItemUsedEventMetadata
import com.github.cao.awa.conium.item.event.use.block.ConiumItemUseOnBlockEventMetadata
import com.github.cao.awa.conium.item.event.use.block.ConiumItemUsedOnBlockEventMetadata
import com.github.cao.awa.conium.item.event.use.entity.ConiumItemUseOnEntityEventMetadata
import com.github.cao.awa.conium.item.event.use.entity.ConiumItemUsedOnEntityEventMetadata
import com.github.cao.awa.conium.item.event.use.usage.ConiumItemUsageTickEventMetadata
import com.github.cao.awa.conium.item.event.use.usage.ConiumItemUsageTickedEventMetadata
import com.github.cao.awa.conium.mixin.block.BlockStateMixin
import com.github.cao.awa.conium.mixin.client.interaction.ClientPlayerInteractionManagerMixin
import com.github.cao.awa.conium.mixin.server.interaction.ServerPlayerInteractionManagerMixin
import com.github.cao.awa.conium.network.event.ConiumServerConfigurationConnectionEventMetadata
import com.github.cao.awa.conium.random.event.ConiumRandomEventMetadata
import com.github.cao.awa.conium.server.event.random.ConiumServerRandomEventMetadata
import com.github.cao.awa.conium.server.event.tick.ConiumServerTickEventMetadata
import com.github.cao.awa.conium.server.event.tick.ConiumServerTickTailEventMetadata
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.fluid.Fluid
import net.minecraft.item.Item
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationNetworkHandler
import net.minecraft.world.chunk.WorldChunk
import kotlin.reflect.KClass
import net.minecraft.util.Unit as MinecraftUnit

class ConiumEventType<I : Any, M: ConiumEventMetadata>(val name: String, val identityType: KClass<I>) {
    companion object {
        @JvmField
        val RANDOM: ConiumEventType<MinecraftUnit, ConiumRandomEventMetadata> = ConiumEventType("random", MinecraftUnit::class)

        @JvmField
        val SERVER_RANDOM: ConiumEventType<MinecraftServer, ConiumServerRandomEventMetadata> = ConiumEventType("server_random", MinecraftServer::class)

//        @JvmField
//        // Unsupported now.
//        val CLIENT_RANDOM: ConiumEventType<MinecraftClient> = ConiumEventType("client_random", MinecraftClient::class)

        @JvmField
        val SERVER_TICK: ConiumEventType<MinecraftServer, ConiumServerTickEventMetadata> = ConiumEventType("server_tick", MinecraftServer::class)

        @JvmField
        val SERVER_TICK_TAIL: ConiumEventType<MinecraftServer, ConiumServerTickTailEventMetadata> = ConiumEventType("server_tick_tail", MinecraftServer::class)

        @JvmField
        val ITEM_USAGE_TICK: ConiumEventType<Item, ConiumItemUsageTickEventMetadata> = ConiumEventType("item_pre_usage_tick", Item::class)

        @JvmField
        val ITEM_USAGE_TICKED: ConiumEventType<Item, ConiumItemUsageTickedEventMetadata> = ConiumEventType("item_usage_tick", Item::class)

        @JvmField
        val ITEM_INVENTORY_TICK: ConiumEventType<Item, ConiumItemInventoryTickEventMetadata> = ConiumEventType("item_inventory_tick", Item::class)

        @JvmField
        val ITEM_INVENTORY_TICKED: ConiumEventType<Item, ConiumItemInventoryTickedEventMetadata> = ConiumEventType("item_inventory_ticked", Item::class)

        @JvmField
        val ITEM_STACK_CLICK: ConiumEventType<Item, ConiumItemStackClickEventMetadata> = ConiumEventType("item_stack_click", Item::class)

        @JvmField
        val ITEM_STACK_CLICKED: ConiumEventType<Item, ConiumItemStackClickedEventMetadata> = ConiumEventType("item_stack_clicked", Item::class)

        @JvmField
        val ITEM_USE: ConiumEventType<Item, ConiumItemUseEventMetadata> = ConiumEventType("item_use", Item::class)

        @JvmField
        val ITEM_USED: ConiumEventType<Item, ConiumItemUsedEventMetadata> = ConiumEventType("item_used", Item::class)

        @JvmField
        val ITEM_USE_ON_BLOCK: ConiumEventType<Item, ConiumItemUseOnBlockEventMetadata> = ConiumEventType("item_use_on_block", Item::class)

        @JvmField
        val ITEM_USED_ON_BLOCK: ConiumEventType<Item, ConiumItemUsedOnBlockEventMetadata> = ConiumEventType("item_used_on_block", Item::class)

        @JvmField
        val ITEM_USE_ON_ENTITY: ConiumEventType<Item, ConiumItemUseOnEntityEventMetadata> = ConiumEventType("item_use_on_entity", Item::class)

        @JvmField
        val ITEM_USED_ON_ENTITY: ConiumEventType<Item, ConiumItemUsedOnEntityEventMetadata> = ConiumEventType("item_used_on_entity", Item::class)

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
        val BREAKING_BLOCK: ConiumEventType<Block, ConiumBreakingBlockEventMetadata> = ConiumEventType("breaking_block", Block::class)

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
        val BREAK_BLOCK: ConiumEventType<Block, ConiumBreakBlockEventMetadata> = ConiumEventType("break_block", Block::class)

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
        val BROKEN_BLOCK: ConiumEventType<Block, ConiumBrokenBlockEventMetadata> = ConiumEventType("broken_block", Block::class)

        @JvmField
        val PLACE_BLOCK: ConiumEventType<Block, ConiumPlaceBlockEventMetadata> = ConiumEventType("place_block", Block::class)

        @JvmField
        val PLACED_BLOCK: ConiumEventType<Block, ConiumPlacedBlockEventMetadata> = ConiumEventType("placed_block", Block::class)

        @JvmField
        val USE_BLOCK: ConiumEventType<Block, ConiumUseBlockEventMetadata> = ConiumEventType("use_block", Block::class)

        @JvmField
        val USED_BLOCK: ConiumEventType<Block, ConiumUsedBlockEventMetadata> = ConiumEventType("used_block", Block::class)

        @JvmField
        val SHULKER_BOX_OPENING: ConiumEventType<Block, ConiumShulkerBoxOpeningEventMetadata> = ConiumEventType("shulker_box_opening", Block::class)

        @JvmField
        val SHULKER_BOX_OPENED: ConiumEventType<Block, ConiumShulkerBoxOpenedEventMetadata> = ConiumEventType("shulker_box_opened", Block::class)

        @JvmField
        val SHULKER_BOX_CLOSING: ConiumEventType<Block, ConiumShulkerBoxClosingEventMetadata> = ConiumEventType("shulker_box_closing", Block::class)

        @JvmField
        val SHULKER_BOX_CLOSED: ConiumEventType<Block, ConiumShulkerBoxClosedEventMetadata> = ConiumEventType("shulker_box_closed", Block::class)

        @JvmField
        val CHEST_OPENING: ConiumEventType<Block, ConiumChestOpeningEventMetadata> = ConiumEventType("chest_opening", Block::class)

        @JvmField
        val CHEST_OPENED: ConiumEventType<Block, ConiumChestOpenedEventMetadata> = ConiumEventType("chest_opened", Block::class)

        @JvmField
        val CHEST_CLOSING: ConiumEventType<Block, ConiumChestClosingEventMetadata> = ConiumEventType("chest_closing", Block::class)

        @JvmField
        val CHEST_CLOSED: ConiumEventType<Block, ConiumChestClosedEventMetadata> = ConiumEventType("chest_closed", Block::class)

        @JvmField
        val TRAPPED_CHEST_OPENING: ConiumEventType<Block, ConiumTrappedChestOpeningEventMetadata> = ConiumEventType("trapped_chest_opening", Block::class)

        @JvmField
        val TRAPPED_CHEST_OPENED: ConiumEventType<Block, ConiumTrappedChestOpenedEventMetadata> = ConiumEventType("trapped_chest_opened", Block::class)

        @JvmField
        val TRAPPED_CHEST_CLOSING: ConiumEventType<Block, ConiumTrappedChestClosingEventMetadata> = ConiumEventType("trapped_chest_closing", Block::class)

        @JvmField
        val TRAPPED_CHEST_CLOSED: ConiumEventType<Block, ConiumTrappedChestClosedEventMetadata> = ConiumEventType("trapped_chest_closed", Block::class)

        @JvmField
        val ENTITY_TICK: ConiumEventType<EntityType<*>, ConiumEntityTickEventMetadata> = ConiumEventType("entity_tick", EntityType::class)

        @JvmField
        val ENTITY_TICKED: ConiumEventType<EntityType<*>, ConiumEntityTickedEventMetadata> = ConiumEventType("entity_tick_ticked", EntityType::class)

        // Entity.
        @JvmField
        val ENTITY_DAMAGE: ConiumEventType<EntityType<*>, ConiumEntityDamageEventMetadata> = ConiumEventType("entity_damage", EntityType::class)

        @JvmField
        val ENTITY_DAMAGED: ConiumEventType<EntityType<*>, ConiumEntityDamagedEventMetadata> = ConiumEventType("entity_damaged", EntityType::class)

        /**
         * The event where that entity was dying.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DIE: ConiumEventType<EntityType<*>, ConiumEntityDieEventMetadata> = ConiumEventType("entity_die", EntityType::class)

        /**
         * The event where that entity was died.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DEAD: ConiumEventType<EntityType<*>, ConiumEntityDeadEventMetadata> = ConiumEventType("entity_dead", EntityType::class)

        /**
         * The event where that entity trying to sleep.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_TRY_SLEEP: ConiumEventType<EntityType<*>, ConiumEntityTrySleepEventMetadata> = ConiumEventType("entity_try_sleep", EntityType::class)

        /**
         * The event where that entity sleeps.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_SLEEP: ConiumEventType<EntityType<*>, ConiumEntitySleepEventMetadata> = ConiumEventType("entity_sleep", EntityType::class)

        /**
         * The event where that entity trying to wake up.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_WAKE_UP: ConiumEventType<EntityType<*>, ConiumEntityWakeUpEventMetadata> = ConiumEventType("entity_wake_up", EntityType::class)

        /**
         * The event where that entity waked up.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_WAKED_UP: ConiumEventType<EntityType<*>, ConiumEntityWakedUpEventMetadata> = ConiumEventType("entity_waked_up", EntityType::class)

        @JvmField
        val ENTITY_SPRINT: ConiumEventType<EntityType<*>, ConiumEntitySprintEventMetadata> = ConiumEventType("entity_sprint", EntityType::class)

        @JvmField
        val ENTITY_SPRINTING: ConiumEventType<EntityType<*>, ConiumEntitySprintingEventMetadata> = ConiumEventType("entity_sprinting", EntityType::class)

        @JvmField
        val ENTITY_STOP_SPRINT: ConiumEventType<EntityType<*>, ConiumEntityStopSprintEventMetadata> = ConiumEventType("entity_stop_sprint", EntityType::class)

        @JvmField
        val ENTITY_ON_FIRE: ConiumEventType<EntityType<*>, ConiumEntityOnFireEventMetadata> = ConiumEventType("entity_on_fire", EntityType::class)

        @JvmField
        val ENTITY_EXTINGUISH_FIRE: ConiumEventType<EntityType<*>, ConiumEntityExtinguishFireEventMetadata> = ConiumEventType("entity_extinguish_fire", EntityType::class)

        @JvmField
        val ENTITY_EXTINGUISHED_FIRE: ConiumEventType<EntityType<*>, ConiumEntityExtinguishedFireEventMetadata> = ConiumEventType("entity_extinguished_fire", EntityType::class)

        @JvmField
        val BLOCK_SCHEDULE_TICK: ConiumEventType<Block, ConiumBlockScheduleTickEventMetadata> = ConiumEventType("block_schedule_tick", Block::class)

        @JvmField
        val BLOCK_SCHEDULE_TICKED: ConiumEventType<Block, ConiumBlockScheduleTickedEventMetadata> = ConiumEventType("block_schedule_ticked", Block::class)

        @JvmField
        val FLUID_SCHEDULE_TICK: ConiumEventType<Fluid, ConiumFluidScheduleTickEventMetadata> = ConiumEventType("fluid_schedule_tick", Fluid::class)

        @JvmField
        val FLUID_SCHEDULE_TICKED: ConiumEventType<Fluid, ConiumFluidScheduleTickedEventMetadata> = ConiumEventType("fluid_schedule_ticked", Fluid::class)

        @JvmField
        val RECEIVE_CHUNK: ConiumEventType<ChunkDataS2CPacket, ConiumReceiveChunkEventMetadata> = ConiumEventType("receive_chunk", ChunkDataS2CPacket::class)

        @JvmField
        val RECEIVED_CHUNK: ConiumEventType<WorldChunk, ConiumReceivedChunkEventMetadata> = ConiumEventType("received_chunk", WorldChunk::class)

        @JvmField
        val SERVER_CONFIGURATION_CONNECTION: ConiumEventType<ServerConfigurationNetworkHandler, ConiumServerConfigurationConnectionEventMetadata> = ConiumEventType("server_configuration_connection", ServerConfigurationNetworkHandler::class)
    }

    override fun toString(): String = StringBuilder().also { builder ->
        builder.append(this.name)
        builder.append("<")
        builder.append(this.identityType.simpleName)
        builder.append(">")
    }.toString()
}
