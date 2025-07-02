@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import com.github.cao.awa.conium.mapping.yarn.*
import java.util.*


/**
 * See the mapping [LivingEntity](https://mappings.dev/1.21.4/net/minecraft/world/entity/LivingEntity.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val LivingEntity.mainHandStack: ItemStack by LivingEntity::mainHandStack
val LivingEntity.offHandStack: ItemStack by LivingEntity::offHandStack
val LivingEntity.despawnCounter: Int by LivingEntity::despawnCounter
val LivingEntity.damageTracker: DamageTracker by LivingEntity::damageTracker
val LivingEntity.damageTiltYaw: Float by LivingEntity::damageTiltYaw
val LivingEntity.climbingPos: Optional<BlockPos> by LivingEntity::climbingPos
val LivingEntity.movementSpeed: Float by LivingEntity::movementSpeed
val LivingEntity.armorVisibility: Float by LivingEntity::armorVisibility
val LivingEntity.attacker: LivingEntity? by LivingEntity::attacker
val LivingEntity.attacking: LivingEntity? by LivingEntity::attacking
val LivingEntity.attributes: AttributeContainer by LivingEntity::attributes
val LivingEntity.blockingItem: ItemStack? by LivingEntity::blockingItem
val LivingEntity.brain: Brain<*> by LivingEntity::brain
val PlayerEntity.activeItem: ItemStack by PlayerEntity::activeItem
val PlayerEntity.activeStatusEffects: Map<RegistryEntry<StatusEffect>, StatusEffectInstance> by PlayerEntity::activeStatusEffects
val PlayerEntity.allArmorItems: Iterable<ItemStack> by PlayerEntity::allArmorItems
val PlayerEntity.armor: Int by PlayerEntity::armor