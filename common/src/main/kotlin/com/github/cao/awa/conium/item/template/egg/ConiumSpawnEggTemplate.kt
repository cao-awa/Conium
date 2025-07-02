package com.github.cao.awa.conium.item.template.egg

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Item.SPAWN_EGG
import com.google.gson.JsonElement
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World

class ConiumSpawnEggTemplate(private val entityType: EntityType<*>) : ConiumItemTemplate(name = SPAWN_EGG) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumSpawnEggTemplate {
            if (element.isJsonObject) {
                throw IllegalArgumentException("Not supported: $element")
            }
            EntityType.get(element.asString).let {
                if (it.isPresent) {
                    return ConiumSpawnEggTemplate(it.get())
                }
            }
            throw IllegalArgumentException("Entity type ${element.asString} not found")
        }
    }

    override fun attach(target: ConiumItem) {
        ConiumEvent.itemUseOnBlock.subscribe(target) { world: World, context: ItemUsageContext ->
            if (world is ServerWorld) {
                world.spawnEntity(
                    entityType.create(
                        world,
                        {},
                        context.blockPos.offset(context.side),
                        SpawnReason.SPAWN_ITEM_USE,
                        false, false
                    )
                )
            }

            true
        }
    }
}
