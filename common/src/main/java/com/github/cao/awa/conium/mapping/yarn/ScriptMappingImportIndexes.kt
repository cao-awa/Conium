@file:Suppress("unused")

package com.github.cao.awa.conium.mapping.yarn

// net.minecraft
import net.minecraft.GameVersion
import net.minecraft.MinecraftVersion
import net.minecraft.SaveVersion
import net.minecraft.SharedConstants

// net.minecraft.registry
import net.minecraft.registry.Registry
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryKeyedValue
import net.minecraft.registry.BuiltinRegistries
import net.minecraft.registry.CombinedDynamicRegistries
import net.minecraft.registry.DefaultedRegistry
import net.minecraft.registry.SimpleDefaultedRegistry
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.registry.ExperimentalRegistriesValidator
import net.minecraft.registry.ServerDynamicRegistryType
import net.minecraft.registry.MutableRegistry
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryCloner
import net.minecraft.registry.RegistryCodecs
import net.minecraft.registry.RegistryEntryLookup
import net.minecraft.registry.RegistryLoader
import net.minecraft.registry.RegistryOps
import net.minecraft.registry.RegistryPair
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.ReloadableRegistries
import net.minecraft.registry.SerializableRegistries
import net.minecraft.registry.SimpleRegistry
import net.minecraft.registry.TradeRebalanceBuiltinRegistries
import net.minecraft.registry.VersionedIdentifier

// net.minecraft.registry.entry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.entry.RegistryEntryInfo
import net.minecraft.registry.entry.RegistryEntryList
import net.minecraft.registry.entry.RegistryEntryListCodec
import net.minecraft.registry.entry.RegistryEntryOwner
import net.minecraft.registry.entry.RegistryFixedCodec
import net.minecraft.registry.entry.RegistryElementCodec

// net.minecraft.registry.tag
import net.minecraft.registry.tag.TagKey
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.BannerPatternTags
import net.minecraft.registry.tag.CatVariantTags
import net.minecraft.registry.tag.FlatLevelGeneratorPresetTags
import net.minecraft.registry.tag.FluidTags
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.registry.tag.EnchantmentTags
import net.minecraft.registry.tag.EntityTypeTags
import net.minecraft.registry.tag.InstrumentTags
import net.minecraft.registry.tag.PaintingVariantTags
import net.minecraft.registry.tag.PointOfInterestTypeTags
import net.minecraft.registry.tag.StructureTags
import net.minecraft.registry.tag.TagEntry
import net.minecraft.registry.tag.TagFile
import net.minecraft.registry.tag.WorldPresetTags

// net.minecraft.block
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.BlockState
import net.minecraft.block.Waterloggable
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.InventoryProvider
import net.minecraft.block.Oxidizable
import net.minecraft.block.Portal
import net.minecraft.block.SculkSpreadable
import net.minecraft.block.Stainable
import net.minecraft.block.SuspiciousStewIngredient
import net.minecraft.block.FluidDrainable
import net.minecraft.block.FluidFillable
import net.minecraft.block.Degradable
import net.minecraft.block.Fertilizable
import net.minecraft.block.LandingBlock
import net.minecraft.block.OperatorBlock
import net.minecraft.block.ShapeContext

// net.minecraft.block.entity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.ViewerCountManager

// net.minecraft.client
import net.minecraft.client.MinecraftClient

// net.minecraft.client.world
import net.minecraft.client.world.ClientWorld

// net.minecraft.client.network
import net.minecraft.client.network.ClientPlayerEntity

// net.minecraft.inventory
import net.minecraft.inventory.EnderChestInventory

// net.minecraft.entity
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.EntityAttachments

// net.minecraft.entity.effect
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.effect.StatusEffectUtil
import net.minecraft.entity.effect.PoisonStatusEffect
import net.minecraft.entity.effect.InstantStatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.effect.WitherStatusEffect

// net.minecraft.entity.ai
// net.minecraft.entity.ai.brain
import net.minecraft.entity.ai.brain.Brain

// net.minecraft.entity.damage
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageSources
import net.minecraft.entity.damage.DamageTracker
import net.minecraft.entity.damage.DamageType
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.entity.damage.DamageEffects
import net.minecraft.entity.damage.DamageRecord
import net.minecraft.entity.damage.DamageScaling
import net.minecraft.entity.damage.DeathMessageType
import net.minecraft.entity.damage.FallLocation

// net.minecraft.entity.attribute
import net.minecraft.entity.attribute.AttributeContainer

// net.minecraft.entity.player
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerAbilities

// net.minecraft.entity.projectile
import net.minecraft.entity.projectile.thrown.EnderPearlEntity

// net.minecraft.fluid
import net.minecraft.fluid.FluidState

// net.minecraft.network
// net.minecraft.network.packet.c2s.common
import net.minecraft.network.packet.c2s.common.SyncedClientOptions

// net.minecraft.network.encryption
import net.minecraft.network.encryption.PublicPlayerSession
// net.minecraft.network.message
import net.minecraft.network.message.ChatVisibility

// net.minecraft.item
import net.minecraft.item.Item
import com.github.cao.awa.conium.mapping.yarn.reference.YarnItems /* Static delegate of 'net.minecraft.item.Items' */
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemUsageContext

// net.minecraft.advancement
import net.minecraft.advancement.PlayerAdvancementTracker

// net.minecraft.recipe.book
import net.minecraft.recipe.book.RecipeBook

// net.minecraft.screen.slot
import net.minecraft.screen.slot.Slot

// net.minecraft.server
import net.minecraft.server.MinecraftServer

// net.minecraft.server.command
import net.minecraft.server.command.CommandOutput
import net.minecraft.server.command.ServerCommandSource

// net.minecraft.server.filter
import net.minecraft.server.filter.TextStream

// net.minecraft.server.network
import net.minecraft.server.network.ChunkFilter
import net.minecraft.server.network.ServerPlayNetworkHandler

// net.minecraft.server.world
import net.minecraft.server.world.ServerWorld

// net.minecraft.server.network
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.network.ServerPlayerInteractionManager

// net.minecraft.stat
import net.minecraft.stat.ServerStatHandler

// net.minecraft.text
import net.minecraft.text.Text

// net.minecraft.util
import net.minecraft.util.ActionResult
import net.minecraft.util.ClickType
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.PlayerInput
import net.minecraft.util.Arm

// net.minecraft.util.hit
import net.minecraft.util.hit.BlockHitResult

// net.minecraft.util.math
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkSectionPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec2f
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i
import net.minecraft.util.math.Box
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.math.Direction

// net.minecraft.util.math.random
import net.minecraft.util.math.random.Random

// net.minecraft.world
import net.minecraft.world.World

// net.minecraft.world.tick
import net.minecraft.world.tick.ScheduledTickView

// ----------

// net.minecraft
typealias GameVersion = GameVersion
typealias MinecraftVersion = MinecraftVersion
typealias SaveVersion = SaveVersion
typealias SharedConstants = SharedConstants

// net.minecraft.advancement
typealias PlayerAdvancementTracker = PlayerAdvancementTracker

// net.minecraft.registry
typealias Registry<T> = Registry<T>
typealias Registries = Registries
typealias RegistryKey<T> = RegistryKey<T>
typealias RegistryKeys = RegistryKeys
typealias RegistryKeyedValue<T, V> = RegistryKeyedValue<T, V>
typealias BuiltinRegistries = BuiltinRegistries
typealias CombinedDynamicRegistries<T> = CombinedDynamicRegistries<T>
typealias DefaultedRegistry<T> = DefaultedRegistry<T>
typealias SimpleDefaultedRegistry<T> = SimpleDefaultedRegistry<T>
typealias DynamicRegistryManager = DynamicRegistryManager
typealias ExperimentalRegistriesValidator = ExperimentalRegistriesValidator
typealias ServerDynamicRegistryType = ServerDynamicRegistryType
typealias MutableRegistry<T> = MutableRegistry<T>
typealias Registerable<T> = Registerable<T>
typealias RegistryBuilder = RegistryBuilder
typealias RegistryCloner<T> = RegistryCloner<T>
typealias RegistryCodecs = RegistryCodecs
typealias RegistryEntryLookup<T> = RegistryEntryLookup<T>
typealias RegistryLoader = RegistryLoader
typealias RegistryOps<T> = RegistryOps<T>
typealias RegistryPair<T> = RegistryPair<T>
typealias RegistryWrapper<T> = RegistryWrapper<T>
typealias ReloadableRegistries = ReloadableRegistries
typealias SerializableRegistries = SerializableRegistries
typealias SimpleRegistry<T> = SimpleRegistry<T>
typealias TradeRebalanceBuiltinRegistries = TradeRebalanceBuiltinRegistries
typealias VersionedIdentifier = VersionedIdentifier

// net.minecraft.registry.entry
typealias RegistryEntry<T> = RegistryEntry<T>
typealias RegistryEntryInfo = RegistryEntryInfo
typealias RegistryEntryList<T> = RegistryEntryList<T>
typealias RegistryEntryListCodec<T> = RegistryEntryListCodec<T>
typealias RegistryEntryOwner<T> = RegistryEntryOwner<T>
typealias RegistryFixedCodec<T> = RegistryFixedCodec<T>
typealias RegistryElementCodec<T> = RegistryElementCodec<T>

// net.minecraft.registry.tag
typealias TagKey<T> = TagKey<T>
typealias BiomeTags = BiomeTags
typealias BlockTags = BlockTags
typealias ItemTags = ItemTags
typealias BannerPatternTags = BannerPatternTags
typealias CatVariantTags = CatVariantTags
typealias FlatLevelGeneratorPresetTags = FlatLevelGeneratorPresetTags
typealias FluidTags = FluidTags
typealias DamageTypeTags = DamageTypeTags
typealias EnchantmentTags = EnchantmentTags
typealias EntityTypeTags = EntityTypeTags
typealias InstrumentTags = InstrumentTags
typealias PaintingVariantTags = PaintingVariantTags
typealias PointOfInterestTypeTags = PointOfInterestTypeTags
typealias StructureTags = StructureTags
typealias TagEntry = TagEntry
typealias TagFile = TagFile
typealias WorldPresetTags = WorldPresetTags

// net.minecraft.block
typealias AbstractBlock = AbstractBlock
typealias Block = Block
typealias Blocks = Blocks
typealias BlockState = BlockState
typealias Waterloggable = Waterloggable
typealias BlockEntityProvider = BlockEntityProvider
typealias InventoryProvider = InventoryProvider
typealias Oxidizable = Oxidizable
typealias Portal = Portal
typealias SculkSpreadable = SculkSpreadable
typealias Stainable = Stainable
typealias SuspiciousStewIngredient = SuspiciousStewIngredient
typealias FluidDrainable = FluidDrainable
typealias FluidFillable = FluidFillable
typealias Degradable<T> = Degradable<T>
typealias Fertilizable = Fertilizable
typealias LandingBlock = LandingBlock
typealias OperatorBlock = OperatorBlock
typealias ShapeContext = ShapeContext

// net.minecraft.block.entity
typealias BlockEntity = BlockEntity
typealias ViewerCountManager = ViewerCountManager

// net.minecraft.client
typealias MinecraftClient = MinecraftClient

// net.minecraft.client.world
typealias ClientWorld = ClientWorld

// net.minecraft.client.network
typealias ClientPlayerEntity = ClientPlayerEntity

// net.minecraft.inventory
typealias EnderChestInventory = EnderChestInventory

// net.minecraft.entity
typealias Entity = Entity
typealias LivingEntity = LivingEntity
typealias EntityAttachments = EntityAttachments

// net.minecraft.entity.effect
typealias StatusEffect = StatusEffect
typealias StatusEffectInstance = StatusEffectInstance
typealias StatusEffects = StatusEffects
typealias StatusEffectUtil = StatusEffectUtil
typealias PoisonStatusEffect = PoisonStatusEffect
typealias InstantStatusEffect = InstantStatusEffect
typealias StatusEffectCategory = StatusEffectCategory
typealias WitherStatusEffect = WitherStatusEffect

// net.minecraft.entity.ai
// net.minecraft.entity.ai.brain
typealias Brain<E> = Brain<E>

// net.minecraft.entity.damage
typealias DamageSource = DamageSource
typealias DamageSources = DamageSources
typealias DamageTracker = DamageTracker
typealias DamageType = DamageType
typealias DamageTypes = DamageTypes
typealias DamageEffects = DamageEffects
typealias DamageRecord = DamageRecord
typealias DamageScaling = DamageScaling
typealias DeathMessageType = DeathMessageType
typealias FallLocation = FallLocation

// net.minecraft.entity.attribute
typealias AttributeContainer = AttributeContainer

// net.minecraft.entity.projectile.thrown
typealias EnderPearlEntity = EnderPearlEntity

// net.minecraft.entity.player
typealias PlayerEntity = PlayerEntity
typealias PlayerAbilities = PlayerAbilities
typealias ServerPlayerEntity = ServerPlayerEntity

// net.minecraft.fluid
typealias FluidState = FluidState

// net.minecraft.network
// net.minecraft.network.packet.c2s.common
typealias SyncedClientOptions = SyncedClientOptions

// net.minecraft.network.encryption
typealias PublicPlayerSession = PublicPlayerSession

// net.minecraft.network.message
typealias ChatVisibility = ChatVisibility

// net.minecraft.item
typealias Item = Item
typealias Items = YarnItems
typealias ItemStack = ItemStack
typealias ItemPlacementContext = ItemPlacementContext
typealias ItemUsageContext = ItemUsageContext

// net.minecraft.screen.slot
typealias Slot = Slot

// net.minecraft.server
typealias MinecraftServer = MinecraftServer

// net.minecraft.server.filter
typealias TextStream = TextStream

// net.minecraft.server.world
typealias ServerWorld = ServerWorld

// net.minecraft.server.command
typealias CommandOutput = CommandOutput
typealias ServerCommandSource = ServerCommandSource

// net.minecraft.server.network
typealias ServerPlayNetworkHandler = ServerPlayNetworkHandler
typealias ServerPlayerInteractionManager = ServerPlayerInteractionManager
typealias ChunkFilter = ChunkFilter

// net.minecraft.util
typealias ActionResult = ActionResult
typealias ClickType = ClickType
typealias Hand = Hand
typealias Identifier = Identifier
typealias PlayerInput = PlayerInput
typealias Arm = Arm

// net.minecraft.util.hit
typealias BlockHitResult = BlockHitResult

// net.minecraft.util.math
typealias MathHelper = MathHelper
typealias BlockPos = BlockPos
typealias ChunkSectionPos = ChunkSectionPos
typealias Vec3d = Vec3d
typealias Vec3i = Vec3i
typealias Vec2f = Vec2f
typealias Box = Box
typealias ChunkPos = ChunkPos
typealias Direction = Direction

// net.minecraft.util.math.random
typealias Random = Random

// net.minecraft.world
typealias World = World

// net.minecraft.world.tick
typealias ScheduledTickView = ScheduledTickView

// net.minecraft.stat
typealias ServerStatHandler = ServerStatHandler

// net.minecraft.recipe.book
typealias RecipeBook = RecipeBook

// net.minecraft.text
typealias Text = Text