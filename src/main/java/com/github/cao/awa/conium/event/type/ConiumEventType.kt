package com.github.cao.awa.conium.event.type

import com.github.cao.awa.conium.mixin.block.BlockStateMixin
import com.github.cao.awa.conium.mixin.client.interaction.ClientPlayerInteractionManagerMixin
import com.github.cao.awa.conium.mixin.server.interaction.ServerPlayerInteractionManagerMixin

enum class ConiumEventType {
    SERVER_TICK,

    ITEM_USE_ON_BLOCK,

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
    BREAKING_BLOCK,

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
    BREAK_BLOCK,

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
    BROKEN_BLOCK,
    PLACE_BLOCK,
    PLACED_BLOCK,

    USE_BLOCK,
    USED_BLOCK,

    // Entity.
    ENTITY_DAMAGE,
    ENTITY_DAMAGED,

    /**
     * The event where that entity was dying.
     *
     * @since 1.0.0
     */
    ENTITY_DIE,

    /**
     * The event where that entity was died.
     *
     * @since 1.0.0
     */
    ENTITY_DEAD,
}
