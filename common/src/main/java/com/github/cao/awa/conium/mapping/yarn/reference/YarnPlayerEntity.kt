@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import com.github.cao.awa.conium.mapping.yarn.*

/**
 * See the mapping [PlayerEntity](https://mappings.dev/1.21.4/net/minecraft/world/entity/player/Player.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val PlayerEntity.abilities: PlayerAbilities by PlayerEntity::abilities
val PlayerEntity.absorptionAmount: Float by PlayerEntity::absorptionAmount
val PlayerEntity.armorItems: Iterable<ItemStack> by PlayerEntity::armorItems
val PlayerEntity.activeHand: Hand by PlayerEntity::activeHand
val PlayerEntity.mainArm: Arm by PlayerEntity::mainArm
val PlayerEntity.permissionLevel: Int by PlayerEntity::permissionLevel
val PlayerEntity.isCreative: Boolean
    get() = this.isCreative
val PlayerEntity.attackCooldownProgressPerTick: Float by PlayerEntity::attackCooldownProgressPerTick
val PlayerEntity.blockInteractionRange: Double by PlayerEntity::blockInteractionRange
val PlayerEntity.damageTiltYaw: Float by PlayerEntity::damageTiltYaw
val PlayerEntity.defaultPortalCooldown: Int by PlayerEntity::defaultPortalCooldown
val PlayerEntity.displayName: Text? by PlayerEntity::displayName
val PlayerEntity.styledDisplayName: Text? by PlayerEntity::styledDisplayName
val PlayerEntity.enchantingTableSeed: Int by PlayerEntity::enchantingTableSeed
val PlayerEntity.enderChestInventory: EnderChestInventory by PlayerEntity::enderChestInventory
val PlayerEntity.entityInteractionRange: Double by PlayerEntity::entityInteractionRange
val PlayerEntity.equippedItems: Iterable<ItemStack> by PlayerEntity::equippedItems
val PlayerEntity.glidingTicks: Int by PlayerEntity::glidingTicks
val PlayerEntity.isGliding: Boolean
    get() = this.isGliding
