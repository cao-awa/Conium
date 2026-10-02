package com.github.cao.awa.conium.item.template.egg

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.exception.Exceptions.illegalArgument
import com.github.cao.awa.conium.exception.notSupported
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifString
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.SPAWN_EGG
import com.google.gson.JsonElement
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level

class ConiumSpawnEggTemplate(private val entityType: EntityType<*>) : ConiumItemTemplate(name = SPAWN_EGG) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumSpawnEggTemplate {
            var result: ConiumSpawnEggTemplate? = null
            element.ifString({ entity ->
                BuiltInRegistries.ENTITY_TYPE.getOptional(Identifier.parse(entity)).ifPresent {
                    result = ConiumSpawnEggTemplate(it)
                }
            },
                notSupported()
            )

            if (result != null) {
                return result!!
            }
            return illegalArgument("Entity type ${element.asString} not found")
        }
    }

    override fun attach(target: ConiumItem) {
        ConiumEvent.itemUseOnBlock.subscribe(target) { world: Level, context: UseOnContext ->
            if (world is ServerLevel) {
                val entity = entityType.create(world, EntitySpawnReason.SPAWN_ITEM_USE)
                if (entity != null) {
                    val pos = context.clickedPos.relative(context.clickedFace)
                    entity.setPos(pos.x + 0.5, pos.y.toDouble(), pos.z + 0.5)
                    world.addFreshEntity(entity)
                }
            }

            true
        }
    }
}
