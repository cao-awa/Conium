@file:Suppress("unchecked_cast")

package com.github.cao.awa.conium.datapack.block

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.block.builder.bedrock.BedrockSchemaBlockBuilder
import com.github.cao.awa.conium.block.builder.conium.ConiumSchemaBlockBuilder
import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.kotlin.extent.block.register
import com.github.cao.awa.conium.kotlin.extent.block.registerBlock
import com.github.cao.awa.conium.kotlin.extent.item.registerBlockItem
import com.github.cao.awa.conium.extent.caster.cast
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.conium.registry.extend.ConiumDynamicIdList
import com.github.cao.awa.conium.registry.extend.ConiumDynamicRegistry
import com.google.common.collect.UnmodifiableIterator
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.resources.Identifier
import net.minecraft.util.profiling.ProfilerFiller
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumBlockManager(var registryLookup: HolderLookup.Provider) : ConiumJsonDataLoader(ConiumRegistryKeys.BLOCK.identifier()) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumBlockManager")
    }

    override fun earlyLoad(manager: ResourceManager, dataType: Identifier, result: MutableMap<Identifier, JsonElement>) {
        resetRegistries()

        for ((key: Identifier, value: JsonElement) in result) {
            load(key, value as JsonObject)
        }
    }

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: ProfilerFiller) {
    }

    fun resetRegistries() {
        (BuiltInRegistries.BLOCK as ConiumDynamicRegistry).clearDynamic()
        (Block.BLOCK_STATE_REGISTRY as ConiumDynamicIdList<BlockState>).clearDynamic()
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

            Conium.coniumItemManager!!.pendingBlockItem(BuiltInRegistries.BLOCK.getKey(block)) {
                settings: Item.Properties -> BlockItem(block, settings)
            }
        }
    }

    fun registerBlockStates(block: Block) {
        val stateIds: ConiumDynamicIdList<BlockState> = (Block.BLOCK_STATE_REGISTRY as Any).cast()

        val var2 = block.stateDefinition.possibleStates.iterator()

        while (var2.hasNext()) {
            val blockState: BlockState = var2.next() as BlockState
            stateIds.addDynamic(blockState)
            blockState.initCache()
        }

        block.lootTable
    }

    fun register(
        identifier: Identifier,
        blockProvider: (BlockBehaviour.Properties) -> Block,
        itemSettings: ((Item.Properties) -> Unit)? = null
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
