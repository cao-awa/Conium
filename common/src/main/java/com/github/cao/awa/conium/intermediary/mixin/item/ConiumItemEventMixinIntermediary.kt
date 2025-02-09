package com.github.cao.awa.conium.intermediary.mixin.item

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.intermediary.mixin.ConiumEventMixinIntermediary.Companion.fireEvent
import com.github.cao.awa.conium.intermediary.mixin.ConiumEventMixinIntermediary.Companion.fireEventCancelable
import com.github.cao.awa.conium.intermediary.mixin.ConiumEventMixinIntermediary.Companion.fireInheritedCascadedResultEvent
import com.github.cao.awa.conium.mapping.yarn.LivingEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.screen.slot.Slot
import net.minecraft.server.MinecraftServer
import net.minecraft.util.ActionResult
import net.minecraft.util.ClickType
import net.minecraft.util.Hand
import net.minecraft.world.World

/**
 * Conium item event intermediary triggers.
 *
 * @see MinecraftServer
 * @see ConiumEventType.ITEM_USE
 * @see ConiumEventType.ITEM_USED
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumItemEventMixinIntermediary {
    companion object {
        /**
         * Trigger the item use event when the player using item.
         *
         * @see MinecraftServer
         * @see ConiumEventType.ITEM_USE
         * @see ConiumEventType.ITEM_USED
         *
         * @param world the user world
         * @param user the user
         * @param hand the hand which using item
         * @param itemStack the item stack that using
         *
         * @return flag that noted should do mixin cancel
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireItemUseEvent(world: World, user: PlayerEntity, hand: Hand, itemStack: ItemStack): Boolean {
            val item: Item = itemStack.item

            return fireEventCancelable(
                ConiumEventType.ITEM_USE,
                item
            ) { context: ConiumEventContext<*> ->
                context[ConiumEventArgTypes.WORLD] = world
                context[ConiumEventArgTypes.PLAYER] = user
                context[ConiumEventArgTypes.HAND] = hand
                context[ConiumEventArgTypes.ITEM_STACK] = itemStack
            }
        }

        /**
         * Trigger the item used event when the player used item.
         *
         * @see MinecraftServer
         * @see ConiumEventType.ITEM_USE
         * @see ConiumEventType.ITEM_USED
         *
         * @param world the user world
         * @param user the user
         * @param hand the hand which using item
         * @param itemStack the item stack that used
         *
         * @return flag that noted should do mixin cancel
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireItemUsedEvent(world: World, user: PlayerEntity, hand: Hand, itemStack: ItemStack, actionResult: ActionResult) {
            val item: Item = itemStack.item

            fireEvent(
                ConiumEventType.ITEM_USED,
                item
            ) { context: ConiumEventContext<*> ->
                context[ConiumEventArgTypes.WORLD] = world
                context[ConiumEventArgTypes.PLAYER] = user
                context[ConiumEventArgTypes.HAND] = hand
                context[ConiumEventArgTypes.ITEM_STACK] = itemStack
                context[ConiumEventArgTypes.ACTION_RESULT] = actionResult
            }
        }

        /**
         * Trigger the item use on block events when the player using item to a block.
         *
         * @see MinecraftServer
         * @see ConiumEventType.ITEM_USE
         * @see ConiumEventType.ITEM_USED
         *
         * @param usageContext the item usage context
         *
         * @return the action result of item use on block
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireItemUseOnBlock(usageContext: ItemUsageContext): ActionResult {
            val itemStack: ItemStack = usageContext.stack
            val item: Item = itemStack.item

            return fireInheritedCascadedResultEvent(
                ConiumEventType.ITEM_USE_ON_BLOCK,
                ConiumEventType.ITEM_USED_ON_BLOCK,
                item,
                { context: ConiumEventContext<*> ->
                    context[ConiumEventArgTypes.ITEM_USAGE_CONTEXT] = usageContext
                },
                { result: ActionResult, context: ConiumEventContext<*> ->
                    context[ConiumEventArgTypes.ACTION_RESULT] = result
                },
                {
                    item.useOnBlock(usageContext)
                },
                ActionResult.FAIL
            )
        }

        /**
         * Trigger the item use on entity events when the player using item to a block.
         *
         * @see MinecraftServer
         * @see ConiumEventType.ITEM_USE
         * @see ConiumEventType.ITEM_USED
         *
         * @param itemStack the item stack that used
         * @param user the user
         * @param target the item using to target
         * @param hand the hand which using item
         *
         * @return the action result of item use on block
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireItemUseOnEntity(itemStack: ItemStack, user: PlayerEntity, target: LivingEntity, hand: Hand): ActionResult {
            val item: Item = itemStack.item

            return fireInheritedCascadedResultEvent(
                ConiumEventType.ITEM_USE_ON_ENTITY,
                ConiumEventType.ITEM_USED_ON_ENTITY,
                item,
                { context: ConiumEventContext<*> ->
                    context[ConiumEventArgTypes.ITEM_STACK] = itemStack
                    context[ConiumEventArgTypes.PLAYER] = user
                    context[ConiumEventArgTypes.LIVING_ENTITY] = target
                    context[ConiumEventArgTypes.HAND] = hand
                },
                { result, context ->
                    context[ConiumEventArgTypes.ACTION_RESULT] = result
                },
                {
                    item.useOnEntity(itemStack, user, target, hand)
                },
                ActionResult.FAIL
            )
        }

        /**
         * Trigger the item pre-usage tick event when the player using item.
         *
         * @see MinecraftServer
         * @see ConiumEventType.ITEM_USAGE_TICK
         * @see ConiumEventType.ITEM_USAGE_TICKED
         *
         * @param world the user world
         * @param user the user
         * @param itemStack the item stack that using
         * @param remainingUseTicks the item usage remaining use ticks
         *
         * @return flag that noted should do mixin cancel
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireItemPreUsageTickEvent(world: World, user: LivingEntity, itemStack: ItemStack, remainingUseTicks: Int): Boolean {
            val item: Item = itemStack.item

            return fireEventCancelable(
                ConiumEventType.ITEM_USAGE_TICK,
                item
            ) { context: ConiumEventContext<*> ->
                // Fill the context args.
                context[ConiumEventArgTypes.WORLD] = world
                context[ConiumEventArgTypes.LIVING_ENTITY] = user
                context[ConiumEventArgTypes.ITEM_STACK] = itemStack
                context[ConiumEventArgTypes.REMAINING_USE_TICKS] = remainingUseTicks
            }
        }

        /**
         * Trigger the item usage tick event when the player using item.
         *
         * @see MinecraftServer
         * @see ConiumEventType.ITEM_USAGE_TICK
         * @see ConiumEventType.ITEM_USAGE_TICKED
         *
         * @param world the user world
         * @param user the user
         * @param itemStack the item stack that using
         * @param remainingUseTicks the item usage remaining use ticks
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireItemUsageTickEvent(world: World, user: LivingEntity, itemStack: ItemStack, remainingUseTicks: Int) {
            val item: Item = itemStack.item

            fireEvent(
                ConiumEventType.ITEM_USAGE_TICKED,
                item
            ) { context: ConiumEventContext<*> ->
                // Fill the context args.
                context[ConiumEventArgTypes.WORLD] = world
                context[ConiumEventArgTypes.LIVING_ENTITY] = user
                context[ConiumEventArgTypes.ITEM_STACK] = itemStack
                context[ConiumEventArgTypes.REMAINING_USE_TICKS] = remainingUseTicks
            }
        }

        /**
         * Trigger the item inventory tick event when item stack loaded in inventory.
         *
         * @see MinecraftServer
         * @see ConiumEventType.ITEM_USAGE_TICK
         * @see ConiumEventType.ITEM_USAGE_TICKED
         *
         * @param world the holder world
         * @param holder the entity holder
         * @param itemStack the item stack that ticking now
         * @param slotNumber the slot number in the inventory
         * @param slotNumber the item stack selects status in inventory
         *
         * @return flag that noted should do mixin cancel
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireItemInventoryTickEvent(world: World, holder: Entity, itemStack: ItemStack, slotNumber: Int, isSelected:Boolean): Boolean {
            val item: Item = itemStack.item

            return fireEventCancelable(
                ConiumEventType.ITEM_INVENTORY_TICK,
                item
            ) { context: ConiumEventContext<*> ->
                // Fill the context args.
                context[ConiumEventArgTypes.WORLD] = world
                context[ConiumEventArgTypes.ENTITY] = holder
                context[ConiumEventArgTypes.ITEM_STACK] = itemStack
                context[ConiumEventArgTypes.SLOT_NUMBER] = slotNumber
                context[ConiumEventArgTypes.SELECT_STATUS] = isSelected
            }
        }

        /**
         * Trigger the item inventory ticked event when item stack loaded in inventory.
         *
         * @see MinecraftServer
         * @see ConiumEventType.ITEM_USAGE_TICK
         * @see ConiumEventType.ITEM_USAGE_TICKED
         *
         * @param world the holder world
         * @param holder the entity holder
         * @param itemStack the item stack that ticked
         * @param slotNumber the slot number in the inventory
         * @param slotNumber the item stack selects status in inventory
         *
         * @return flag that noted should do mixin cancel
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireItemInventoryTickedEvent(world: World, holder: Entity, itemStack: ItemStack, slotNumber: Int, isSelected:Boolean): Boolean {
            val item: Item = itemStack.item

            return fireEventCancelable(
                ConiumEventType.ITEM_INVENTORY_TICKED,
                item
            ) { context: ConiumEventContext<*> ->
                // Fill the context args.
                context[ConiumEventArgTypes.WORLD] = world
                context[ConiumEventArgTypes.ENTITY] = holder
                context[ConiumEventArgTypes.ITEM_STACK] = itemStack
                context[ConiumEventArgTypes.SLOT_NUMBER] = slotNumber
                context[ConiumEventArgTypes.SELECT_STATUS] = isSelected
            }
        }

        /**
         * Trigger the item stack clicked event when the player clicked item stack.
         *
         * @see MinecraftServer
         * @see ConiumEventType.ITEM_STACK_CLICK
         * @see ConiumEventType.ITEM_STACK_CLICKED
         *
         * @param player the player that clicking
         * @param itemStack the item stack that clicked
         * @param slot the slot type
         * @param clickType the click type to this clicking
         *
         * @return flag that noted should do mixin cancel
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun fireItemStackClickedEvent(player: PlayerEntity, itemStack: ItemStack, slot: Slot, clickType: ClickType): Boolean {
            val item: Item = itemStack.item

            return fireEventCancelable(
                ConiumEventType.ITEM_STACK_CLICKED,
                item
            ) { context: ConiumEventContext<*> ->
                // Fill the context args.
                context[ConiumEventArgTypes.ENTITY] = player
                context[ConiumEventArgTypes.ITEM_STACK] = itemStack
                context[ConiumEventArgTypes.SLOT] = slot
                context[ConiumEventArgTypes.CLICK_TYPE] = clickType
            }
        }
    }
}
