@file:Suppress("unchecked_cast")

package com.github.cao.awa.conium.datapack.block

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.block.builder.bedrock.BedrockSchemaBlockBuilder
import com.github.cao.awa.conium.block.builder.conium.ConiumSchemaBlockBuilder
import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.registry.extend.ConiumDynamicIdList
import com.github.cao.awa.conium.registry.extend.ConiumDynamicRegistry
import com.github.cao.awa.conium.kotlin.extent.block.register
import com.github.cao.awa.conium.kotlin.extent.item.registerBlockItem
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.google.common.collect.UnmodifiableIterator
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumBlockManager(private val registryLookup: RegistryWrapper.WrapperLookup) : ConiumJsonDataLoader(ConiumRegistryKeys.BLOCK.value) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumBlockManager")
    }

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        resetRegistries()

        for ((key: Identifier, value: JsonElement) in prepared) {
            load(key, value as JsonObject)
        }
    }

    fun resetRegistries() {
        (Registries.BLOCK as ConiumDynamicRegistry).clearDynamic()
        (Block.STATE_IDS as ConiumDynamicIdList<BlockState>).clearDynamic()
        ConiumEvent.clearBlockSubscribes()
    }

    fun load(identifier: Identifier, json: JsonObject) {
        val stateIds: ConiumDynamicIdList<BlockState> = Manipulate.cast(Block.STATE_IDS)

        // Use to debug, trace inject details.
        Conium.debug(
            "Registering block '{}' from '{}'",
            identifier::getPath,
            identifier::getNamespace,
            LOGGER::info
        )

        val builder: ConiumBlockBuilder = if (json["schema_style"]?.asString == "conium") {
            ConiumSchemaBlockBuilder.deserialize(json, this.registryLookup)
        } else {
            BedrockSchemaBlockBuilder.deserialize(json, this.registryLookup)
        }

        builder.register { block: ConiumBlock ->
            val var2: UnmodifiableIterator<*> = block.stateManager.states.iterator()

            while (var2.hasNext()) {
                val blockState: BlockState = var2.next() as BlockState
                stateIds.addDynamic(blockState)
                blockState.initShapeCache()
            }

            block.lootTableKey

            builder.registerBlockItem(block)
        }
    }
}
