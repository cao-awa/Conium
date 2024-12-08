package com.github.cao.awa.conium

import com.github.cao.awa.conium.client.ConiumClient
import com.github.cao.awa.conium.component.ConiumComponentTypes
import com.github.cao.awa.conium.datapack.block.ConiumBlockManager
import com.github.cao.awa.conium.datapack.entity.ConiumEntityManager
import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInjectManager
import com.github.cao.awa.conium.datapack.item.ConiumItemManager
import com.github.cao.awa.conium.datapack.script.ConiumScriptManager
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.function.consumer.string.`object`.*
import com.github.cao.awa.conium.script.translate.ConiumScriptTranslator
import com.github.cao.awa.conium.server.datapack.ConiumContentDatapack
import com.github.cao.awa.conium.server.datapack.ConiumServerLoadDatapacks
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.github.cao.awa.language.translator.translate.LanguageTranslator
import com.github.cao.awa.language.translator.translate.lang.TranslateTarget
import com.github.cao.awa.language.translator.translate.lang.element.TranslateElementData
import com.github.cao.awa.sinuatum.resource.loader.ResourceLoader
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.github.cao.awa.sinuatum.util.io.IOUtil
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.function.Supplier

class Conium : ModInitializer {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("Conium")

        val isClient: Boolean get() = ConiumClient.initialized

        @JvmField
        var VERSION = "1.0.0-alpha6"

        @JvmField
        var LANGUAGE_TRANSLATOR_VERSION: String = LanguageTranslator.getVersion()

        @JvmField
        var itemInjectManager: ItemPropertyInjectManager? = null

        @JvmField
        var coniumItemManager: ConiumItemManager? = null

        @JvmField
        var coniumBlockManager: ConiumBlockManager? = null

        @JvmField
        var coniumEntityManager: ConiumEntityManager? = null

        @JvmField
        var scriptManager: ConiumScriptManager? = null

        @JvmField
        val reloadCallbacks: MutableList<Runnable> = CollectionFactor.arrayList()

        @JvmField
        var enableDebugs: Boolean = false

        @JvmField
        var allowBedrock: Boolean = true

        @JvmField
        var pendingDatapack: ConiumServerLoadDatapacks = ConiumServerLoadDatapacks()

        @JvmStatic
        fun onLoadData(datapackIdentifier: Identifier, resourceIdentifier: Identifier, content: String) {
            synchronized(this) {
                try {
                    this.pendingDatapack.datapacks.computeIfAbsent(
                        datapackIdentifier,
                        ::ConiumContentDatapack
                    ).contents[resourceIdentifier] = content
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        @JvmStatic
        fun debug(debugger: Runnable) {
            if (this.enableDebugs) {
                debugger.run()
            }
        }

        @JvmStatic
        fun debug(message: String?, p1: Supplier<Any?>, debugger: StrObjConsumer1) {
            if (this.enableDebugs) {
                debugger.accept(message, p1.get())
            }
        }

        @JvmStatic
        fun debug(message: String?, p1: Supplier<Any?>, p2: Supplier<Any?>, debugger: StrObjConsumer2) {
            if (this.enableDebugs) {
                debugger.accept(message, p1.get(), p2.get())
            }
        }

        @JvmStatic
        fun debug(
            message: String?,
            p1: Supplier<Any?>,
            p2: Supplier<Any?>,
            p3: Supplier<Any?>,
            debugger: StrObjConsumer3
        ) {
            if (this.enableDebugs) {
                debugger.accept(message, p1.get(), p2.get(), p3.get())
            }
        }

        @JvmStatic
        fun debug(
            message: String?,
            p1: Supplier<Any?>,
            p2: Supplier<Any?>,
            p3: Supplier<Any?>,
            p4: Supplier<Any?>,
            debugger: StrObjConsumer4
        ) {
            if (this.enableDebugs) {
                debugger.accept(message, p1.get(), p2.get(), p3.get(), p4.get())
            }
        }

        @JvmStatic
        fun debug(
            message: String?,
            p1: Supplier<Any?>,
            p2: Supplier<Any?>,
            p3: Supplier<Any?>,
            p4: Supplier<Any?>,
            p5: Supplier<Any?>,
            debugger: StrObjConsumer5
        ) {
            if (this.enableDebugs) {
                debugger.accept(message, p1.get(), p2.get(), p3.get(), p4.get(), p5.get())
            }
        }

        @JvmStatic
        fun debug(
            message: String?,
            p1: Supplier<Any?>,
            p2: Supplier<Any?>,
            p3: Supplier<Any?>,
            p4: Supplier<Any?>,
            p5: Supplier<Any?>,
            p6: Supplier<Any?>,
            debugger: StrObjConsumer6
        ) {
            if (this.enableDebugs) {
                debugger.accept(message, p1.get(), p2.get(), p3.get(), p4.get(), p5.get(), p6.get())
            }
        }

        private fun collectTranslators(translators: Map<TranslateTarget, Map<TranslateElementData<*>, LanguageTranslator<*>>>): Map<TranslateTarget, Collection<Class<*>>> {
            val result: MutableMap<TranslateTarget, Collection<Class<*>>> = CollectionFactor.hashMap()
            translators.forEach { (target: TranslateTarget, targetTranslators: Map<TranslateElementData<*>, LanguageTranslator<*>>) ->
                result[target] = targetTranslators.keys.map { it.clazz() }
            }
            return result
        }

        private fun printBanner() {
            for (line: String in IOUtil.read(ResourceLoader.get("assets/conium/banner.txt")).lines()) {
                LOGGER.info(line)
            }
        }
    }

    override fun onInitialize() {
        printBanner()

        // Initialize for item injecting.
        ConiumComponentTypes.init()
        ConiumComponentTypes.types().let { dataComponents ->
            LOGGER.info("Loaded ${dataComponents.size} data components: $dataComponents")
            debug(
                "Loaded {} data components: {}",
                { dataComponents.size },
                { dataComponents },
                LOGGER::info
            )
        }

        // Initialize for events
        ConiumEvent.init()
        ConiumEvent.events().let { events ->
            LOGGER.info("Loaded {} events", events.size)
            debug(
                "Loaded {} events: {}",
                events::size,
                { events },
                LOGGER::info
            )
        }

        // Initialize for templates
        ConiumTemplates.init()
        ConiumTemplate.templates().let { templates ->
            LOGGER.info(
                "Loaded {} templates ({} conium templates, {} bedrock templates/components)",
                templates.size,
                ConiumTemplate.coniumCount(),
                ConiumTemplate.bedrockCount()
            )
            debug(
                "Loaded {} templates ({} conium templates, {} bedrock templates/components): {}",
                templates::size,
                { ConiumTemplate.coniumCount() },
                { ConiumTemplate.bedrockCount() },
                { templates },
                LOGGER::info
            )
        }

        // Initialize script translator for bedrock's typescript.
        LOGGER.info("Loading conium '{}' language translator providers for [typescript]", VERSION)
        ConiumScriptTranslator.postRegister()

        LanguageTranslator.getTranslators("conium").let { translators ->
            LOGGER.info(
                "Loaded {} translators by conium language providers({})",
                translators.size,
                VERSION
            )
            debug(
                "Loaded {} translators by conium language providers({}): {}",
                translators::size,
                { VERSION },
                { collectTranslators(translators) },
                LOGGER::info
            )
        }

        LOGGER.info("Loaded {} conium events", ConiumEvent.count())
        debug(
            "Loaded {} conium events: {}",
            { ConiumEvent.count() },
            { ConiumEvent.events() },
            LOGGER::info
        )
    }
}
