@file:Suppress("unused")

package com.github.cao.awa.conium.mapping.yarn

// Basic Minecraft classes
typealias Identifier = net.minecraft.resources.Identifier
typealias ResourceLocation = net.minecraft.resources.Identifier
typealias ResourceKey<T> = net.minecraft.resources.ResourceKey<T>

// Text & Chat
typealias Text = net.minecraft.network.chat.Component
typealias MutableText = net.minecraft.network.chat.MutableComponent
typealias Formatting = net.minecraft.ChatFormatting

// Math & Physics
typealias Vec3d = net.minecraft.world.phys.Vec3
typealias Vec3 = net.minecraft.world.phys.Vec3
typealias Vec3i = net.minecraft.core.Vec3i
typealias Vec2f = net.minecraft.world.phys.Vec2
typealias Vec2 = net.minecraft.world.phys.Vec2
typealias Box = net.minecraft.world.phys.AABB
typealias AABB = net.minecraft.world.phys.AABB
typealias BlockPos = net.minecraft.core.BlockPos
typealias Direction = net.minecraft.core.Direction
typealias ChunkPos = net.minecraft.world.level.ChunkPos
typealias ChunkSectionPos = net.minecraft.core.SectionPos
typealias MathHelper = net.minecraft.util.Mth
typealias Mth = net.minecraft.util.Mth

// Hit results
typealias HitResult = net.minecraft.world.phys.HitResult
typealias BlockHitResult = net.minecraft.world.phys.BlockHitResult
typealias EntityHitResult = net.minecraft.world.phys.EntityHitResult

// World & Levels
typealias World = net.minecraft.world.level.Level
typealias Level = net.minecraft.world.level.Level
typealias ServerWorld = net.minecraft.server.level.ServerLevel
typealias ServerLevel = net.minecraft.server.level.ServerLevel
typealias ClientWorld = net.minecraft.client.multiplayer.ClientLevel
typealias ClientLevel = net.minecraft.client.multiplayer.ClientLevel
typealias ScheduledTickView = net.minecraft.world.level.ScheduledTickAccess
typealias MinecraftServer = net.minecraft.server.MinecraftServer

// Items & Blocks
typealias Item = net.minecraft.world.item.Item
typealias ItemStack = net.minecraft.world.item.ItemStack
typealias ItemConvertible = net.minecraft.world.level.ItemLike
typealias ItemLike = net.minecraft.world.level.ItemLike
typealias ItemSettings = net.minecraft.world.item.Item.Properties
typealias BlockSettings = net.minecraft.world.level.block.state.BlockBehaviour.Properties
typealias Block = net.minecraft.world.level.block.Block
typealias BlockState = net.minecraft.world.level.block.state.BlockState
typealias AbstractBlockState = net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase
typealias FluidState = net.minecraft.world.level.material.FluidState
typealias BlockEntity = net.minecraft.world.level.block.entity.BlockEntity
typealias ContainerOpenersCounter = net.minecraft.world.level.block.entity.ContainerOpenersCounter

// Entities & Players
typealias Entity = net.minecraft.world.entity.Entity
typealias LivingEntity = net.minecraft.world.entity.LivingEntity
typealias PlayerEntity = net.minecraft.world.entity.player.Player
typealias Player = net.minecraft.world.entity.player.Player
typealias ServerPlayerEntity = net.minecraft.server.level.ServerPlayer
typealias ServerPlayer = net.minecraft.server.level.ServerPlayer
typealias ClientPlayerEntity = net.minecraft.client.player.LocalPlayer
typealias LocalPlayer = net.minecraft.client.player.LocalPlayer
typealias EntityType<T> = net.minecraft.world.entity.EntityType<T>
typealias EntityTypeBuilder<T> = net.minecraft.world.entity.EntityType.Builder<T>
typealias SpawnGroup = net.minecraft.world.entity.MobCategory
typealias SpawnReason = net.minecraft.world.entity.EntitySpawnReason
typealias DamageSource = net.minecraft.world.damagesource.DamageSource
typealias DamageTracker = net.minecraft.world.damagesource.CombatTracker
typealias AttributeContainer = net.minecraft.world.entity.ai.attributes.AttributeMap
typealias EntityAttribute = net.minecraft.world.entity.ai.attributes.Attribute
typealias EntityAttributes = net.minecraft.world.entity.ai.attributes.Attributes
typealias EntityAttributeModifier = net.minecraft.world.entity.ai.attributes.AttributeModifier
typealias Brain<E> = net.minecraft.world.entity.ai.Brain<E>
typealias PlayerAbilities = net.minecraft.world.entity.player.Abilities
typealias Arm = net.minecraft.world.entity.HumanoidArm
typealias ServerPlayerGameMode = net.minecraft.server.level.ServerPlayerGameMode
typealias ServerGamePacketListenerImpl = net.minecraft.server.network.ServerGamePacketListenerImpl
typealias ServerStatHandler = net.minecraft.stats.ServerStatsCounter
typealias TextStream = net.minecraft.server.network.FilteredText
typealias RecipeBook = net.minecraft.stats.RecipeBook
typealias PublicPlayerSession = net.minecraft.network.chat.RemoteChatSession

// Interactions & Inventory
typealias Hand = net.minecraft.world.InteractionHand
typealias InteractionHand = net.minecraft.world.InteractionHand
typealias ActionResult = net.minecraft.world.InteractionResult
typealias InteractionResult = net.minecraft.world.InteractionResult
typealias ClickType = net.minecraft.world.inventory.ClickAction
typealias Slot = net.minecraft.world.inventory.Slot
typealias ScreenHandler = net.minecraft.world.inventory.AbstractContainerMenu
typealias UseOnContext = net.minecraft.world.item.context.UseOnContext
typealias BlockPlaceContext = net.minecraft.world.item.context.BlockPlaceContext

// Effects & Components
typealias StatusEffect = net.minecraft.world.effect.MobEffect
typealias StatusEffects = net.minecraft.world.effect.MobEffects
typealias StatusEffectInstance = net.minecraft.world.effect.MobEffectInstance
typealias MobEffect = net.minecraft.world.effect.MobEffect
typealias MobEffects = net.minecraft.world.effect.MobEffects
typealias MobEffectInstance = net.minecraft.world.effect.MobEffectInstance
typealias ComponentMap = net.minecraft.core.component.DataComponentMap
typealias ComponentType<T> = net.minecraft.core.component.DataComponentType<T>
typealias ComponentHolder = net.minecraft.core.component.DataComponentHolder
typealias ComponentChanges = net.minecraft.core.component.DataComponentPatch
typealias DataComponentTypes = net.minecraft.core.component.DataComponents

// NBT & Storage
typealias NbtCompound = net.minecraft.nbt.CompoundTag
typealias CompoundTag = net.minecraft.nbt.CompoundTag
typealias Tag = net.minecraft.nbt.Tag
typealias ReadView = net.minecraft.world.level.storage.ValueInput
typealias WriteView = net.minecraft.world.level.storage.ValueOutput
typealias ValueInput = net.minecraft.world.level.storage.ValueInput
typealias ValueOutput = net.minecraft.world.level.storage.ValueOutput

// Sound & Registry
typealias SoundEvent = net.minecraft.sounds.SoundEvent
typealias SoundEvents = net.minecraft.sounds.SoundEvents
typealias SoundSource = net.minecraft.sounds.SoundSource
typealias SoundType = net.minecraft.world.level.block.SoundType
typealias Holder<T> = net.minecraft.core.Holder<T>
typealias Random = net.minecraft.util.RandomSource
typealias FeatureSet = net.minecraft.world.flag.FeatureFlagSet
typealias ToggleableFeature = net.minecraft.world.flag.FeatureElement
typealias DyeColor = net.minecraft.world.item.DyeColor

// Static delegates
typealias Blocks = com.github.cao.awa.conium.mapping.yarn.reference.YarnBlocks
typealias Items = com.github.cao.awa.conium.mapping.yarn.reference.YarnItems
