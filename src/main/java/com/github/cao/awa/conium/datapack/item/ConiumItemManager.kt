package com.github.cao.awa.conium.datapack.item

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.extend.ConiumDynamicRegistry
import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.kotlin.extent.item.register
import com.github.cao.awa.conium.mixin.registry.RegistryEntryReferenceMixin
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.*
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.TagKey
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.stream.Collectors

class ConiumItemManager(private val registryLookup: RegistryWrapper.WrapperLookup, private val pendingTagLoad: List<Registry.PendingTagLoad<*>>) :
    ConiumJsonDataLoader(RegistryKeys.getPath(ConiumRegistryKeys.ITEM)) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumItemManager")
        private val GSON: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    }

    private val items = CollectionFactor.hashMap<Identifier, Item>()

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        (Registries.ITEM as ConiumDynamicRegistry).clearDynamic()
        ConiumEvent.clearItemSubscribes()

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

            val builder: ConiumItemBuilder = ConiumItemBuilder.deserialize(value, this.registryLookup)

            builder.register()
        }
    }

    fun loadTags(identifier: Identifier) {
//        for (tagLoad in this.pendingTagLoad) {
//            Registries.ITEM.getEntry(identifier).ifPresent {
//                (it as RegistryEntryReferenceMixin<Item>).invokeSetTags(tagLoad.lookup.streamTagKeys().toList() as Collection<TagKey<Item>>)
//            }
//        }
    }
}
