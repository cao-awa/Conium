@file:Suppress("UNCHECKED_CAST")

package com.github.cao.awa.conium.kotlin.extent.component

import com.github.cao.awa.conium.mixin.component.map.builder.ComponentMapBuilderAccessor
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap
import net.minecraft.component.ComponentMap
import net.minecraft.component.ComponentType
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.component.type.ConsumableComponent
import net.minecraft.component.type.ToolComponent
import net.minecraft.item.consume.UseAction

// Acquires components map.
val ComponentMap.Builder.components: Reference2ObjectMap<ComponentType<*>, Any> get() = (this as ComponentMapBuilderAccessor).components

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
fun <T : Any, Y : Any> ComponentMap.Builder.withComponent(
    type: ComponentType<T>,
    creator: () -> T,
    compute: Pair<(T) -> Y, (Y) -> T>,
    callback: (Y) -> Unit = { }
): ComponentMap.Builder {
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

fun <T: Any> ComponentMap.Builder.getOrCreate(type: ComponentType<T>, creator: () -> T): T {
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
fun <T : Any, Y : Any> ComponentMap.Builder.withComponentProvides(
    type: ComponentType<T>,
    creator: () -> T,
    compute: (T, Y) -> T,
    callback: () -> Y
): ComponentMap.Builder {
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
fun <T : Any> ComponentMap.Builder.withComponent(type: ComponentType<T>, creator: () -> T, callback: (T) -> Unit): ComponentMap.Builder {
    // Ensure value is present, then operate it, put back to make it still presents in next acquired.
    this.components[type] = getOrCreate(type, creator).also(callback)

    return this
}

// Let the component rebuild and put it back to components map when the target component type is present.
fun <T : Any> ComponentMap.Builder.rebuild(type: ComponentType<T>, creator: (T) -> T): ComponentMap.Builder {
    (this.components[type] as? T)?.let {
        this.components[type] = creator(it)
    }

    return this
}

// Operates the component when the target component type is present.
fun <T : Any> ComponentMap.Builder.acquire(type: ComponentType<T>, creator: (T) -> Unit, callback: (T) -> Unit = { }): ComponentMap.Builder {
    (this.components[type] as? T)?.let {
        creator(it)
        callback(it)
    }

    return this
}

// Create value of 'AttributeModifiersComponent'.
fun withCreateAttributeModifiers(): () -> AttributeModifiersComponent = { AttributeModifiersComponent(CollectionFactor.arrayList(), true) }

// Acquires attribute modifiers list, make new attribute modifiers component after operated entries.
fun withComputeAttributeModifiers(): Pair<(AttributeModifiersComponent) -> MutableList<AttributeModifiersComponent.Entry>, (MutableList<AttributeModifiersComponent.Entry>) -> AttributeModifiersComponent> = Pair(
    { CollectionFactor.arrayList(it.modifiers) },
    { AttributeModifiersComponent(it, true) }
)

// Create value of 'ToolComponent'.
fun withCreateTool(): () -> ToolComponent = { ToolComponent(CollectionFactor.arrayList(), 1.0F, 1) }

// Acquires tool rule list, make new tool component after operated rules.
fun withComputeTool(): Pair<(ToolComponent) -> MutableList<ToolComponent.Rule>, (MutableList<ToolComponent.Rule>) -> ToolComponent> = Pair(
    { it.rules },
    { ToolComponent(it, 1.0F, 1) }
)

fun withCreateConsumable(): () -> ConsumableComponent = { ConsumableComponent.builder().build() }

fun withComputeUseAction(): (ConsumableComponent, UseAction) -> ConsumableComponent = { consumable, action ->
    ConsumableComponent(
        consumable.consumeSeconds,
        action,
        consumable.sound,
        consumable.hasConsumeParticles,
        consumable.onConsumeEffects
    )
}
