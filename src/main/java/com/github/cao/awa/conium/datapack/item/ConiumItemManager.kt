package com.github.cao.awa.conium.datapack.item

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.datapack.item.fuel.ConiumFuelRegistry
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.registry.extend.ConiumDynamicRegistry
import com.github.cao.awa.conium.item.builder.bedrock.BedrockSchemaItemBuilder
import com.github.cao.awa.conium.item.builder.conium.ConiumSchemaItemBuilder
import com.github.cao.awa.conium.kotlin.extent.item.register
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryWrapper
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*

class ConiumItemManager(
    private val registryLookup: RegistryWrapper.WrapperLookup,
    private val pendingTagLoad: List<Registry.PendingTagLoad<*>>
) : ConiumJsonDataLoader(ConiumRegistryKeys.ITEM.value) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumItemManager")
    }

    private val fuelRegistry: ConiumFuelRegistry = ConiumFuelRegistry()
    val fuels: Set<Item> get() = this.fuelRegistry.fuelItems

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        resetRegistries()

        for ((key: Identifier, value: JsonElement) in prepared) {
            load(key, value as JsonObject)
        }
    }

    fun resetRegistries() {
        (Registries.ITEM as ConiumDynamicRegistry).clearDynamic()
        ConiumEvent.clearItemSubscribes()
        this.fuelRegistry.resetComputedFuels()
    }

    fun load(identifier: Identifier, json: JsonObject) {
        // Use to debug, trace inject details.
        Conium.debug(
            "Registering item '{}' from '{}'",
            identifier::getPath,
            identifier::getNamespace,
            LOGGER::info
        )

        if (json["schema_style"]?.asString == "conium") {
            ConiumSchemaItemBuilder.deserialize(json, this.registryLookup).register()
        } else {
            BedrockSchemaItemBuilder.deserialize(json, this.registryLookup).register()
        }
    }

    fun addFuel(item: Item, duration: Int) = this.fuelRegistry.add(item, duration)

    fun getFuelTicks(stack: ItemStack): Int = this.fuelRegistry.getFuelTicks(stack)

    fun computeFuels(fuels: SequencedSet<Item>): SequencedSet<Item> = this.fuelRegistry.computeFuels(fuels)
}
