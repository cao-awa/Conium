package com.github.cao.awa.conium.datapack.item

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.codec.ConiumCodec
import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInject
import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction
import com.github.cao.awa.conium.datapack.inject.item.action.handler.ItemPropertyInjectHandler
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponent
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponentValue
import com.github.cao.awa.conium.item.ItemBuilder
import com.github.cao.awa.conium.registry.ConiumDynamicRegistry
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.google.gson.*
import com.mojang.serialization.JsonOps
import net.minecraft.component.ComponentType
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
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
        private val LOGGER: Logger = LogManager.getLogger("ConiumManager")
        private val GSON: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    }

    private val items = ApricotCollectionFactor.hashMap<Identifier, Item>()

    override fun apply(prepared: Map<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        for ((key, value) in prepared) {
            (Registries.ITEM as ConiumDynamicRegistry).clearDynamic()

            value as JsonObject

            val identifier = value.get("id")

            // Use to debug, trace inject details.
            Conium.debug(
                "Registering item '{}' from '{}'",
                { identifier },
                key::getNamespace,
                LOGGER::info
            )

            val registryOps = registryLookup.getOps(JsonOps.INSTANCE)

            var item: ItemBuilder? = null

            ConiumCodec.ITEM.parse(registryOps, value).let {
                it.result().let { result ->
                    if (result.isPresent) {
                        item = result.get()
                    } else {
                        LOGGER.info("Failure register the item '{}'", identifier)
                    }
                }
            }

            item?.let {
                Items.register(it.identifier, it.build())

            }
        }
    }
}
