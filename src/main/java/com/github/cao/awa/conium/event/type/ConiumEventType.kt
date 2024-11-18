package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.mixin.block.BlockStateMixin
import com.github.cao.awa.conium.mixin.client.interaction.ClientPlayerInteractionManagerMixin
import com.github.cao.awa.conium.mixin.server.interaction.ServerPlayerInteractionManagerMixin
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.server.MinecraftServer

class ConiumEventType<I> {
    companion object {
        @JvmField
        val SERVER_TICK: ConiumEventType<MinecraftServer> = ConiumEventType()

        @JvmField
        val ITEM_USE_ON_BLOCK: ConiumEventType<Item> = ConiumEventType()

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
        val BREAKING_BLOCK: ConiumEventType<Block> = ConiumEventType()

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
        val BREAK_BLOCK: ConiumEventType<Block> = ConiumEventType()

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
        val BROKEN_BLOCK: ConiumEventType<Block> = ConiumEventType()

        @JvmField
        val PLACE_BLOCK: ConiumEventType<Block> = ConiumEventType()

        @JvmField
        val PLACED_BLOCK: ConiumEventType<Block> = ConiumEventType()

        @JvmField
        val USE_BLOCK: ConiumEventType<Block> = ConiumEventType()

        @JvmField
        val USED_BLOCK: ConiumEventType<Block> = ConiumEventType()

        // Entity.
        @JvmField
        val ENTITY_DAMAGE: ConiumEventType<EntityType<*>> = ConiumEventType()

        @JvmField
        val ENTITY_DAMAGED: ConiumEventType<EntityType<*>> = ConiumEventType()

        /**
         * The event where that entity was dying.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DIE: ConiumEventType<EntityType<*>> = ConiumEventType()

        /**
         * The event where that entity was died.
         *
         * @since 1.0.0
         */
        @JvmField
        val ENTITY_DEAD: ConiumEventType<EntityType<*>> = ConiumEventType()
    }
}
