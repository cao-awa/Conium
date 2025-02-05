@file:Suppress("unused")

package com.github.cao.awa.conium.mapping.yarn

// net.minecraft
import net.minecraft.GameVersion
import net.minecraft.MinecraftVersion
import net.minecraft.SaveVersion
import net.minecraft.SharedConstants

// net.minecraft.registry
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys

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

// net.minecraft.entity
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity

// net.minecraft.entity.player
import net.minecraft.entity.player.PlayerEntity

// net.minecraft.fluid
import net.minecraft.fluid.FluidState

// net.minecraft.item
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemUsageContext

// net.minecraft.screen.slot
import net.minecraft.screen.slot.Slot

// net.minecraft.server
import net.minecraft.server.MinecraftServer

// net.minecraft.server.world
import net.minecraft.server.world.ServerWorld

// net.minecraft.server.network
import net.minecraft.server.network.ServerPlayerEntity

// net.minecraft.util
import net.minecraft.util.ActionResult
import net.minecraft.util.ClickType
import net.minecraft.util.Hand
import net.minecraft.util.Identifier

// net.minecraft.util.hit
import net.minecraft.util.hit.BlockHitResult

// net.minecraft.util.math
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper

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

// net.minecraft.registry
typealias Registries = Registries
typealias RegistryKe<T> = RegistryKey<T>
typealias RegistryKeys = RegistryKeys

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

// net.minecraft.entity
typealias Entity = Entity
typealias LivingEntity = LivingEntity

// net.minecraft.entity.player
typealias PlayerEntity = PlayerEntity

// net.minecraft.fluid
typealias FluidState = FluidState

// net.minecraft.item
typealias Item = Item
typealias Items = Items
typealias ItemStack = ItemStack
typealias ItemPlacementContext = ItemPlacementContext
typealias ItemUsageContext = ItemUsageContext

// net.minecraft.screen.slot
typealias Slot = Slot

// net.minecraft.server
typealias MinecraftServer = MinecraftServer

// net.minecraft.server.world
typealias ServerWorld = ServerWorld

// net.minecraft.server.network
typealias ServerPlayerEntity = ServerPlayerEntity

// net.minecraft.util
typealias ActionResult = ActionResult
typealias ClickType = ClickType
typealias Hand = Hand
typealias Identifier = Identifier

// net.minecraft.util.hit
typealias BlockHitResult = BlockHitResult

// net.minecraft.util.math
typealias MathHelper = MathHelper
typealias BlockPos = BlockPos

// net.minecraft.util.math.random
typealias Random = Random

// net.minecraft.world
typealias World = World

// net.minecraft.world.tick
typealias ScheduledTickView = ScheduledTickView
