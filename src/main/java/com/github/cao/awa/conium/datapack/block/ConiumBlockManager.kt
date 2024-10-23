package com.github.cao.awa.conium.datapack.block

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.extend.ConiumDynamicIdList
import com.github.cao.awa.conium.extend.ConiumDynamicRegistry
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.google.common.collect.UnmodifiableIterator
import com.google.gson.*
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
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

class ConiumBlockManager(private val registryLookup: RegistryWrapper.WrapperLookup) :
    JsonDataLoader(GSON, RegistryKeys.getPath(ConiumRegistryKeys.BLOCK)) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumBlockManager")
        private val GSON: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    }

    override fun apply(prepared: Map<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        (Registries.BLOCK as ConiumDynamicRegistry).clearDynamic()

        val stateIds: ConiumDynamicIdList<BlockState> = Manipulate.cast(Block.STATE_IDS)

        stateIds.clearDynamic()

        ConiumEvent.clearBlockSubscribes()

        for ((key, value) in prepared) {
            value as JsonObject

            val identifier = value.get("id").asString

            // Use to debug, trace inject details.
            Conium.debug(
                "Registering block '{}' from '{}'",
                { identifier },
                key::getNamespace,
                LOGGER::info
            )

            val builder: ConiumBlockBuilder = ConiumBlockBuilder.deserialize(value, this.registryLookup)

            builder.build().let { block ->
                Blocks.register(builder.identifier.toString(), block)

                val var2: UnmodifiableIterator<*> = block.stateManager.states.iterator()

                while (var2.hasNext()) {
                    val blockState = var2.next() as BlockState
                    stateIds.addDynamic(blockState)
                    blockState.initShapeCache()
                }

                block.lootTableKey

                Items.register(builder.identifier, BlockItem(block, Item.Settings()))
            }
        }
    }
}
