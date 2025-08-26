package com.github.cao.awa.conium.datapack.inject.item

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.datapack.inject.item.action.ItemPropertyInjectAction
import com.github.cao.awa.conium.datapack.inject.item.action.handler.ItemPropertyInjectHandler
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponent
import com.github.cao.awa.conium.datapack.inject.item.component.ItemPropertyInjectComponentValue
import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.component.ComponentType
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.Registries
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ItemPropertyInjectManager(var registryLookup: RegistryWrapper.WrapperLookup) : ConiumJsonDataLoader(ConiumRegistryKeys.ITEM_PROPERTY_INJECT.value) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ItemPropertyInjectManager")
    }

    private val injects: HashMap<Item, MutableList<ItemPropertyInject<*>>> = CollectionFactor.hashMap()

    override fun earlyLoad(manager: ResourceManager, dataType: Identifier, result: MutableMap<Identifier, JsonElement>) {
        // Nothing here.
    }

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        for ((key: Identifier, value: JsonElement) in prepared) {
            inject(key, value as JsonObject)
        }
    }

    fun inject(identifier: Identifier, json: JsonObject) {
        val itemTarget = json["target"].asString

        // Use to debug, trace inject details.
        Conium.debug(
            "Injecting property to item '{}' from '{}'",
            { itemTarget },
            identifier::getNamespace,
            LOGGER::info
        )

        val injecting: ItemPropertyInject<*> = ItemPropertyInject.deserialize(json)

        val item: Item = Registries.ITEM[Identifier.of(itemTarget)]

        this.injects.computeIfAbsent(item) { CollectionFactor.arrayList() }

        this.injects[item]!!.add(injecting)
    }

    fun injects(item: Item): List<ItemPropertyInject<*>> {
        return this.injects[item] ?: emptyList()
    }

    fun inject(stack: ItemStack) = injectProperty(stack, injects(stack.item))

    fun injectProperty(stack: ItemStack, injects: List<ItemPropertyInject<*>>) {
        for (inject: ItemPropertyInject<*> in injects) {
            injectComponent(stack, inject.components)
        }
    }

    fun injectComponent(stack: ItemStack, injects: List<ItemPropertyInjectComponent<*>>) {
        // Inject to current stack.
        for (component: ItemPropertyInjectComponent<*> in injects) {
            val value: ItemPropertyInjectComponentValue<*> = component.value
            val type: ComponentType<*> = component.type

            // Do not append the preset value when the component is present.
            if (component.action == ItemPropertyInjectAction.SET_PRESET) {
                if (!stack.contains(type)) {
                    injectDefault(stack, type, value)
                }

                continue
            }

            // When the component is present, do actions.
            val currentValue: Any? = stack.get(type)
            val calculatedValue: Any? = ItemPropertyInjectHandler.doHandles(currentValue, value.value, component.action)

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
            stack.set(type, calculatedValue.doCast())
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
        stack.set(type, value.value.doCast())
    }
}
