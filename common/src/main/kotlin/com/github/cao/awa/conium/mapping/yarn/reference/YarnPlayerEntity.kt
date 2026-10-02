@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.HumanoidArm
import net.minecraft.world.entity.player.Abilities
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.PlayerEnderChestContainer
import net.minecraft.world.item.ItemStack

/**
 * See the mapping [Player](https://mappings.dev/1.21.4/net/minecraft/world/entity/player/Player.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val Player.abilities: Abilities get() = this.abilities
val Player.absorptionAmount: Float get() = this.absorptionAmount
val Player.armorItems: Iterable<ItemStack>
    get() = listOf(
        this.getItemBySlot(EquipmentSlot.FEET),
        this.getItemBySlot(EquipmentSlot.LEGS),
        this.getItemBySlot(EquipmentSlot.CHEST),
        this.getItemBySlot(EquipmentSlot.HEAD)
    )
val Player.activeHand: InteractionHand get() = this.usedItemHand
val Player.mainArm: HumanoidArm get() = this.mainArm
val Player.permissionLevel: Int get() = 0
val Player.isCreativeMode: Boolean get() = this.isCreative
val Player.attackCooldownProgressPerTick: Float get() = this.getAttackStrengthScale(0.0f)
val Player.blockInteractionRange: Double get() = this.blockInteractionRange()
val Player.damageTiltYaw: Float get() = 0.0f
val Player.defaultPortalCooldown: Int get() = this.dimensionChangingDelay
val Player.displayName: Component? get() = this.name
val Player.styledDisplayName: Component? get() = this.displayName
val Player.enchantingTableSeed: Int get() = this.enchantmentSeed
val Player.enderChestInventory: PlayerEnderChestContainer get() = this.enderChestInventory
val Player.entityInteractionRange: Double get() = this.entityInteractionRange()
val Player.equippedItems: Iterable<ItemStack>
    get() = listOf(
        this.getItemBySlot(EquipmentSlot.MAINHAND),
        this.getItemBySlot(EquipmentSlot.OFFHAND)
    )
val Player.glidingTicks: Int get() = this.fallFlyingTicks
val Player.isFallFlying: Boolean get() = this.isFallFlying
