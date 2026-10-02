package com.github.cao.awa.conium.item.template.entity.placer

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.arrayOrString
import com.github.cao.awa.conium.kotlin.extent.json.objectOrString
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.ENTITY_PLACER
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.Spawner
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.server.level.ServerLevel
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.resources.Identifier
import net.minecraft.world.phys.HitResult
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
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
            allowedBlocks.arrayOrString({ blocks ->
                for (element in blocks) {
                    val identifier: String = element.asString
                    if (identifier.split(":").size != 2) {
                        LOGGER.warn("The value {} in allowed blocks is not full identifier, will use \"minecraft\" be the namespace path", identifier)
                        result.add(BuiltInRegistries.BLOCK.getValue(Identifier.fromNamespaceAndPath("minecraft", identifier)))
                    } else {
                        result.add(BuiltInRegistries.BLOCK.getValue(Identifier.parse(identifier)))
                    }
                }
            }) { identifier ->
                result.add(BuiltInRegistries.BLOCK.getValue(Identifier.parse(identifier)))
            }

            return result
        }

        @JvmStatic
        fun getEntityType(identifier: String): EntityType<*> = BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse(identifier))
    }

    override fun attach(target: ConiumItem) {
        target.useOnBlockHandlers.add { context: UseOnContext ->
            val world: Level = context.level
            if (world.isClientSide) {
                return@add false
            }
            val itemStack: ItemStack = context.itemInHand
            val blockPos: BlockPos = context.clickedPos
            val direction: Direction = context.clickedFace
            val blockState: BlockState = world.getBlockState(blockPos)
            if (!this.allowedBlocks.isEmpty() && !this.allowedBlocks.contains(blockState.block)) {
                return@add false
            }
            val blockEntity: BlockEntity? = world.getBlockEntity(blockPos)
            if (blockEntity is Spawner) {
                val spawner = blockEntity as Spawner
                spawner.setEntityId(this.entityType, world.random)
                world.sendBlockUpdated(blockPos, blockState, blockState, 3)
                world.gameEvent(context.player, GameEvent.BLOCK_CHANGE, blockPos)
                return@add true
            }
            val blockPos2 = if (blockState.getCollisionShape(world, blockPos).isEmpty) blockPos else blockPos.relative(direction)
            if (this.entityType.spawn(world as ServerLevel, itemStack, context.player, blockPos2, EntitySpawnReason.SPAWN_ITEM_USE, true, blockPos != blockPos2 && direction == Direction.UP) != null) {
                world.gameEvent(context.player, GameEvent.ENTITY_PLACE, blockPos)
            }
            return@add true
        }

        target.useHandlers.add { world: Level, user: Player, hand: InteractionHand ->
            val itemStack = user.getItemInHand(hand)
            val blockHitResult = ConiumItem.doRaycast(world, user, ClipContext.Fluid.SOURCE_ONLY)
            if (blockHitResult.type != HitResult.Type.BLOCK) {
                return@add false
            }
            if (world !is ServerLevel) {
                return@add false
            }
            val serverWorld: ServerLevel = world
            val blockPos = blockHitResult.blockPos
            val blockState: BlockState = world.getBlockState(blockPos)
            val block: Block = blockState.block
            if (block !is LiquidBlock) {
                return@add false
            }
            if (!this.allowedDispenserBlocks.isEmpty() && !this.allowedDispenserBlocks.contains(block)) {
                return@add false
            }
            if (!world.mayInteract(user, blockPos) || !user.mayUseItemAt(blockPos, blockHitResult.direction, itemStack)) {
                return@add false
            }
            val entity: Entity = this.entityType.spawn(
                serverWorld,
                itemStack,
                user,
                blockPos,
                EntitySpawnReason.SPAWN_ITEM_USE,
                false,
                false
            ) ?: return@add false
            user.awardStat(Stats.ITEM_USED.get(target))
            world.gameEvent(user, GameEvent.ENTITY_PLACE, entity.position())
            return@add true
        }
    }
}
