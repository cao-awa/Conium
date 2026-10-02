@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.world.damagesource.CombatTracker
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.Brain
import net.minecraft.world.entity.ai.attributes.AttributeMap
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import java.util.Optional

val LivingEntity.mainHandStack: ItemStack get() = this.mainHandItem
val LivingEntity.offHandStack: ItemStack get() = this.offhandItem
val LivingEntity.despawnCounter: Int get() = this.noActionTime
val LivingEntity.damageTracker: CombatTracker get() = this.combatTracker
val LivingEntity.damageTiltYaw: Float get() = 0.0f
val LivingEntity.climbingPos: Optional<BlockPos> get() = this.lastClimbablePos
val LivingEntity.movementSpeed: Float get() = this.speed
val LivingEntity.armorVisibility: Float get() = this.armorCoverPercentage
val LivingEntity.attacker: LivingEntity? get() = this.lastAttacker
val LivingEntity.attacking: LivingEntity? get() = this.lastHurtByMob
val LivingEntity.attributes: AttributeMap get() = this.attributes
val LivingEntity.blockingItem: ItemStack? get() = this.useItem
val LivingEntity.brain: Brain<*> get() = this.brain
val LivingEntity.allArmorItems: Iterable<ItemStack>
    get() = listOf(
        this.getItemBySlot(EquipmentSlot.FEET),
        this.getItemBySlot(EquipmentSlot.LEGS),
        this.getItemBySlot(EquipmentSlot.CHEST),
        this.getItemBySlot(EquipmentSlot.HEAD)
    )
val LivingEntity.armor: Int get() = this.armorValue
val Player.activeItem: ItemStack get() = this.useItem
val Player.activeStatusEffects: Map<Holder<MobEffect>, MobEffectInstance> get() = this.activeEffectsMap
val Player.allArmorItems: Iterable<ItemStack> get() = (this as LivingEntity).allArmorItems
val Player.armor: Int get() = this.armorValue
