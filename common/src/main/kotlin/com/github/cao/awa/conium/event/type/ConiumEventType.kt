package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.block.event.breaking.type.ConiumBreakingBlockEventType
import com.github.cao.awa.conium.block.event.breaks.type.ConiumBreakBlockEventType
import com.github.cao.awa.conium.block.event.broken.type.ConiumBrokenBlockEventType
import com.github.cao.awa.conium.block.event.fluid.schedule.tick.type.ConiumFluidScheduleTickEventType
import com.github.cao.awa.conium.block.event.fluid.schedule.ticked.type.ConiumFluidScheduleTickedEventType
import com.github.cao.awa.conium.block.event.place.type.ConiumPlaceBlockEventType
import com.github.cao.awa.conium.block.event.placed.type.ConiumPlacedBlockEventType
import com.github.cao.awa.conium.block.event.schedule.tick.type.ConiumBlockScheduleTickEventType
import com.github.cao.awa.conium.block.event.schedule.ticked.type.ConiumBlockScheduleTickedEventType
import com.github.cao.awa.conium.block.event.use.type.ConiumUseBlockEventType
import com.github.cao.awa.conium.block.event.used.type.ConiumUsedBlockEventType
import com.github.cao.awa.conium.blockentity.event.chest.closed.type.ConiumChestClosedEventType
import com.github.cao.awa.conium.blockentity.event.chest.closing.type.ConiumChestClosingEventType
import com.github.cao.awa.conium.blockentity.event.chest.opened.type.ConiumChestOpenedEventType
import com.github.cao.awa.conium.blockentity.event.chest.opening.type.ConiumChestOpeningEventType
import com.github.cao.awa.conium.blockentity.event.chest.trapped.closed.type.ConiumTrappedChestClosedEventType
import com.github.cao.awa.conium.blockentity.event.chest.trapped.closing.type.ConiumTrappedChestClosingEventType
import com.github.cao.awa.conium.blockentity.event.chest.trapped.opened.type.ConiumTrappedChestOpenedEventType
import com.github.cao.awa.conium.blockentity.event.chest.trapped.opening.type.ConiumTrappedChestOpeningEventType
import com.github.cao.awa.conium.blockentity.event.shulker.closed.type.ConiumShulkerBoxClosedEventType
import com.github.cao.awa.conium.blockentity.event.shulker.closing.type.ConiumShulkerBoxClosingEventType
import com.github.cao.awa.conium.blockentity.event.shulker.opened.type.ConiumShulkerBoxOpenedEventType
import com.github.cao.awa.conium.blockentity.event.shulker.opening.type.ConiumShulkerBoxOpeningEventType
import com.github.cao.awa.conium.chunk.event.receive.metadata.ConiumReceiveChunkEventMetadata
import com.github.cao.awa.conium.chunk.event.receive.type.ConiumReceiveChunkEventType
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.chunk.event.received.type.ConiumReceivedChunkEventType
import com.github.cao.awa.conium.craft.table.event.crafted.metadata.ConiumCraftingTableCraftedEventMetadata
import com.github.cao.awa.conium.craft.table.event.crafted.type.ConiumCraftingTableCraftedEventType
import com.github.cao.awa.conium.craft.table.event.crafting.metadata.ConiumCraftingTableCraftingEventMetadata
import com.github.cao.awa.conium.craft.table.event.crafting.type.ConiumCraftingTableCraftingEventType
import com.github.cao.awa.conium.entity.event.damage.type.ConiumEntityDamageEventType
import com.github.cao.awa.conium.entity.event.damaged.type.ConiumEntityDamagedEventType
import com.github.cao.awa.conium.entity.event.dead.type.ConiumEntityDeadEventType
import com.github.cao.awa.conium.entity.event.die.type.ConiumEntityDieEventType
import com.github.cao.awa.conium.entity.event.extinguish.type.ConiumEntityExtinguishFireEventType
import com.github.cao.awa.conium.entity.event.extinguished.type.ConiumEntityExtinguishedFireEventType
import com.github.cao.awa.conium.entity.event.fire.type.ConiumEntityOnFireEventType
import com.github.cao.awa.conium.entity.event.rest.sleep.type.ConiumEntitySleepEventType
import com.github.cao.awa.conium.entity.event.rest.sleep.type.ConiumEntityTrySleepEventType
import com.github.cao.awa.conium.entity.event.rest.wake.type.ConiumEntityWakeUpEventType
import com.github.cao.awa.conium.entity.event.rest.waked.type.ConiumEntityWakedUpEventType
import com.github.cao.awa.conium.entity.event.sprint.type.ConiumEntitySprintEventType
import com.github.cao.awa.conium.entity.event.sprinting.stop.type.ConiumEntityStopSprintEventType
import com.github.cao.awa.conium.entity.event.sprinting.type.ConiumEntitySprintingEventType
import com.github.cao.awa.conium.entity.event.tick.type.ConiumEntityTickEventType
import com.github.cao.awa.conium.entity.event.ticked.type.ConiumEntityTickedEventType
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.item.event.inventory.tick.type.ConiumItemInventoryTickEventType
import com.github.cao.awa.conium.item.event.inventory.ticked.type.ConiumItemInventoryTickedEventType
import com.github.cao.awa.conium.item.event.stack.click.type.ConiumItemStackClickEventType
import com.github.cao.awa.conium.item.event.stack.clicked.type.ConiumItemStackClickedEventType
import com.github.cao.awa.conium.item.event.use.block.on.use.type.ConiumItemUseOnBlockEventType
import com.github.cao.awa.conium.item.event.use.block.on.used.type.ConiumItemUsedOnBlockEventType
import com.github.cao.awa.conium.item.event.use.entity.on.use.type.ConiumItemUseOnEntityEventType
import com.github.cao.awa.conium.item.event.use.entity.on.used.type.ConiumItemUsedOnEntityEventType
import com.github.cao.awa.conium.item.event.use.type.ConiumItemUseEventType
import com.github.cao.awa.conium.item.event.use.usage.tick.type.ConiumItemUsageTickEventType
import com.github.cao.awa.conium.item.event.use.usage.ticked.type.ConiumItemUsageTickedEventType
import com.github.cao.awa.conium.item.event.used.type.ConiumItemUsedEventType
import com.github.cao.awa.conium.mixin.block.AbstractBlockStateMixin
import com.github.cao.awa.conium.mixin.client.interaction.ClientPlayerInteractionManagerMixin
import com.github.cao.awa.conium.mixin.server.interaction.ServerPlayerInteractionManagerMixin
import com.github.cao.awa.conium.network.event.server.connection.configuration.metadata.ConiumServerConfigurationConnectionEventMetadata
import com.github.cao.awa.conium.network.event.server.connection.configuration.type.ConiumServerConfigurationConnectionEventType
import com.github.cao.awa.conium.network.event.server.connection.configured.metadata.ConiumServerConfiguredConnectionEventMetadata
import com.github.cao.awa.conium.network.event.server.connection.configured.type.ConiumServerConfiguredConnectionEventType
import com.github.cao.awa.conium.random.event.type.ConiumRandomEventType
import com.github.cao.awa.conium.server.event.random.type.ConiumServerRandomEventType
import com.github.cao.awa.conium.server.event.tick.start.type.ConiumServerTickEventType
import com.github.cao.awa.conium.server.event.tick.tail.type.ConiumServerTickTailEventType
import net.minecraft.item.Item
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket
import net.minecraft.server.network.ServerConfigurationNetworkHandler
import net.minecraft.world.chunk.WorldChunk

abstract class ConiumEventType<I : Any, M: ConiumEventMetadata<I, M>, C: Any, N: ConiumEventMetadata<C, N>>(val name: String, val identityDescription: String, val instance: () -> ConiumEvent<I, M, *, *>) {
    companion object {
        @JvmField
        val INACTIVE: ConiumInactiveEventType = ConiumInactiveEventType()

        @JvmField
        val RANDOM: ConiumRandomEventType = ConiumRandomEventType()

        @JvmField
        val SERVER_RANDOM: ConiumServerRandomEventType = ConiumServerRandomEventType()

//        @JvmField
//        // Unsupported now.
//        val CLIENT_RANDOM: ConiumEventType<MinecraftClient> = ConiumEventType("client_random", MinecraftClient::class)

        @JvmField
        val SERVER_TICK: ConiumServerTickEventType = ConiumServerTickEventType()

        @JvmField
        val SERVER_TICK_TAIL: ConiumServerTickTailEventType = ConiumServerTickTailEventType()

        @JvmField
        val ITEM_USAGE_TICK: ConiumItemUsageTickEventType = ConiumItemUsageTickEventType()

        @JvmField
        val ITEM_USAGE_TICKED: ConiumItemUsageTickedEventType = ConiumItemUsageTickedEventType()

        @JvmField
        val ITEM_INVENTORY_TICK: ConiumItemInventoryTickEventType = ConiumItemInventoryTickEventType()

        @JvmField
        val ITEM_INVENTORY_TICKED: ConiumItemInventoryTickedEventType = ConiumItemInventoryTickedEventType()

        @JvmField
        val ITEM_STACK_CLICK: ConiumItemStackClickEventType = ConiumItemStackClickEventType()

        @JvmField
        val ITEM_STACK_CLICKED: ConiumItemStackClickedEventType = ConiumItemStackClickedEventType()

        @JvmField
        val ITEM_USE: ConiumItemUseEventType = ConiumItemUseEventType()

        @JvmField
        val ITEM_USED: ConiumItemUsedEventType = ConiumItemUsedEventType()

        @JvmField
        val ITEM_USE_ON_BLOCK: ConiumItemUseOnBlockEventType = ConiumItemUseOnBlockEventType()

        @JvmField
        val ITEM_USED_ON_BLOCK: ConiumItemUsedOnBlockEventType = ConiumItemUsedOnBlockEventType()

        @JvmField
        val ITEM_USE_ON_ENTITY: ConiumItemUseOnEntityEventType = ConiumItemUseOnEntityEventType()

        @JvmField
        val ITEM_USED_ON_ENTITY: ConiumItemUsedOnEntityEventType = ConiumItemUsedOnEntityEventType()

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
        val BREAKING_BLOCK: ConiumBreakingBlockEventType = ConiumBreakingBlockEventType()

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
        val BREAK_BLOCK: ConiumBreakBlockEventType = ConiumBreakBlockEventType()

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
        val BROKEN_BLOCK: ConiumBrokenBlockEventType = ConiumBrokenBlockEventType()

        @JvmField
        val PLACE_BLOCK: ConiumPlaceBlockEventType = ConiumPlaceBlockEventType()

        @JvmField
        val PLACED_BLOCK: ConiumPlacedBlockEventType = ConiumPlacedBlockEventType()

        @JvmField
        val USE_BLOCK: ConiumUseBlockEventType = ConiumUseBlockEventType()

        @JvmField
        val USED_BLOCK: ConiumUsedBlockEventType = ConiumUsedBlockEventType()

        @JvmField
        val SHULKER_BOX_OPENING: ConiumShulkerBoxOpeningEventType = ConiumShulkerBoxOpeningEventType()

        @JvmField
        val SHULKER_BOX_OPENED: ConiumShulkerBoxOpenedEventType = ConiumShulkerBoxOpenedEventType()

        @JvmField
        val SHULKER_BOX_CLOSING: ConiumShulkerBoxClosingEventType = ConiumShulkerBoxClosingEventType()

        @JvmField
        val SHULKER_BOX_CLOSED: ConiumShulkerBoxClosedEventType = ConiumShulkerBoxClosedEventType()

        @JvmField
        val CHEST_OPENING: ConiumChestOpeningEventType = ConiumChestOpeningEventType()

        @JvmField
        val CHEST_OPENED: ConiumChestOpenedEventType = ConiumChestOpenedEventType()

        @JvmField
        val CHEST_CLOSING: ConiumChestClosingEventType = ConiumChestClosingEventType()

        @JvmField
        val CHEST_CLOSED: ConiumChestClosedEventType = ConiumChestClosedEventType()

        @JvmField
        val TRAPPED_CHEST_OPENING: ConiumTrappedChestOpeningEventType = ConiumTrappedChestOpeningEventType()

        @JvmField
        val TRAPPED_CHEST_OPENED: ConiumTrappedChestOpenedEventType = ConiumTrappedChestOpenedEventType()

        @JvmField
        val TRAPPED_CHEST_CLOSING: ConiumTrappedChestClosingEventType = ConiumTrappedChestClosingEventType()

        @JvmField
        val TRAPPED_CHEST_CLOSED: ConiumTrappedChestClosedEventType = ConiumTrappedChestClosedEventType()

        @JvmField
        val ENTITY_TICK: ConiumEntityTickEventType = ConiumEntityTickEventType()

        @JvmField
        val ENTITY_TICKED: ConiumEntityTickedEventType = ConiumEntityTickedEventType()

        // Entity.
        @JvmField
        val ENTITY_DAMAGE: ConiumEntityDamageEventType = ConiumEntityDamageEventType()

        @JvmField
        val ENTITY_DAMAGED: ConiumEntityDamagedEventType = ConiumEntityDamagedEventType()

        /**
         * The event where that entity was dying.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DIE: ConiumEntityDieEventType = ConiumEntityDieEventType()

        /**
         * The event where that entity was died.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DEAD: ConiumEntityDeadEventType = ConiumEntityDeadEventType()

        /**
         * The event where that entity trying to sleep.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_TRY_SLEEP: ConiumEntityTrySleepEventType = ConiumEntityTrySleepEventType()

        /**
         * The event where that entity sleeps.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_SLEEP: ConiumEntitySleepEventType = ConiumEntitySleepEventType()

        /**
         * The event where that entity trying to wake up.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_WAKE_UP: ConiumEntityWakeUpEventType = ConiumEntityWakeUpEventType()

        /**
         * The event where that entity waked up.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_WAKED_UP: ConiumEntityWakedUpEventType = ConiumEntityWakedUpEventType()

        @JvmField
        val ENTITY_SPRINT: ConiumEntitySprintEventType = ConiumEntitySprintEventType()

        @JvmField
        val ENTITY_SPRINTING: ConiumEntitySprintingEventType = ConiumEntitySprintingEventType()

        @JvmField
        val ENTITY_STOP_SPRINT: ConiumEntityStopSprintEventType = ConiumEntityStopSprintEventType()

        @JvmField
        val ENTITY_ON_FIRE: ConiumEntityOnFireEventType = ConiumEntityOnFireEventType()

        @JvmField
        val ENTITY_EXTINGUISH_FIRE: ConiumEntityExtinguishFireEventType = ConiumEntityExtinguishFireEventType()

        @JvmField
        val ENTITY_EXTINGUISHED_FIRE: ConiumEntityExtinguishedFireEventType = ConiumEntityExtinguishedFireEventType()

        @JvmField
        val BLOCK_SCHEDULE_TICK: ConiumBlockScheduleTickEventType = ConiumBlockScheduleTickEventType()

        @JvmField
        val BLOCK_SCHEDULE_TICKED: ConiumBlockScheduleTickedEventType = ConiumBlockScheduleTickedEventType()

        @JvmField
        val FLUID_SCHEDULE_TICK: ConiumFluidScheduleTickEventType = ConiumFluidScheduleTickEventType()

        @JvmField
        val FLUID_SCHEDULE_TICKED: ConiumFluidScheduleTickedEventType = ConiumFluidScheduleTickedEventType()

        @JvmField
        val RECEIVE_CHUNK: ConiumReceiveChunkEventType = ConiumReceiveChunkEventType()

        @JvmField
        val RECEIVED_CHUNK: ConiumReceivedChunkEventType = ConiumReceivedChunkEventType()

        @JvmField
        val SERVER_CONFIGURATION_CONNECTION: ConiumServerConfigurationConnectionEventType = ConiumServerConfigurationConnectionEventType()

        @JvmField
        val SERVER_CONFIGURED_CONNECTION: ConiumServerConfiguredConnectionEventType = ConiumServerConfiguredConnectionEventType()

        @JvmField
        val CRAFTING_TABLE_CRAFTING: ConiumCraftingTableCraftingEventType = ConiumCraftingTableCraftingEventType()

        @JvmField
        val CRAFTING_TABLE_CRAFTED: ConiumCraftingTableCraftedEventType = ConiumCraftingTableCraftedEventType()
    }

    override fun toString(): String = StringBuilder().also { builder ->
        builder.append(this.name)
        builder.append("<")
        builder.append(this.identityDescription)
        builder.append(">")
    }.toString()
}
