@file:Suppress("unchecked_cast")

package com.github.cao.awa.conium.datapack.block

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.block.builder.bedrock.BedrockSchemaBlockBuilder
import com.github.cao.awa.conium.block.builder.conium.ConiumSchemaBlockBuilder
import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.kotlin.extend.block.register
import com.github.cao.awa.conium.kotlin.extend.block.registerBlock
import com.github.cao.awa.conium.kotlin.extend.item.registerBlockItem
import com.github.cao.awa.conium.kotlin.extend.manipulate.doCast
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.conium.registry.extend.ConiumDynamicIdList
import com.github.cao.awa.conium.registry.extend.ConiumDynamicRegistry
import com.google.common.collect.UnmodifiableIterator
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.Registries
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumBlockManager(var registryLookup: RegistryWrapper.WrapperLookup) : ConiumJsonDataLoader(ConiumRegistryKeys.BLOCK.value) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumBlockManager")
    }

    override fun earlyLoad(manager: ResourceManager, dataType: Identifier, result: MutableMap<Identifier, JsonElement>) {
        resetRegistries()

        for ((key: Identifier, value: JsonElement) in result) {
            load(key, value as JsonObject)
        }
    }

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
//        (Block.STATE_IDS as ConiumDynamicIdList<BlockState>).clearDynamic()
//
//        for ((key: Identifier, value: JsonElement) in prepared) {
//            load(key, value as JsonObject)
//        }
    }

    fun resetRegistries() {
        (Registries.BLOCK as ConiumDynamicRegistry).clearDynamic()
        (Block.STATE_IDS as ConiumDynamicIdList<BlockState>).clearDynamic()
    }

    fun load(identifier: Identifier, json: JsonObject) {
        // Use to debug, trace inject details.
        Conium.debug(
            "Registering block '{}' from '{}'",
            identifier::getPath,
            identifier::getNamespace,
            LOGGER::info
        )

        val builder: ConiumBlockBuilder = if (json["schema_style"]?.asString == "conium") {
            ConiumSchemaBlockBuilder.deserialize(json)
        } else {
            BedrockSchemaBlockBuilder.deserialize(json)
        }

        builder.register { block: ConiumBlock ->
            registerBlockStates(block)

            Conium.coniumItemManager!!.pendingBlockItem(Registries.BLOCK.getId(block)) {
                settings: Item.Settings -> BlockItem(block, settings)
            }
        }
    }

    fun registerBlockStates(block: Block) {
        val stateIds: ConiumDynamicIdList<BlockState> = Block.STATE_IDS.doCast()

        val var2: UnmodifiableIterator<*> = block.stateManager.states.iterator()

        while (var2.hasNext()) {
            val blockState: BlockState = var2.next() as BlockState
            stateIds.addDynamic(blockState)
            blockState.initShapeCache()
        }

        block.lootTableKey
    }

    fun register(
        identifier: Identifier,
        blockProvider: (AbstractBlock.Settings) -> Block,
        itemSettings: ((Item.Settings) -> Unit)? = null
    ): Block {
        return registerBlock(identifier, blockProvider).also { block: Block ->
            Conium.debug(
                "Registering block '{}'",
                { block },
                LOGGER::info,
            )

            registerBlockStates(block)

            if (itemSettings != null) {
                registerBlockItem(identifier, block, itemSettings)
            }
        }
    }
}
