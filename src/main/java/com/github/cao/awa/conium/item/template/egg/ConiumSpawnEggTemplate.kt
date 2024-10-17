package com.github.cao.awa.conium.item.template.egg

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.google.gson.JsonElement
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.item.Item

class ConiumSpawnEggTemplate(val entityType: EntityType<*>) : ConiumItemTemplate("spawn_egg") {
    override fun attach(item: ConiumItem) {
        ConiumEvent.itemUseOnBlockEvent.subscribe(item) { world, context ->
            println("Spawn for: $item / $world at ${context.blockPos}")

            world.spawnEntity(
                entityType.create(
                    world,
                    {},
                    context.blockPos.offset(context.side),
                    SpawnReason.SPAWN_EGG,
                    false, false
                )
            )

            return@subscribe true
        }
    }

    override fun settings(settings: Item.Settings) {

    }

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
}
