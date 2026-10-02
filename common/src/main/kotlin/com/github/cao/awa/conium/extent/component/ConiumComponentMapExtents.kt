@file:Suppress("UNCHECKED_CAST")

package com.github.cao.awa.conium.kotlin.extent.component

import com.github.cao.awa.conium.mixin.component.map.builder.ComponentMapBuilderAccessor
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap
import net.minecraft.core.component.DataComponentMap
import net.minecraft.core.component.DataComponentType
import net.minecraft.world.item.component.ItemAttributeModifiers
import net.minecraft.world.item.component.Consumable
import net.minecraft.world.item.component.Tool
import net.minecraft.world.item.ItemUseAnimation

// Acquires components map.
val DataComponentMap.Builder.components: Reference2ObjectMap<DataComponentType<*>, Any> get() = (this as ComponentMapBuilderAccessor).components

/**
 * Computes the old component data and put the new a component to components map.
 *
 * @param type the type of component
 * @param creator callback that create the value when missing
 * @param compute callback that acquire and vary an element and make a new component
 * @param callback callback that received an element from 'compute' acquired, operate it
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
fun <T : Any, Y : Any> DataComponentMap.Builder.withComponent(
    type: DataComponentType<T>,
    creator: () -> T,
    compute: Pair<(T) -> Y, (Y) -> T>,
    callback: (Y) -> Unit = { }
): DataComponentMap.Builder {
    // Operates value and put new value back to components map.
    this.components[type] = getOrCreate(type) {
        // Create value when missing.
        // This lambda won't be calls if value is presents.
        creator()
    }.let { t ->
        // Make new value.
        compute.first(t).also { y ->
            // Operate elements.
            callback(y)
        }.let { y ->
            // Create new value instance.
            compute.second(y)
        }
    }

    return this
}

fun <T: Any> DataComponentMap.Builder.getOrCreate(type: DataComponentType<T>, creator: () -> T): T {
    return (this.components[type] ?: creator()) as T
}

/**
 * Computes the old component data and put a new component to components map.
 *
 * @param type the type of component
 * @param creator callback that create the value when missing
 * @param compute callback that acquire the component instance and vary an element and make a new component
 * @param callback callback that provides an element to 'compute'
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
fun <T : Any, Y : Any> DataComponentMap.Builder.withComponentProvides(
    type: DataComponentType<T>,
    creator: () -> T,
    compute: (T, Y) -> T,
    callback: () -> Y
): DataComponentMap.Builder {
    // Operates value and put new value back to components map.
    this.components[type] = getOrCreate(type) {
        // Create value when missing.
        // This lambda won't be calls if value is presents.
        creator()
    }.let { t ->
        // Make new value in the component.
        compute(t, callback())
    }

    return this
}

/**
 * Computes the component and put it to components map.
 *
 * @param type the type of component
 * @param creator callback that create the value when missing
 * @param callback callback that received component value, operate it
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
fun <T : Any> DataComponentMap.Builder.withComponent(type: DataComponentType<T>, creator: () -> T, callback: (T) -> Unit): DataComponentMap.Builder {
    // Ensure value is present, then operate it, put back to make it still presents in next acquired.
    this.components[type] = getOrCreate(type, creator).also(callback)

    return this
}

// Let the component rebuild and put it back to components map when the target component type is present.
fun <T : Any> DataComponentMap.Builder.rebuild(type: DataComponentType<T>, creator: (T) -> T): DataComponentMap.Builder {
    (this.components[type] as? T)?.let {
        this.components[type] = creator(it)
    }

    return this
}

// Operates the component when the target component type is present.
fun <T : Any> DataComponentMap.Builder.acquire(type: DataComponentType<T>, creator: (T) -> Unit, callback: (T) -> Unit = { }): DataComponentMap.Builder {
    (this.components[type] as? T)?.let {
        creator(it)
        callback(it)
    }

    return this
}

// Create value of 'ItemAttributeModifiers'.
fun withCreateAttributeModifiers(): () -> ItemAttributeModifiers = { ItemAttributeModifiers(ArrayList()) }

// Acquires attribute modifiers list, make new attribute modifiers component after operated entries.
fun withComputeAttributeModifiers(): Pair<(ItemAttributeModifiers) -> MutableList<ItemAttributeModifiers.Entry>, (MutableList<ItemAttributeModifiers.Entry>) -> ItemAttributeModifiers> = Pair(
    { ArrayList(it.modifiers()) },
    { ItemAttributeModifiers(it) }
)

// Create value of 'Tool'.
fun withCreateTool(): () -> Tool = { Tool(ArrayList(), 1.0F, 1, true) }

// Acquires the tool rule list, to make a new tool component after operated rules.
fun withComputeTool(): Pair<(Tool) -> MutableList<Tool.Rule>, (MutableList<Tool.Rule>) -> Tool> = Pair(
    { it.rules() },
    { Tool(it, 1.0F, 1, true) }
)

fun withCreateConsumable(): () -> Consumable = { Consumable.builder().build() }

fun withComputeUseAction(): (Consumable, ItemUseAnimation) -> Consumable = { consumable, action ->
    Consumable(
        consumable.consumeSeconds(),
        action,
        consumable.sound(),
        consumable.hasConsumeParticles(),
        consumable.onConsumeEffects()
    )
}
