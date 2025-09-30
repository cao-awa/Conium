package com.github.cao.awa.conium.tag.inject

import com.github.cao.awa.conium.kotlin.extent.registry.tags
import com.github.cao.awa.conium.mapping.yarn.RegistryKeys
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import org.jetbrains.annotations.TestOnly
import java.util.Optional

class ConiumTagInjector {
    companion object {
        @TestOnly
        fun test() {
            Registries.BLOCK.getOptional(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("minecraft:oak_log")))
                .ifPresent {
                    it.tags = ObjectOpenHashSet(it.tags).also { it.add(TagKey.of(RegistryKeys.BLOCK, Identifier.of("conium:test"))) }
                }
        }
    }
}
