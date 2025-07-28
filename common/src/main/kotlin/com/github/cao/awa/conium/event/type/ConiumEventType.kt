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
import com.github.cao.awa.conium.craft.table.event.ConiumCraftingTableCraftedEvent
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
import com.github.cao.awa.conium.event.metadata.ConiumEmptyEventMetadata
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
import com.github.cao.awa.conium.network.event.ConiumServerConfiguredConnectionEvent
import com.github.cao.awa.conium.network.event.ConiumServerConfiguredConnectionEventMetadata
import com.github.cao.awa.conium.random.event.ConiumRandomEventMetadata
import com.github.cao.awa.conium.script.index.common.ConiumEventContext
import com.github.cao.awa.conium.server.event.random.ConiumServerRandomEventMetadata
import com.github.cao.awa.conium.server.event.tick.ConiumServerTickEventMetadata
import com.github.cao.awa.conium.server.event.tick.ConiumServerTickTailEventMetadata
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.fluid.Fluid
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationNetworkHandler
import net.minecraft.world.chunk.WorldChunk
import kotlin.reflect.KClass
import net.minecraft.util.Unit as MinecraftUnit

class ConiumEventType<I : Any, M: ConiumEventMetadata<I>, C: Any, N: ConiumEventMetadata<C>>(val name: String, val identityDescription: String) {
    companion object {
        @JvmField
        val INACTIVE: ConiumEventType<Unit, ConiumEmptyEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("inactive", "Unit")

        @JvmField
        val RANDOM: ConiumEventType<MinecraftUnit, ConiumRandomEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("random", "Unit")

        @JvmField
        val SERVER_RANDOM: ConiumEventType<MinecraftServer, ConiumServerRandomEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("server_random", "MinecraftServer")

//        @JvmField
//        // Unsupported now.
//        val CLIENT_RANDOM: ConiumEventType<MinecraftClient> = ConiumEventType("client_random", MinecraftClient::class)

        @JvmField
        val SERVER_TICK: ConiumEventType<MinecraftServer, ConiumServerTickEventMetadata, MinecraftServer, ConiumServerTickTailEventMetadata> = ConiumEventType("server_tick", "MinecraftServer")

        @JvmField
        val SERVER_TICK_TAIL: ConiumEventType<MinecraftServer, ConiumServerTickTailEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("server_tick_tail", "MinecraftServer")

        @JvmField
        val ITEM_USAGE_TICK: ConiumEventType<Item, ConiumItemUsageTickEventMetadata, Item, ConiumItemUsageTickedEventMetadata> = ConiumEventType("item_pre_usage_tick", "Item")

        @JvmField
        val ITEM_USAGE_TICKED: ConiumEventType<Item, ConiumItemUsageTickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("item_usage_tick", "Item")

        @JvmField
        val ITEM_INVENTORY_TICK: ConiumEventType<Item, ConiumItemInventoryTickEventMetadata, Item, ConiumItemInventoryTickedEventMetadata> = ConiumEventType("item_inventory_tick", "Item")

        @JvmField
        val ITEM_INVENTORY_TICKED: ConiumEventType<Item, ConiumItemInventoryTickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("item_inventory_ticked", "Item")

        @JvmField
        val ITEM_STACK_CLICK: ConiumEventType<Item, ConiumItemStackClickEventMetadata, Item, ConiumItemStackClickedEventMetadata> = ConiumEventType("item_stack_click", "Item")

        @JvmField
        val ITEM_STACK_CLICKED: ConiumEventType<Item, ConiumItemStackClickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("item_stack_clicked", "Item")

        @JvmField
        val ITEM_USE: ConiumEventType<Item, ConiumItemUseEventMetadata, Item, ConiumItemUsedEventMetadata> = ConiumEventType("item_use", "Item")

        @JvmField
        val ITEM_USED: ConiumEventType<Item, ConiumItemUsedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("item_used", "Item")

        @JvmField
        val ITEM_USE_ON_BLOCK: ConiumEventType<Item, ConiumItemUseOnBlockEventMetadata, Item, ConiumItemUsedOnBlockEventMetadata> = ConiumEventType("item_use_on_block", "Item")

        @JvmField
        val ITEM_USED_ON_BLOCK: ConiumEventType<Item, ConiumItemUsedOnBlockEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("item_used_on_block", "Item")

        @JvmField
        val ITEM_USE_ON_ENTITY: ConiumEventType<Item, ConiumItemUseOnEntityEventMetadata, Item, ConiumItemUsedOnEntityEventMetadata> = ConiumEventType("item_use_on_entity", "Item")

        @JvmField
        val ITEM_USED_ON_ENTITY: ConiumEventType<Item, ConiumItemUsedOnEntityEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("item_used_on_entity", "Item")

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
        val BREAKING_BLOCK: ConiumEventType<Block, ConiumBreakingBlockEventMetadata, Block, ConiumBreakBlockEventMetadata> = ConiumEventType("breaking_block", "Block")

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
        val BREAK_BLOCK: ConiumEventType<Block, ConiumBreakBlockEventMetadata, Block, ConiumBrokenBlockEventMetadata> = ConiumEventType("break_block", "Block")

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
        val BROKEN_BLOCK: ConiumEventType<Block, ConiumBrokenBlockEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("broken_block", "Block")

        @JvmField
        val PLACE_BLOCK: ConiumEventType<Block, ConiumPlaceBlockEventMetadata, Block, ConiumPlacedBlockEventMetadata> = ConiumEventType("place_block", "Block")

        @JvmField
        val PLACED_BLOCK: ConiumEventType<Block, ConiumPlacedBlockEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("placed_block", "Block")

        @JvmField
        val USE_BLOCK: ConiumEventType<Block, ConiumUseBlockEventMetadata, Block, ConiumUsedBlockEventMetadata> = ConiumEventType("use_block", "Block")

        @JvmField
        val USED_BLOCK: ConiumEventType<Block, ConiumUsedBlockEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("used_block", "Block")

        @JvmField
        val SHULKER_BOX_OPENING: ConiumEventType<Block, ConiumShulkerBoxOpeningEventMetadata, Block, ConiumShulkerBoxOpenedEventMetadata> = ConiumEventType("shulker_box_opening", "Block")

        @JvmField
        val SHULKER_BOX_OPENED: ConiumEventType<Block, ConiumShulkerBoxOpenedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("shulker_box_opened", "Block")

        @JvmField
        val SHULKER_BOX_CLOSING: ConiumEventType<Block, ConiumShulkerBoxClosingEventMetadata, Block, ConiumShulkerBoxClosedEventMetadata> = ConiumEventType("shulker_box_closing", "Block")

        @JvmField
        val SHULKER_BOX_CLOSED: ConiumEventType<Block, ConiumShulkerBoxClosedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("shulker_box_closed", "Block")

        @JvmField
        val CHEST_OPENING: ConiumEventType<Block, ConiumChestOpeningEventMetadata, Block, ConiumChestOpenedEventMetadata> = ConiumEventType("chest_opening", "Block")

        @JvmField
        val CHEST_OPENED: ConiumEventType<Block, ConiumChestOpenedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("chest_opened", "Block")

        @JvmField
        val CHEST_CLOSING: ConiumEventType<Block, ConiumChestClosingEventMetadata, Block, ConiumChestClosedEventMetadata> = ConiumEventType("chest_closing", "Block")

        @JvmField
        val CHEST_CLOSED: ConiumEventType<Block, ConiumChestClosedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("chest_closed", "Block")

        @JvmField
        val TRAPPED_CHEST_OPENING: ConiumEventType<Block, ConiumTrappedChestOpeningEventMetadata, Block, ConiumTrappedChestOpenedEventMetadata> = ConiumEventType("trapped_chest_opening", "Block")

        @JvmField
        val TRAPPED_CHEST_OPENED: ConiumEventType<Block, ConiumTrappedChestOpenedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("trapped_chest_opened", "Block")

        @JvmField
        val TRAPPED_CHEST_CLOSING: ConiumEventType<Block, ConiumTrappedChestClosingEventMetadata, Block, ConiumTrappedChestClosedEventMetadata> = ConiumEventType("trapped_chest_closing", "Block")

        @JvmField
        val TRAPPED_CHEST_CLOSED: ConiumEventType<Block, ConiumTrappedChestClosedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("trapped_chest_closed", "Block")

        @JvmField
        val ENTITY_TICK: ConiumEventType<EntityType<*>, ConiumEntityTickEventMetadata, EntityType<*>, ConiumEntityTickedEventMetadata> = ConiumEventType("entity_tick", "EntityType")

        @JvmField
        val ENTITY_TICKED: ConiumEventType<EntityType<*>, ConiumEntityTickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("entity_tick_ticked", "EntityType")

        // Entity.
        @JvmField
        val ENTITY_DAMAGE: ConiumEventType<EntityType<*>, ConiumEntityDamageEventMetadata, EntityType<*>, ConiumEntityDamagedEventMetadata> = ConiumEventType("entity_damage", "EntityType")

        @JvmField
        val ENTITY_DAMAGED: ConiumEventType<EntityType<*>, ConiumEntityDamagedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("entity_damaged", "EntityType")

        /**
         * The event where that entity was dying.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DIE: ConiumEventType<EntityType<*>, ConiumEntityDieEventMetadata, EntityType<*>, ConiumEntityDeadEventMetadata> = ConiumEventType("entity_die", "EntityType")

        /**
         * The event where that entity was died.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DEAD: ConiumEventType<EntityType<*>, ConiumEntityDeadEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("entity_dead", "EntityType")

        /**
         * The event where that entity trying to sleep.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_TRY_SLEEP: ConiumEventType<EntityType<*>, ConiumEntityTrySleepEventMetadata, EntityType<*>, ConiumEntitySleepEventMetadata> = ConiumEventType("entity_try_sleep", "EntityType")

        /**
         * The event where that entity sleeps.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_SLEEP: ConiumEventType<EntityType<*>, ConiumEntitySleepEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("entity_sleep", "EntityType")

        /**
         * The event where that entity trying to wake up.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_WAKE_UP: ConiumEventType<EntityType<*>, ConiumEntityWakeUpEventMetadata, EntityType<*>, ConiumEntityWakedUpEventMetadata> = ConiumEventType("entity_wake_up", "EntityType")

        /**
         * The event where that entity waked up.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_WAKED_UP: ConiumEventType<EntityType<*>, ConiumEntityWakedUpEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("entity_waked_up", "EntityType")

        @JvmField
        val ENTITY_SPRINT: ConiumEventType<EntityType<*>, ConiumEntitySprintEventMetadata, EntityType<*>, ConiumEntitySprintingEventMetadata> = ConiumEventType("entity_sprint", "EntityType")

        @JvmField
        val ENTITY_SPRINTING: ConiumEventType<EntityType<*>, ConiumEntitySprintingEventMetadata, EntityType<*>, ConiumEntityStopSprintEventMetadata> = ConiumEventType("entity_sprinting", "EntityType")

        @JvmField
        val ENTITY_STOP_SPRINT: ConiumEventType<EntityType<*>, ConiumEntityStopSprintEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("entity_stop_sprint", "EntityType")

        @JvmField
        val ENTITY_ON_FIRE: ConiumEventType<EntityType<*>, ConiumEntityOnFireEventMetadata, EntityType<*>, ConiumEntityExtinguishFireEventMetadata> = ConiumEventType("entity_on_fire", "EntityType")

        @JvmField
        val ENTITY_EXTINGUISH_FIRE: ConiumEventType<EntityType<*>, ConiumEntityExtinguishFireEventMetadata, EntityType<*>, ConiumEntityExtinguishedFireEventMetadata> = ConiumEventType("entity_extinguish_fire", "EntityType")

        @JvmField
        val ENTITY_EXTINGUISHED_FIRE: ConiumEventType<EntityType<*>, ConiumEntityExtinguishedFireEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("entity_extinguished_fire", "EntityType")

        @JvmField
        val BLOCK_SCHEDULE_TICK: ConiumEventType<Block, ConiumBlockScheduleTickEventMetadata, Block, ConiumBlockScheduleTickedEventMetadata> = ConiumEventType("block_schedule_tick", "Block")

        @JvmField
        val BLOCK_SCHEDULE_TICKED: ConiumEventType<Block, ConiumBlockScheduleTickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("block_schedule_ticked", "Block")

        @JvmField
        val FLUID_SCHEDULE_TICK: ConiumEventType<Fluid, ConiumFluidScheduleTickEventMetadata, Fluid, ConiumFluidScheduleTickedEventMetadata> = ConiumEventType("fluid_schedule_tick", "Fluid")

        @JvmField
        val FLUID_SCHEDULE_TICKED: ConiumEventType<Fluid, ConiumFluidScheduleTickedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("fluid_schedule_ticked", "Fluid")

        @JvmField
        val RECEIVE_CHUNK: ConiumEventType<ChunkDataS2CPacket, ConiumReceiveChunkEventMetadata, WorldChunk, ConiumReceivedChunkEventMetadata> = ConiumEventType("receive_chunk", "ChunkDataS2CPacket")

        @JvmField
        val RECEIVED_CHUNK: ConiumEventType<WorldChunk, ConiumReceivedChunkEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("received_chunk", "WorldChunk")

        @JvmField
        val SERVER_CONFIGURATION_CONNECTION: ConiumEventType<ServerConfigurationNetworkHandler, ConiumServerConfigurationConnectionEventMetadata, ServerConfigurationNetworkHandler, ConiumServerConfiguredConnectionEventMetadata> = ConiumEventType("server_configuration_connection", "ServerConfigurationNetworkHandler")

        @JvmField
        val SERVER_CONFIGURED_CONNECTION: ConiumEventType<ServerConfigurationNetworkHandler, ConiumServerConfiguredConnectionEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("server_configuration_connection", "ServerConfigurationNetworkHandler")

        @JvmField
        val CRAFTING_TABLE_CRAFTING: ConiumEventType<Item, ConiumCraftingTableCraftingEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("crafting_table_crafting", "Item")

        @JvmField
        val CRAFTING_TABLE_CRAFTED: ConiumEventType<Item, ConiumCraftingTableCraftedEventMetadata, Unit, ConiumEmptyEventMetadata> = ConiumEventType("crafting_table_crafted", "Item")
    }

    override fun toString(): String = StringBuilder().also { builder ->
        builder.append(this.name)
        builder.append("<")
        builder.append(this.identityDescription)
        builder.append(">")
    }.toString()
}
