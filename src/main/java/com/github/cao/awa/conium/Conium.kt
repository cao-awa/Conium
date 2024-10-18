package com.github.cao.awa.conium

import com.github.cao.awa.conium.component.ConiumComponentTypes
import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInjectManager
import com.github.cao.awa.conium.datapack.item.ConiumItemManager
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.function.consumer.string.`object`.*
import com.github.cao.awa.conium.script.ScriptManager
import com.github.cao.awa.conium.template.ConiumTemplates
import net.fabricmc.api.ModInitializer
import java.util.function.Supplier

class Conium : ModInitializer {
    companion object {
        @JvmField
        var itemInjectManager: ItemPropertyInjectManager? = null

        @JvmField
        var coniumItemManager: ConiumItemManager? = null

        @JvmField
        var scriptManager: ScriptManager = ScriptManager()

        @JvmField
        var enableDebugs: Boolean = true

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
    }
}
