package com.github.cao.awa.conium.datapack.entity

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.ConiumClientInitializer
import com.github.cao.awa.conium.client.entity.renderer.ConiumEntityRenderers
import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.entity.attribute.ConiumEntityAttributeRegistry
import com.github.cao.awa.conium.entity.builder.bedrock.BedrockSchemaEntityBuilder
import com.github.cao.awa.conium.entity.builder.conium.ConiumSchemaEntityBuilder
import com.github.cao.awa.conium.entity.metadata.ConiumEntityMetadata
import com.github.cao.awa.conium.registry.extend.ConiumDynamicRegistry
import com.github.cao.awa.conium.kotlin.extend.entity.register
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.Registries
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumEntityManager(var registryLookup: RegistryWrapper.WrapperLookup): ConiumJsonDataLoader(ConiumRegistryKeys.ENTITY.value) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumEntityManager")
    }

    val metadata: MutableList<ConiumEntityMetadata> = CollectionFactor.arrayList()

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        resetRegistries()

        for ((key: Identifier, value: JsonElement) in prepared) {
            load(key, value as JsonObject)
        }
    }

    fun resetRegistries() {
        (Registries.ENTITY_TYPE as ConiumDynamicRegistry).clearDynamic()
        ConiumEntityAttributeRegistry.resetAttributes()

        if (Conium.isClient) {
            ConiumEntityRenderers.clearRenderers()
        }
    }

    fun load(identifier: Identifier, json: JsonObject) {
        // Use to debug, trace inject details.
        Conium.debug(
            "Registering entity '{}' from '{}'",
            identifier::getPath,
            identifier::getNamespace,
            LOGGER::info
        )

        var metadata: ConiumEntityMetadata? = null

        if (json["schema_style"]?.asString == "conium") {
            ConiumSchemaEntityBuilder.deserialize(json).register {
                metadata = it
            }
        } else {
            BedrockSchemaEntityBuilder.deserialize(json).register {
                metadata = it
            }
        }

        metadata?.also {
            this.metadata.add(it)

            // Only init entity renderer when client initializer loaded.
            if (Conium.isClient) {
                ConiumClientInitializer.createEntityRenderer(it)
            }
        }
    }
}
