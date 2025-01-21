@file:Suppress("UNCHECKED_CAST")

package com.github.cao.awa.conium.kotlin.extent.registry

import com.github.cao.awa.conium.mixin.registry.RegistryEntryReferenceMixin
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.tag.TagKey

// Access tags in reference registry entry.
var <T> RegistryEntry.Reference<T>.tags: Set<TagKey<T>>
    // Get or create the tags.
    get() = (this as RegistryEntryReferenceMixin<T>).tags ?: CollectionFactor.hashSet()
    // Set the tags.
    set(value) = (this as RegistryEntryReferenceMixin<T>).tags(value)
