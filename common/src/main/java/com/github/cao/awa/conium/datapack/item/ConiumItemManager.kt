package com.github.cao.awa.conium.datapack.item

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.datapack.item.fuel.ConiumFuelRegistry
import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.item.builder.bedrock.BedrockSchemaItemBuilder
import com.github.cao.awa.conium.item.builder.conium.ConiumSchemaItemBuilder
import com.github.cao.awa.conium.kotlin.extent.item.register
import com.github.cao.awa.conium.kotlin.extent.item.registerItem
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.conium.registry.extend.ConiumDynamicRegistry
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryWrapper
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*

class ConiumItemManager: ConiumJsonDataLoader(ConiumRegistryKeys.ITEM.value) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumItemManager")
    }

    private val blockItems: MutableMap<Identifier, (Item.Settings) -> BlockItem> = CollectionFactor.hashMap()
    private val fuelRegistry: ConiumFuelRegistry = ConiumFuelRegistry()
    var registryLookup: RegistryWrapper.WrapperLookup? = null
    var pendingTagLoad: List<Registry.PendingTagLoad<*>>? = null
    val fuels: Set<Item> get() = this.fuelRegistry.fuelItems

    override fun earlyLoad(manager: ResourceManager, dataType: Identifier, result: MutableMap<Identifier, JsonElement>, registryLookup: DynamicRegistryManager) {
        // TODO
    }

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        resetRegistries()

        for ((key: Identifier, value: JsonElement) in prepared) {
            load(key, value as JsonObject)
        }

        for ((identifier: Identifier, itemProvider: (Item.Settings) -> BlockItem) in this.blockItems) {
            registerItem(identifier, itemProvider)
        }
    }

    fun resetRegistries() {
        (Registries.ITEM as ConiumDynamicRegistry).clearDynamic()
        this.fuelRegistry.resetComputedFuels()
    }

    fun load(identifier: Identifier, json: JsonObject) {
        val builder: ConiumItemBuilder

        if (json["schema_style"]?.asString == "conium") {
            builder = ConiumSchemaItemBuilder.deserialize(json, this.registryLookup!!)
        } else {
            builder = BedrockSchemaItemBuilder.deserialize(json, this.registryLookup!!)
        }

        // Use to debug, trace inject details.
        Conium.debug(
            "Registering item '{}' from '{}', using templates: {}",
            identifier::getPath,
            identifier::getNamespace,
            {
                builder.templates.values.map(ConiumTemplate<*, *>::name)
            },
            LOGGER::info
        )

        builder.register()
    }

    fun pendingBlockItem(identifier: Identifier, item: (Item.Settings) -> BlockItem) {
        this.blockItems[identifier] = item
    }

    fun register(identifier: Identifier, item: (Item.Settings) -> Item): Item = registerItem(identifier, item)

    fun addFuel(item: Item, duration: Int) = this.fuelRegistry.add(item, duration)

    fun getFuelTicks(stack: ItemStack): Int = this.fuelRegistry.getFuelTicks(stack)

    fun computeFuels(fuels: SequencedSet<Item>): SequencedSet<Item> = this.fuelRegistry.computeFuels(fuels)
}
