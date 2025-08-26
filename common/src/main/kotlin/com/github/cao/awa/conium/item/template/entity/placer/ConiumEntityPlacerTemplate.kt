package com.github.cao.awa.conium.item.template.entity.placer

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrString
import com.github.cao.awa.conium.template.ConiumTemplates.Item.ENTITY_PLACER
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.FluidBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.Spawner
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.server.world.ServerWorld
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.RaycastContext
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.Collections
import kotlin.text.split

open class ConiumEntityPlacerTemplate(
    val entityType: EntityType<*>,
    val allowedBlocks: MutableList<Block> = Collections.emptyList(),
    val allowedDispenserBlocks: MutableList<Block> = Collections.emptyList()
) : ConiumItemTemplate(name = ENTITY_PLACER) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumEntityPlacerTemplate")

        @JvmStatic
        fun create(element: JsonElement): ConiumEntityPlacerTemplate = element.objectOrString(
            { obj: JsonObject ->
                ConiumEntityPlacerTemplate(
                    getEntityType(obj["entity"].asString),
                    obj["allowed_blocks"]?.let(this::getAllowedBlocks) ?: Collections.emptyList(),
                    obj["allowed_dispenser_blocks"]?.let(this::getAllowedBlocks) ?: Collections.emptyList()
                )
            }
        ) { identifier: String ->
            ConiumEntityPlacerTemplate(getEntityType(identifier))
        }!!

        @JvmStatic
        fun getAllowedBlocks(allowedBlocks: JsonElement): MutableList<Block> {
            val result: MutableList<Block> = ArrayList()
            for (element in allowedBlocks.asJsonArray) {
                val identifier: String = element.asString
                if (identifier.split(":").size != 2) {
                    LOGGER.warn("The value {} in allowed blocks is not full identifier, will use \"minecraft\" be the namespace path", identifier)
                    result.add(Registries.BLOCK.get(Identifier.of("minecraft", identifier)))
                } else {
                    result.add(Registries.BLOCK.get(Identifier.of(identifier)))
                }
            }
            return result
        }

        @JvmStatic
        fun getEntityType(identifier: String): EntityType<*> = Registries.ENTITY_TYPE.get(Identifier.of(identifier))
    }

    override fun attach(target: ConiumItem) {
        target.useOnBlockHandlers.add { context: ItemUsageContext ->
            val world: World = context.world
            if (world.isClient) {
                return@add false
            }
            val itemStack: ItemStack = context.stack
            val blockPos: BlockPos = context.blockPos
            val direction: Direction = context.side
            val blockState: BlockState = world.getBlockState(blockPos)
            if (!this.allowedBlocks.isEmpty() && !this.allowedBlocks.contains(blockState.block)) {
                return@add false
            }
            val blockEntity: BlockEntity? = world.getBlockEntity(blockPos)
            if (blockEntity is Spawner) {
                val spawner = blockEntity as Spawner
                spawner.setEntityType(this.entityType, world.random)
                world.updateListeners(blockPos, blockState, blockState, 3)
                world.emitGameEvent(context.player, GameEvent.BLOCK_CHANGE, blockPos)
                return@add true
            }
            val blockPos2 = if (blockState.getCollisionShape(world, blockPos).isEmpty) blockPos else blockPos.offset(direction)
            if (this.entityType.spawnFromItemStack(world as ServerWorld, itemStack, context.player, blockPos2, SpawnReason.SPAWN_ITEM_USE, true, blockPos != blockPos2 && direction == Direction.UP) != null) {
                world.emitGameEvent(context.player, GameEvent.ENTITY_PLACE, blockPos)
            }
            return@add true
        }

        target.useHandlers.add { world: World, user: PlayerEntity, hand: Hand ->
            val itemStack = user.getStackInHand(hand)
            val blockHitResult = ConiumItem.doRaycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY)
            if (blockHitResult.type != HitResult.Type.BLOCK) {
                return@add false
            }
            if (world !is ServerWorld) {
                return@add false
            }
            val serverWorld: ServerWorld = world
            val blockHitResult2 = blockHitResult
            val blockPos = blockHitResult2.blockPos
            val blockState: BlockState = world.getBlockState(blockPos)
            val block: Block = blockState.block
            if (block !is FluidBlock) {
                return@add false
            }
            if (!this.allowedDispenserBlocks.isEmpty() && !this.allowedDispenserBlocks.contains(block)) {
                return@add false
            }
            if (!world.canEntityModifyAt(user, blockPos) || !user.canPlaceOn(blockPos, blockHitResult2.side, itemStack)) {
                return@add false
            }
            val entity: Entity? = this.entityType.spawnFromItemStack(serverWorld, itemStack, user, blockPos, SpawnReason.SPAWN_ITEM_USE, false, false)
            if (entity == null) {
                return@add false
            }
            user.incrementStat(Stats.USED.getOrCreateStat(target))
            world.emitGameEvent(user, GameEvent.ENTITY_PLACE, entity.pos)
            return@add true
        }
    }
}