package com.github.cao.awa.conium.script

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.type.ConiumEventType
import java.io.File
import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class ScriptManager {
    companion object {
        @JvmField
        val engine: ScriptEngine = ScriptEngineManager(Thread.currentThread().contextClassLoader).getEngineByExtension("kts")
    }
    val scripts: MutableList<File> = ApricotCollectionFactor.arrayList()

    fun reload() {
        this.scripts.clear()
        ConiumEvent.resetForever()

        val file = File("test/")

        file.listFiles()?.forEach {
            engine.eval(it.readText(Charsets.UTF_8))
            this.scripts.add(it)
        }
    }

    fun eventContext(eventType: ConiumEventType): MutableList<ConiumEventContext<*>> {
        return ConiumEvent.forever(eventType)
    }
}