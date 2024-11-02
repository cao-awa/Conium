package com.github.cao.awa.conium

import com.github.cao.awa.conium.component.ConiumComponentTypes
import com.github.cao.awa.conium.datapack.block.ConiumBlockManager
import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInjectManager
import com.github.cao.awa.conium.datapack.item.ConiumItemManager
import com.github.cao.awa.conium.datapack.script.ConiumScriptManager
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.function.consumer.string.`object`.*
import com.github.cao.awa.conium.script.translate.ConiumScriptTranslator
import com.github.cao.awa.conium.template.ConiumTemplates
import com.github.cao.awa.language.translator.translate.LanguageTranslator
import com.github.cao.awa.language.translator.translate.lang.TranslateTarget
import com.github.cao.awa.language.translator.translate.lang.element.TranslateElementData
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.jetbrains.annotations.NotNull
import java.util.function.Supplier

class Conium : ModInitializer {
    companion object {
        private val LOGGER = LogManager.getLogger("Conium")

        @JvmField
        var VERSION = "1.0.0-alpha1"

        @JvmField
        var LANGUAGE_TRANSLATOR_VERSION = LanguageTranslator.getVersion()

        @JvmField
        var itemInjectManager: ItemPropertyInjectManager? = null

        @JvmField
        var coniumItemManager: ConiumItemManager? = null

        @JvmField
        var coniumBlockManager: ConiumBlockManager? = null

        @JvmField
        var scriptManager: ConiumScriptManager? = null

        @JvmField
        var enableDebugs = true

        @JvmField
        var allowBedrock = true

        @JvmStatic
        fun debug(debugger: Runnable) {
            if (enableDebugs) {
                debugger.run()
            }
        }

        @JvmStatic
        fun debug(message: String?, p1: Supplier<Any?>, debugger: StrObjConsumer1) {
            if (enableDebugs) {
                debugger.accept(message, p1.get())
            }
        }

        @JvmStatic
        fun debug(message: String?, p1: Supplier<Any?>, p2: Supplier<Any?>, debugger: StrObjConsumer2) {
            if (enableDebugs) {
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
            if (enableDebugs) {
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
            if (enableDebugs) {
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
            if (enableDebugs) {
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
            if (enableDebugs) {
                debugger.accept(message, p1.get(), p2.get(), p3.get(), p4.get(), p5.get(), p6.get())
            }
        }
    }

    override fun onInitialize() {
        // Initialize for item injecting.
        ConiumComponentTypes.init()

        // Initialize for events
        ConiumEvent.init()

        // Initialize for templates
        ConiumTemplates.init()

        // Initialize script translator for bedrock's typescript.
        LOGGER.info("Loading conium '{}' language translator providers for [typescript]", VERSION)
        ConiumScriptTranslator.postRegister()

        val typescriptTranslators = LanguageTranslator.getTranslators("conium")
        LOGGER.info(
            "The conium language translator provider has loaded {} translators: {}",
            typescriptTranslators.size,
            collectTranslators(typescriptTranslators)
        )
    }

    private fun collectTranslators(translators: Map<TranslateTarget, Map<TranslateElementData<*>, LanguageTranslator<*>>>): Map<TranslateTarget, Collection<Class<*>>> {
        val result: MutableMap<TranslateTarget, Collection<Class<*>>> = CollectionFactor.hashMap()
        translators.forEach { (target, targetTranslators) ->
            result[target] = targetTranslators.keys.map { it.clazz() }
        }
        return result
    }
}
