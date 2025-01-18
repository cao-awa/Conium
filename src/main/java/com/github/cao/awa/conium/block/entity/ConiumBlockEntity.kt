package com.github.cao.awa.conium.block.entity

import com.github.cao.awa.conium.block.entity.setting.ConiumBlockEntitySettings
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.math.BlockPos

class ConiumBlockEntity(
    private val setting: ConiumBlockEntitySettings,
    pos: BlockPos,
    state: BlockState
) : BlockEntity(setting.type, pos, state) {
    private val data: RegistrableNbt = RegistrableNbt(this.setting.registeredData).also {
        for ((key, value) in this.setting.defaultData) {
            it[key] = value
        }
    }

    /**
     * Write the block entity data to NBT compound using registrable-NBT.
     *
     * @see NbtCompound
     * @see RegistrableNbt
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun writeNbt(nbt: NbtCompound, registries: WrapperLookup) = this.data.writeTo(nbt, registries)

    /**
     * Read the block entity data from NBT compound using registrable-NBT.
     *
     * @see NbtCompound
     * @see RegistrableNbt
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun readNbt(nbt: NbtCompound, registries: WrapperLookup) = this.data.readFrom(nbt, registries)

    /**
     * To get a non-null data using key name, should ensure it already registered.
     *
     * @see NbtCompound
     * @see RegistrableNbt
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    operator fun <X> get(key: String): X = this.data[key]

    /**
     * To set the data value using key name, should ensure it already registered and won't be input a null value.
     *
     * @see NbtCompound
     * @see RegistrableNbt
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    operator fun set(key: String, value: Any) {
        this.data[key] = value
    }
}
