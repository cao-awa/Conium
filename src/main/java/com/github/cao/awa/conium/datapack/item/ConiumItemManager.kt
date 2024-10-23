package com.github.cao.awa.conium.datapack.item

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.registry.ConiumDynamicRegistry
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.google.gson.*
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.resource.JsonDataLoader
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumItemManager(private val registryLookup: RegistryWrapper.WrapperLookup) :
    JsonDataLoader(GSON, RegistryKeys.getPath(ConiumRegistryKeys.ITEM)) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumItemManager")
        private val GSON: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    }

    private val items = ApricotCollectionFactor.hashMap<Identifier, Item>()

    override fun apply(prepared: Map<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        (Registries.ITEM as ConiumDynamicRegistry).clearDynamic()
        ConiumEvent.clearSubscribes()

        for ((key, value) in prepared) {
            value as JsonObject

            val identifier = value.get("id").asString

            // Use to debug, trace inject details.
            Conium.debug(
                "Registering item '{}' from '{}'",
                { identifier },
                key::getNamespace,
                LOGGER::info
            )

            val item: ConiumItemBuilder = ConiumItemBuilder.deserialize(value, this.registryLookup)

            Items.register(item.identifier, item.build())
        }
    }
}
