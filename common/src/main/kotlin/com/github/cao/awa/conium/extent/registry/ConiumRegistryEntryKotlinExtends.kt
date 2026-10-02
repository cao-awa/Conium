@file:Suppress("UNCHECKED_CAST")

package com.github.cao.awa.conium.kotlin.extent.registry

import com.github.cao.awa.conium.mixin.registry.RegistryEntryReferenceAccessor
import net.minecraft.core.Holder
import net.minecraft.tags.TagKey

// Access tags in reference registry entry.
var <T : Any> Holder.Reference<T>.tags: Set<TagKey<T>>
    // Get or create the tags.
    get() = (this as RegistryEntryReferenceAccessor<T>).tags ?: HashSet()
    // Set the tags.
    set(value) = (this as RegistryEntryReferenceAccessor<T>).tags(value)
