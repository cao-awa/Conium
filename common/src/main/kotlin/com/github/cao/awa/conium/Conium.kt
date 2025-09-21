package com.github.cao.awa.conium

import com.github.cao.awa.conium.client.ConiumClient
import com.github.cao.awa.conium.component.ConiumComponentTypes
import com.github.cao.awa.conium.config.ConiumConfig
import com.github.cao.awa.conium.datapack.block.ConiumBlockManager
import com.github.cao.awa.conium.datapack.entity.ConiumEntityManager
import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInjectManager
import com.github.cao.awa.conium.datapack.item.ConiumItemManager
import com.github.cao.awa.conium.datapack.worldgen.ConiumPlacedFeatureManager
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.dsl.DSLSample
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.function.consumer.string.obj.*
import com.github.cao.awa.conium.hitokoto.ConiumHitokoto
import com.github.cao.awa.conium.molang.MolangSupport
import com.github.cao.awa.conium.molang.translator.MolangKotlinScriptTranslator
import com.github.cao.awa.conium.script.index.common.request
import com.github.cao.awa.conium.script.manager.ConiumScriptManager
import com.github.cao.awa.conium.script.translate.ConiumScriptTranslator
import com.github.cao.awa.conium.server.datapack.ConiumContentDatapack
import com.github.cao.awa.conium.server.datapack.ConiumServerLoadDatapacks
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.github.cao.awa.sinuatum.resource.loader.ResourceLoader
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.github.cao.awa.sinuatum.util.io.IOUtil
import com.github.cao.awa.translator.structuring.builtin.typescript.translate.kts.TypescriptKotlinScriptTranslator
import com.github.cao.awa.translator.structuring.translate.StructuringTranslator
import com.github.cao.awa.translator.structuring.translate.element.TranslateElementData
import com.github.cao.awa.translator.structuring.translate.language.LanguageTranslateTarget
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.function.Consumer
import java.util.function.Supplier

class Conium {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("Conium")

        val isClient: Boolean get() = ConiumClient.initialized

        @JvmField
        var VERSION = "1.0.0-alpha14"

        @JvmField
        var STRUCTURING_TRANSLATOR_VERSION: String = StructuringTranslator.getVersion()

        @JvmField
        var itemInjectManager: ItemPropertyInjectManager? = null

        @JvmField
        var coniumItemManager: ConiumItemManager? = null

        @JvmField
        var coniumBlockManager: ConiumBlockManager? = null

        @JvmField
        var coniumEntityManager: ConiumEntityManager? = null

        @JvmField
        var placedFeatureManager: ConiumPlacedFeatureManager? = null

        @JvmField
        var scriptManager: ConiumScriptManager? = null

        @JvmField
        val reloadCallbacks: MutableList<Runnable> = CollectionFactor.arrayList()

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
            if (ConiumConfig.debugs) {
                debugger.run()
            }
        }

        @JvmStatic
        fun debug(message: String, debugger: Consumer<String>) {
            if (ConiumConfig.debugs) {
                debugger.accept(message)
            }
        }

        @JvmStatic
        fun debug(message: String, p1: Supplier<Any?>, debugger: StrObjConsumer1) {
            if (ConiumConfig.debugs) {
                debugger.accept(message, p1.get())
            }
        }

        @JvmStatic
        fun debug(message: String, p1: Supplier<Any?>, p2: Supplier<Any?>, debugger: StrObjConsumer2) {
            if (ConiumConfig.debugs) {
                debugger.accept(message, p1.get(), p2.get())
            }
        }

        @JvmStatic
        fun debug(
            message: String,
            p1: Supplier<Any?>,
            p2: Supplier<Any?>,
            p3: Supplier<Any?>,
            debugger: StrObjConsumer3
        ) {
            if (ConiumConfig.debugs) {
                debugger.accept(message, p1.get(), p2.get(), p3.get())
            }
        }

        @JvmStatic
        fun debug(
            message: String,
            p1: Supplier<Any?>,
            p2: Supplier<Any?>,
            p3: Supplier<Any?>,
            p4: Supplier<Any?>,
            debugger: StrObjConsumer4
        ) {
            if (ConiumConfig.debugs) {
                debugger.accept(message, p1.get(), p2.get(), p3.get(), p4.get())
            }
        }

        @JvmStatic
        fun debug(
            message: String,
            p1: Supplier<Any?>,
            p2: Supplier<Any?>,
            p3: Supplier<Any?>,
            p4: Supplier<Any?>,
            p5: Supplier<Any?>,
            debugger: StrObjConsumer5
        ) {
            if (ConiumConfig.debugs) {
                debugger.accept(message, p1.get(), p2.get(), p3.get(), p4.get(), p5.get())
            }
        }

        @JvmStatic
        fun debug(
            message: String,
            p1: Supplier<Any?>,
            p2: Supplier<Any?>,
            p3: Supplier<Any?>,
            p4: Supplier<Any?>,
            p5: Supplier<Any?>,
            p6: Supplier<Any?>,
            debugger: StrObjConsumer6
        ) {
            if (ConiumConfig.debugs) {
                debugger.accept(message, p1.get(), p2.get(), p3.get(), p4.get(), p5.get(), p6.get())
            }
        }

        /**
         * Print the banner let users known the Conium is loading.
         *
         * @since 1.0.0
         *
         * @author cao_awa
         */
        @JvmStatic
        private fun printBanner() {
            for (line: String in IOUtil.read(ResourceLoader.get("assets/conium/banner.txt")).lines()) {
                LOGGER.info(line)
            }
            LOGGER.info("# {}", ConiumHitokoto.roll())
            LOGGER.info("#")
        }
    }

    /**
     * Initialize the conium framework.
     *
     * @since 1.0.0
     *
     * @see StructuringTranslator
     * @see ConiumScriptTranslator
     *
     * @author cao_awa
     * @author 草二号机
     */
    fun onInitialize() {
        // Print big banner, let user know Conium is loaded.
        // Conium is a very large and complex framework, potential conflict may more than other mods.
        // Need to prevent Conium unexpectedly loads in modpacks that don't use Conium features.
        printBanner()

        // Read config to toggle features.
        ConiumConfig.makeConfig()

        // Initialize for item injecting.
        ConiumComponentTypes.init()
        // Debug only.
        run {
            ConiumComponentTypes.types().let { dataComponents ->
                LOGGER.info("Loaded ${dataComponents.size} data components: $dataComponents")
                debug(
                    "Loaded {} data components: {}",
                    { dataComponents.size },
                    { dataComponents },
                    LOGGER::info
                )
            }
        }

        // Initialize for events.
        ConiumEvent.init()
        // Debug only.
        run {
            ConiumEvent.events().let { events ->
                LOGGER.info("Loaded {} conium events", events.size)
                debug(
                    "Loaded {} conium events: {}",
                    events::size,
                    { events },
                    LOGGER::info
                )
            }
        }

        // Initialize for templates.
        ConiumTemplates.init()
        // Debug only.
        run {
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
        }

        // Initialize script translator for generic typescript translates.
        LOGGER.info("Loading structuring translator {} provider '{}' for [typescript]", STRUCTURING_TRANSLATOR_VERSION, StructuringTranslator.DEFAULT_PROVIDER)
        TypescriptKotlinScriptTranslator.postRegister()
        // Debug only.
        run {
            StructuringTranslator.getTranslators(StructuringTranslator.DEFAULT_PROVIDER).let { translators ->
                LOGGER.info(
                    "The structuring translator provider '{}' has loaded {} translators",
                    StructuringTranslator.DEFAULT_PROVIDER,
                    translators.size
                )
                debug(
                    "The structuring translator provider '{}' has loaded {} translators: {}",
                    { StructuringTranslator.DEFAULT_PROVIDER },
                    { translators.size },
                    { collectTranslators(translators) },
                    LOGGER::info
                )
            }
        }

        // Initialize script translator for conium bedrock's typescript translates.
        LOGGER.info("Loading conium '{}' structuring translator providers for [typescript, molang]", VERSION)
        ConiumScriptTranslator.postRegister()
        MolangKotlinScriptTranslator.postRegister()
        // Debug only.
        run {
            StructuringTranslator.getTranslators("conium").let { translators ->
                LOGGER.info(
                    "Loaded {} translators by conium structuring translator providers({})",
                    translators.size,
                    VERSION
                )
                debug(
                    "Loaded {} translators by conium structuring translator providers({}): {}",
                    translators::size,
                    { VERSION },
                    { collectTranslators(translators) },
                    LOGGER::info
                )
            }
        }

        if (ConiumConfig.debugs) {
            DSLSample.doDslTest()
            MolangSupport.test()

            request(ConiumEventType.PLACE_BLOCK) {
                true
            }

            request(ConiumEventType.PLACED_BLOCK) {

            }
        }
    }

    private fun collectTranslators(translators: MutableMap<LanguageTranslateTarget, MutableMap<TranslateElementData<*>, StructuringTranslator<*>>>): MutableMap<LanguageTranslateTarget, MutableList<Any>> {
        val result = HashMap<LanguageTranslateTarget, MutableList<Any>>()
        translators.forEach { (target: LanguageTranslateTarget, targetTranslators: MutableMap<TranslateElementData<*>, StructuringTranslator<*>>) -> result.put(target, mutableListOf(targetTranslators.keys.stream().map { obj: TranslateElementData<*> -> obj.clazz() }.toList())) }
        return result
    }

}
