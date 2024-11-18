package com.github.cao.awa.conium.datapack.block

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.block.builder.bedrock.BedrockSchemaBlockBuilder
import com.github.cao.awa.conium.block.builder.conium.ConiumSchemaBlockBuilder
import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.extend.ConiumDynamicIdList
import com.github.cao.awa.conium.extend.ConiumDynamicRegistry
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
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumBlockManager(private val registryLookup: RegistryWrapper.WrapperLookup) :
    ConiumJsonDataLoader(RegistryKeys.getPath(ConiumRegistryKeys.BLOCK)) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumBlockManager")
    }

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        (Registries.BLOCK as ConiumDynamicRegistry).clearDynamic()

        val stateIds: ConiumDynamicIdList<BlockState> = Manipulate.cast(Block.STATE_IDS)

        stateIds.clearDynamic()

        ConiumEvent.clearBlockSubscribes()

        for ((key, value) in prepared) {
            value as JsonObject

            // Use to debug, trace inject details.
            Conium.debug(
                "Registering block '{}' from '{}'",
                key::getPath,
                key::getNamespace,
                LOGGER::info
            )

            val builder = if (value["schema_style"]?.asString == "conium") {
                ConiumSchemaBlockBuilder.deserialize(value, this.registryLookup)
            } else {
                BedrockSchemaBlockBuilder.deserialize(value, this.registryLookup)
            }

            builder.register { block ->
                val var2: UnmodifiableIterator<*> = block.stateManager.states.iterator()

                while (var2.hasNext()) {
                    val blockState = var2.next() as BlockState
                    stateIds.addDynamic(blockState)
                    blockState.initShapeCache()
                }

                block.lootTableKey

                builder.registerBlockItem(block)
            }
        }
    }
}
