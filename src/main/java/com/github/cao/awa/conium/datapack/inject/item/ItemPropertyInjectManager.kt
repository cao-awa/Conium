package com.github.cao.awa.conium.datapack.inject.item

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction
import com.github.cao.awa.conium.datapack.inject.item.action.handler.ItemPropertyInjectHandler
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponent
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponentValue
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.google.gson.*
import net.minecraft.component.ComponentType
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.resource.JsonDataLoader
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ItemPropertyInjectManager(private val registryLookup: RegistryWrapper.WrapperLookup) :
    JsonDataLoader(GSON, RegistryKeys.getPath(ConiumRegistryKeys.ITEM_PROPERTY_INJECT)) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ItemPropertyInjectManager")
        private val GSON: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    }

    private val injects = ApricotCollectionFactor.hashMap<Item, MutableList<ItemPropertyInject<*>>>()

    override fun apply(prepared: Map<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        for ((key, value) in prepared) {
            value as JsonObject

            val itemTarget = value["target"].asString

            // Use to debug, trace inject details.
            Conium.debug(
                "Injecting property to item '{}' from '{}'",
                { itemTarget },
                key::getNamespace,
                LOGGER::info
            )

            val injecting: ItemPropertyInject<*> = ItemPropertyInject.deserialize(value.asJsonObject)

            val item = Registries.ITEM[Identifier.of(itemTarget)]

            this.injects.computeIfAbsent(item) { ApricotCollectionFactor.arrayList() }

            this.injects[item]!!.add(injecting)
        }
    }

    fun injects(item: Item): List<ItemPropertyInject<*>> {
        return this.injects[item] ?: emptyList()
    }

    fun inject(stack: ItemStack) = injectProperty(stack, injects(stack.item))

    fun injectProperty(stack: ItemStack, injects: List<ItemPropertyInject<*>>) {
        for (inject in injects) {
            injectComponent(stack, inject.components)
        }
    }

    fun injectComponent(stack: ItemStack, injects: List<ItemPropertyInjectComponent<*>>) {
        // Inject to current stack.
        for (component in injects) {
            val value = component.value
            val type = component.type

            // Do not append the preset value when the component is present.
            if (component.action == ItemPropertyInjectAction.SET_PRESET) {
                if (!stack.contains(type)) {
                    injectDefault(stack, type, value)
                }

                continue
            }

            // When the component is present, do actions.
            val currentValue = stack.get(type)
            val calculatedValue = ItemPropertyInjectHandler.doHandles(currentValue, value.value, component.action)

            // Use to debug, trace inject details.
            Conium.debug(
                "Injecting: '{}' as '{}' to item '{}' using '{}'('{}' -> '{}')",
                { type },
                { calculatedValue },
                { Registries.ITEM.getId(stack.item) },
                component::action,
                { currentValue },
                value::value,
                LOGGER::info
            )

            // Modifies present value.
            stack.set(type, Manipulate.cast(calculatedValue))
        }
    }

    fun injectDefault(stack: ItemStack, type: ComponentType<*>, value: ItemPropertyInjectComponentValue<*>) {
        // Use to debug, trace inject details.
        Conium.debug(
            "Injecting: '{}' as '{}' to item '{}' using '{}'",
            { type },
            value::value,
            { Registries.ITEM.getId(stack.item) },
            { ItemPropertyInjectAction.SET_PRESET },
            LOGGER::info
        )

        // Append preset value to item.
        stack.set(type, Manipulate.cast(value.value))
    }
}
