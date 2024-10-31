package com.github.cao.awa.conium.kotlin.extent.component

import com.github.cao.awa.conium.mixin.component.map.builder.ComponentMapBuilderAccessor
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap
import net.minecraft.component.ComponentMap
import net.minecraft.component.ComponentType
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.component.type.ToolComponent

// Acquires components map.
val ComponentMap.Builder.component: Reference2ObjectMap<ComponentType<*>, Any> get() = (this as ComponentMapBuilderAccessor).components

/**
 * Computes the component and put it to components map.
 *
 * @param type the type of component
 * @param creator callback that create the value when missing
 * @param compute callback that acquire and vary an element then make value again
 * @param callback callback that received an element from 'compute' acquired, operate it
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
fun <T, Y> ComponentMap.Builder.withComponent(
    type: ComponentType<T>,
    creator: () -> T & Any,
    compute: Pair<(T) -> Y, (Y) -> T>,
    callback: (Y) -> Unit = { }
) {
    // Operates value and put new value back to components map.
    this.component[type] = getOrCreate(type) {
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
fun <T> ComponentMap.Builder.withComponent(type: ComponentType<T>, creator: () -> T & Any, callback: (T) -> Unit) {
    // Ensure value is present, then operate it, put back to make it still presents in next acquired.
    this.component[type] = getOrCreate(type, creator).also(callback)
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
