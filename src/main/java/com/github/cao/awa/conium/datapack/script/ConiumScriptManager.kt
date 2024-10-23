package com.github.cao.awa.conium.datapack.script

import com.github.cao.awa.apricot.resource.loader.ResourceLoader
import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.apricot.util.io.IOUtil
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.resource.*
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class ConiumScriptManager(private val registryLookup: RegistryWrapper.WrapperLookup) :
    SinglePreparationResourceReloader<List<Resource>>() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumScriptManager")
        private val DATA_TYPE = RegistryKeys.getPath(ConiumRegistryKeys.SCRIPTS)
        private val engine: ScriptEngine =
            ScriptEngineManager(Thread.currentThread().contextClassLoader).getEngineByExtension("kts")
        private val defaultCommons = IOUtil.read(ResourceLoader.get("assets/conium/scripts/conium.commons.kts"))
    }

    override fun prepare(manager: ResourceManager, profiler: Profiler): List<Resource> {
        val scripts = ApricotCollectionFactor.arrayList<Resource>()

        load(manager, scripts)

        return scripts
    }

    fun load(
        manager: ResourceManager,
        results: MutableList<Resource>
    ) {
        val resourceFinder = ResourceFinder(DATA_TYPE, ".kts")
        val scripts: Iterator<MutableMap.MutableEntry<Identifier, Resource>> =
            resourceFinder.findResources(manager).entries.iterator()

        scripts.forEach {
            results.add(it.value)
        }
    }

    override fun apply(prepared: List<Resource>, manager: ResourceManager, profiler: Profiler) {
        ConiumEvent.resetForever()

        engine.eval(defaultCommons)

        for (resource in prepared) {
            engine.eval(resource.reader.readText())
        }
    }
}
