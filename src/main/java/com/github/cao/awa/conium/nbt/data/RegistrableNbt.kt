package com.github.cao.awa.conium.nbt.data

import com.github.cao.awa.sinuatum.manipulate.Manipulate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper

class RegistrableNbt(private val registries: Map<String, ConiumNbtDataSerializer<*>>) {
    private val values: MutableMap<String, Any> = CollectionFactor.hashMap()

    operator fun <X> get(key: String): X {
        return Manipulate.cast(this.values[key])
    }

    operator fun set(key: String, value: Any) {
        this.values[key] = value
    }
    
    fun writeTo(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup) {
        for ((name, serializer) in this.registries) {
            serializer.write(nbt, registries, name, Manipulate.cast(this.values[name]))
        }
    }

    fun readFrom(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup) {
        for ((name, serializer) in this.registries) {
            this[name] = serializer.read(nbt, registries, name)!!
        }
    }
}