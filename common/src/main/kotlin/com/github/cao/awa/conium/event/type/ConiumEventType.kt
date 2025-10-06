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
import com.github.cao.awa.conium.craft.table.event.ConiumCraftingTableCraftedEventMetadata
import com.github.cao.awa.conium.craft.table.event.ConiumCraftingTableCraftingEventMetadata
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
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.metadata.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
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
import com.github.cao.awa.conium.mixin.block.AbstractBlockStateMixin
import com.github.cao.awa.conium.mixin.client.interaction.ClientPlayerInteractionManagerMixin
import com.github.cao.awa.conium.mixin.server.interaction.ServerPlayerInteractionManagerMixin
import com.github.cao.awa.conium.network.event.ConiumServerConfigurationConnectionEventMetadata
import com.github.cao.awa.conium.network.event.ConiumServerConfiguredConnectionEventMetadata
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
import net.minecraft.util.Unit as MinecraftUnit

abstract class ConiumEventType<I : Any, M: ConiumEventMetadata<I>, C: Any, N: ConiumEventMetadata<C>>(val name: String, val identityDescription: String, val instance: () -> ConiumEvent<I, M, *>) {
    companion object {
        @JvmField
        val INACTIVE: ConiumNoCancelableEventType<Unit, ConiumEmptyEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("inactive", "Unit", ConiumEvent.Companion::inactive)

        @JvmField
        val RANDOM: ConiumNoCancelableEventType<MinecraftUnit, ConiumRandomEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("random", "Unit", ConiumEvent.Companion::random)

        @JvmField
        val SERVER_RANDOM: ConiumNoCancelableEventType<MinecraftServer, ConiumServerRandomEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("server_random", "MinecraftServer", ConiumEvent.Companion::serverRandom)

//        @JvmField
//        // Unsupported now.
//        val CLIENT_RANDOM: ConiumEventType<MinecraftClient> = ConiumEventType("client_random", MinecraftClient::class)

        @JvmField
        val SERVER_TICK: ConiumNoCancelableEventType<MinecraftServer, ConiumServerTickEventMetadata, MinecraftServer, ConiumServerTickTailEventMetadata> = ConiumNoCancelableEventType("server_tick", "MinecraftServer", ConiumEvent.Companion::serverTick)

        @JvmField
        val SERVER_TICK_TAIL: ConiumNoCancelableEventType<MinecraftServer, ConiumServerTickTailEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("server_tick_tail", "MinecraftServer",ConiumEvent.Companion::serverTickTail)

        @JvmField
        val ITEM_USAGE_TICK: ConiumCancelableEventType<Item, ConiumItemUsageTickEventMetadata, Item, ConiumItemUsageTickedEventMetadata> = ConiumCancelableEventType("item_pre_usage_tick", "Item",ConiumEvent.Companion::itemUsageTick)

        @JvmField
        val ITEM_USAGE_TICKED: ConiumNoCancelableEventType<Item, ConiumItemUsageTickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("item_usage_tick", "Item",ConiumEvent.Companion::itemUsageTicked)

        @JvmField
        val ITEM_INVENTORY_TICK: ConiumCancelableEventType<Item, ConiumItemInventoryTickEventMetadata, Item, ConiumItemInventoryTickedEventMetadata> = ConiumCancelableEventType("item_inventory_tick", "Item",ConiumEvent.Companion::itemInventoryTick)

        @JvmField
        val ITEM_INVENTORY_TICKED: ConiumNoCancelableEventType<Item, ConiumItemInventoryTickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("item_inventory_ticked", "Item",ConiumEvent.Companion::itemInventoryTicked)

        @JvmField
        val ITEM_STACK_CLICK: ConiumCancelableEventType<Item, ConiumItemStackClickEventMetadata, Item, ConiumItemStackClickedEventMetadata> = ConiumCancelableEventType("item_stack_click", "Item",ConiumEvent.Companion::itemStackClick)

        @JvmField
        val ITEM_STACK_CLICKED: ConiumNoCancelableEventType<Item, ConiumItemStackClickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("item_stack_clicked", "Item",ConiumEvent.Companion::itemStackClicked)

        @JvmField
        val ITEM_USE: ConiumCancelableEventType<Item, ConiumItemUseEventMetadata, Item, ConiumItemUsedEventMetadata> = ConiumCancelableEventType("item_use", "Item",ConiumEvent.Companion::itemUse)

        @JvmField
        val ITEM_USED: ConiumNoCancelableEventType<Item, ConiumItemUsedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("item_used", "Item",ConiumEvent.Companion::itemUsed)

        @JvmField
        val ITEM_USE_ON_BLOCK: ConiumCancelableEventType<Item, ConiumItemUseOnBlockEventMetadata, Item, ConiumItemUsedOnBlockEventMetadata> = ConiumCancelableEventType("item_use_on_block", "Item",ConiumEvent.Companion::itemUseOnBlock)

        @JvmField
        val ITEM_USED_ON_BLOCK: ConiumNoCancelableEventType<Item, ConiumItemUsedOnBlockEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("item_used_on_block", "Item",ConiumEvent.Companion::itemUsedOnBlock)

        @JvmField
        val ITEM_USE_ON_ENTITY: ConiumCancelableEventType<Item, ConiumItemUseOnEntityEventMetadata, Item, ConiumItemUsedOnEntityEventMetadata> = ConiumCancelableEventType("item_use_on_entity", "Item",ConiumEvent.Companion::itemUseOnEntity)

        @JvmField
        val ITEM_USED_ON_ENTITY: ConiumNoCancelableEventType<Item, ConiumItemUsedOnEntityEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("item_used_on_entity", "Item",ConiumEvent.Companion::itemUsedOnEntity)

        // Block.
        /**
         * The event where that block is be mining.
         *
         * This event is cancelable.
         *
         * @see AbstractBlockStateMixin.breakingBlock
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmField
        val BREAKING_BLOCK: ConiumCancelableEventType<Block, ConiumBreakingBlockEventMetadata, Block, ConiumBreakBlockEventMetadata> = ConiumCancelableEventType("breaking_block", "Block",ConiumEvent.Companion::breakingBlock)

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
        val BREAK_BLOCK: ConiumCancelableEventType<Block, ConiumBreakBlockEventMetadata, Block, ConiumBrokenBlockEventMetadata> = ConiumCancelableEventType("break_block", "Block",ConiumEvent.Companion::breakBlock)

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
        val BROKEN_BLOCK: ConiumNoCancelableEventType<Block, ConiumBrokenBlockEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("broken_block", "Block",ConiumEvent.Companion::brokenBlock)

        @JvmField
        val PLACE_BLOCK: ConiumCancelableEventType<Block, ConiumPlaceBlockEventMetadata, Block, ConiumPlacedBlockEventMetadata> = ConiumCancelableEventType("place_block", "Block",ConiumEvent.Companion::placeBlock)

        @JvmField
        val PLACED_BLOCK: ConiumNoCancelableEventType<Block, ConiumPlacedBlockEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("placed_block", "Block",ConiumEvent.Companion::placedBlock)

        @JvmField
        val USE_BLOCK: ConiumCancelableEventType<Block, ConiumUseBlockEventMetadata, Block, ConiumUsedBlockEventMetadata> = ConiumCancelableEventType("use_block", "Block",ConiumEvent.Companion::useBlock)

        @JvmField
        val USED_BLOCK: ConiumNoCancelableEventType<Block, ConiumUsedBlockEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("used_block", "Block",ConiumEvent.Companion::usedBlock)

        @JvmField
        val SHULKER_BOX_OPENING: ConiumCancelableEventType<Block, ConiumShulkerBoxOpeningEventMetadata, Block, ConiumShulkerBoxOpenedEventMetadata> = ConiumCancelableEventType("shulker_box_opening", "Block",ConiumEvent.Companion::shulkerBoxOpening)

        @JvmField
        val SHULKER_BOX_OPENED: ConiumNoCancelableEventType<Block, ConiumShulkerBoxOpenedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("shulker_box_opened", "Block",ConiumEvent.Companion::shulkerBoxOpened)

        @JvmField
        val SHULKER_BOX_CLOSING: ConiumCancelableEventType<Block, ConiumShulkerBoxClosingEventMetadata, Block, ConiumShulkerBoxClosedEventMetadata> = ConiumCancelableEventType("shulker_box_closing", "Block",ConiumEvent.Companion::shulkerBoxClosing)

        @JvmField
        val SHULKER_BOX_CLOSED: ConiumNoCancelableEventType<Block, ConiumShulkerBoxClosedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("shulker_box_closed", "Block",ConiumEvent.Companion::shulkerBoxClosed)

        @JvmField
        val CHEST_OPENING: ConiumCancelableEventType<Block, ConiumChestOpeningEventMetadata, Block, ConiumChestOpenedEventMetadata> = ConiumCancelableEventType("chest_opening", "Block",ConiumEvent.Companion::chestOpening)

        @JvmField
        val CHEST_OPENED: ConiumNoCancelableEventType<Block, ConiumChestOpenedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("chest_opened", "Block",ConiumEvent.Companion::chestOpened)

        @JvmField
        val CHEST_CLOSING: ConiumCancelableEventType<Block, ConiumChestClosingEventMetadata, Block, ConiumChestClosedEventMetadata> = ConiumCancelableEventType("chest_closing", "Block",ConiumEvent.Companion::chestClosing)

        @JvmField
        val CHEST_CLOSED: ConiumNoCancelableEventType<Block, ConiumChestClosedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("chest_closed", "Block",ConiumEvent.Companion::chestClosed)

        @JvmField
        val TRAPPED_CHEST_OPENING: ConiumCancelableEventType<Block, ConiumTrappedChestOpeningEventMetadata, Block, ConiumTrappedChestOpenedEventMetadata> = ConiumCancelableEventType("trapped_chest_opening", "Block",ConiumEvent.Companion::trappedChestOpening)

        @JvmField
        val TRAPPED_CHEST_OPENED: ConiumNoCancelableEventType<Block, ConiumTrappedChestOpenedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("trapped_chest_opened", "Block",ConiumEvent.Companion::trappedChestOpened)

        @JvmField
        val TRAPPED_CHEST_CLOSING: ConiumCancelableEventType<Block, ConiumTrappedChestClosingEventMetadata, Block, ConiumTrappedChestClosedEventMetadata> = ConiumCancelableEventType("trapped_chest_closing", "Block",ConiumEvent.Companion::trappedChestClosing)

        @JvmField
        val TRAPPED_CHEST_CLOSED: ConiumNoCancelableEventType<Block, ConiumTrappedChestClosedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("trapped_chest_closed", "Block",ConiumEvent.Companion::trappedChestClosed)

        @JvmField
        val ENTITY_TICK: ConiumCancelableEventType<EntityType<*>, ConiumEntityTickEventMetadata, EntityType<*>, ConiumEntityTickedEventMetadata> = ConiumCancelableEventType("entity_tick", "EntityType",ConiumEvent.Companion::entityTick)

        @JvmField
        val ENTITY_TICKED: ConiumNoCancelableEventType<EntityType<*>, ConiumEntityTickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("entity_tick_ticked", "EntityType",ConiumEvent.Companion::entityTicked)

        // Entity.
        @JvmField
        val ENTITY_DAMAGE: ConiumCancelableEventType<EntityType<*>, ConiumEntityDamageEventMetadata, EntityType<*>, ConiumEntityDamagedEventMetadata> = ConiumCancelableEventType("entity_damage", "EntityType",ConiumEvent.Companion::entityDamage)

        @JvmField
        val ENTITY_DAMAGED: ConiumNoCancelableEventType<EntityType<*>, ConiumEntityDamagedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("entity_damaged", "EntityType",ConiumEvent.Companion::entityDamaged)

        /**
         * The event where that entity was dying.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DIE: ConiumCancelableEventType<EntityType<*>, ConiumEntityDieEventMetadata, EntityType<*>, ConiumEntityDeadEventMetadata> = ConiumCancelableEventType("entity_die", "EntityType",ConiumEvent.Companion::entityDie)

        /**
         * The event where that entity was died.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DEAD: ConiumNoCancelableEventType<EntityType<*>, ConiumEntityDeadEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("entity_dead", "EntityType",ConiumEvent.Companion::entityDead)

        /**
         * The event where that entity trying to sleep.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_TRY_SLEEP: ConiumCancelableEventType<EntityType<*>, ConiumEntityTrySleepEventMetadata, EntityType<*>, ConiumEntitySleepEventMetadata> = ConiumCancelableEventType("entity_try_sleep", "EntityType",ConiumEvent.Companion::entityTrySleep)

        /**
         * The event where that entity sleeps.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_SLEEP: ConiumNoCancelableEventType<EntityType<*>, ConiumEntitySleepEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("entity_sleep", "EntityType",ConiumEvent.Companion::entitySleep)

        /**
         * The event where that entity trying to wake up.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_WAKE_UP: ConiumCancelableEventType<EntityType<*>, ConiumEntityWakeUpEventMetadata, EntityType<*>, ConiumEntityWakedUpEventMetadata> = ConiumCancelableEventType("entity_wake_up", "EntityType",ConiumEvent.Companion::entityWakeUp)

        /**
         * The event where that entity waked up.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_WAKED_UP: ConiumNoCancelableEventType<EntityType<*>, ConiumEntityWakedUpEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("entity_waked_up", "EntityType",ConiumEvent.Companion::entityWakedUp)

        @JvmField
        val ENTITY_SPRINT: ConiumCancelableEventType<EntityType<*>, ConiumEntitySprintEventMetadata, EntityType<*>, ConiumEntitySprintingEventMetadata> = ConiumCancelableEventType("entity_sprint", "EntityType",ConiumEvent.Companion::entitySprint)

        @JvmField
        val ENTITY_SPRINTING: ConiumNoCancelableEventType<EntityType<*>, ConiumEntitySprintingEventMetadata, EntityType<*>, ConiumEntityStopSprintEventMetadata> = ConiumNoCancelableEventType("entity_sprinting", "EntityType",ConiumEvent.Companion::entitySprinting)

        @JvmField
        val ENTITY_STOP_SPRINT: ConiumCancelableEventType<EntityType<*>, ConiumEntityStopSprintEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumCancelableEventType("entity_stop_sprint", "EntityType",ConiumEvent.Companion::entityStopSprint)

        @JvmField
        val ENTITY_ON_FIRE: ConiumCancelableEventType<EntityType<*>, ConiumEntityOnFireEventMetadata, EntityType<*>, ConiumEntityExtinguishFireEventMetadata> = ConiumCancelableEventType("entity_on_fire", "EntityType",ConiumEvent.Companion::entityOnFire)

        @JvmField
        val ENTITY_EXTINGUISH_FIRE: ConiumCancelableEventType<EntityType<*>, ConiumEntityExtinguishFireEventMetadata, EntityType<*>, ConiumEntityExtinguishedFireEventMetadata> = ConiumCancelableEventType("entity_extinguish_fire", "EntityType",ConiumEvent.Companion::entityExtinguishFire)

        @JvmField
        val ENTITY_EXTINGUISHED_FIRE: ConiumNoCancelableEventType<EntityType<*>, ConiumEntityExtinguishedFireEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("entity_extinguished_fire", "EntityType",ConiumEvent.Companion::entityExtinguishedFire)

        @JvmField
        val BLOCK_SCHEDULE_TICK: ConiumCancelableEventType<Block, ConiumBlockScheduleTickEventMetadata, Block, ConiumBlockScheduleTickedEventMetadata> = ConiumCancelableEventType("block_schedule_tick", "Block",ConiumEvent.Companion::blockScheduleTick)

        @JvmField
        val BLOCK_SCHEDULE_TICKED: ConiumNoCancelableEventType<Block, ConiumBlockScheduleTickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("block_schedule_ticked", "Block",ConiumEvent.Companion::blockScheduleTicked)

        @JvmField
        val FLUID_SCHEDULE_TICK: ConiumCancelableEventType<Fluid, ConiumFluidScheduleTickEventMetadata, Fluid, ConiumFluidScheduleTickedEventMetadata> = ConiumCancelableEventType("fluid_schedule_tick", "Fluid",ConiumEvent.Companion::fluidScheduleTick)

        @JvmField
        val FLUID_SCHEDULE_TICKED: ConiumNoCancelableEventType<Fluid, ConiumFluidScheduleTickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("fluid_schedule_ticked", "Fluid",ConiumEvent.Companion::fluidScheduleTicked)

        @JvmField
        val RECEIVE_CHUNK: ConiumCancelableEventType<ChunkDataS2CPacket, ConiumReceiveChunkEventMetadata, WorldChunk, ConiumReceivedChunkEventMetadata> = ConiumCancelableEventType("receive_chunk", "ChunkDataS2CPacket",ConiumEvent.Companion::receiveChunk)

        @JvmField
        val RECEIVED_CHUNK: ConiumNoCancelableEventType<WorldChunk, ConiumReceivedChunkEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("received_chunk", "WorldChunk",ConiumEvent.Companion::receivedChunk)

        @JvmField
        val SERVER_CONFIGURATION_CONNECTION: ConiumCancelableEventType<ServerConfigurationNetworkHandler, ConiumServerConfigurationConnectionEventMetadata, ServerConfigurationNetworkHandler, ConiumServerConfiguredConnectionEventMetadata> = ConiumCancelableEventType("server_configuration_connection", "ServerConfigurationNetworkHandler",ConiumEvent.Companion::enterConfigurationConnection)

        @JvmField
        val SERVER_CONFIGURED_CONNECTION: ConiumNoCancelableEventType<ServerConfigurationNetworkHandler, ConiumServerConfiguredConnectionEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("server_configuration_connection", "ServerConfigurationNetworkHandler",ConiumEvent.Companion::enterConfiguredConnection)

        @JvmField
        val CRAFTING_TABLE_CRAFTING: ConiumCancelableEventType<Item, ConiumCraftingTableCraftingEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumCancelableEventType("crafting_table_crafting", "Item",ConiumEvent.Companion::craftingTableCrafting)

        @JvmField
        val CRAFTING_TABLE_CRAFTED: ConiumNoCancelableEventType<Item, ConiumCraftingTableCraftedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumNoCancelableEventType("crafting_table_crafted", "Item",ConiumEvent.Companion::craftingTableCrafted)
    }

    override fun toString(): String = StringBuilder().also { builder ->
        builder.append(this.name)
        builder.append("<")
        builder.append(this.identityDescription)
        builder.append(">")
    }.toString()
}
