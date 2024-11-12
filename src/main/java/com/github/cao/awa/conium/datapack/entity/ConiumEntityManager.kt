package com.github.cao.awa.conium.datapack.entity

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.entity.attribute.ConiumEntityAttributeRegistry
import com.github.cao.awa.conium.entity.builder.bedrock.BedrockSchemaEntityBuilder
import com.github.cao.awa.conium.entity.builder.conium.ConiumSchemaEntityBuilder
import com.github.cao.awa.conium.entity.metadata.ConiumEntityMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.extend.ConiumDynamicRegistry
import com.github.cao.awa.conium.kotlin.extent.entity.register
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.*
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumEntityManager(private val registryLookup: RegistryWrapper.WrapperLookup) :
    ConiumJsonDataLoader(RegistryKeys.getPath(ConiumRegistryKeys.ENTITY)) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumEntityManager")
    }

    val metadata: MutableList<ConiumEntityMetadata> = CollectionFactor.arrayList()

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        (Registries.ENTITY_TYPE as ConiumDynamicRegistry).clearDynamic()
        ConiumEvent.clearEntitySubscribes()
        ConiumEntityAttributeRegistry.resetAttributes()

        for ((key, value) in prepared) {
            value as JsonObject

            // Use to debug, trace inject details.
            Conium.debug(
                "Registering entity '{}' from '{}'",
                key::getPath,
                key::getNamespace,
                LOGGER::info
            )

            if (value["schema_style"]?.asString == "conium") {
                ConiumSchemaEntityBuilder.deserialize(value, this.registryLookup).register {
                    // Do nothing.
                    this.metadata.add(it)
                }
            } else {
                BedrockSchemaEntityBuilder.deserialize(value, this.registryLookup).register {
                    // Do nothing.
                    this.metadata.add(it)
                }
            }
        }
    }
}
