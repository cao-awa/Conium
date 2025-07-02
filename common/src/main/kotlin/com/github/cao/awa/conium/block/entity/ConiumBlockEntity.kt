package com.github.cao.awa.conium.block.entity

import com.github.cao.awa.conium.block.entity.setting.ConiumBlockEntitySettings
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.component.Component
import net.minecraft.component.ComponentMap
import net.minecraft.component.ComponentType
import net.minecraft.component.MergedComponentMap
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.listener.ClientPlayPacketListener
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.storage.ReadView
import net.minecraft.storage.WriteView
import net.minecraft.util.math.BlockPos

class ConiumBlockEntity(
    private val setting: ConiumBlockEntitySettings,
    pos: BlockPos,
    state: BlockState
) : BlockEntity(setting.type, pos, state) {
    private val data: RegistrableNbt = RegistrableNbt(
        // Setting allows keys.
        this.setting.registeredData,
        // Use mark dirty to listens data updates.
        ::markDirty
    ).also { nbt: RegistrableNbt ->
        // Setting default data.
        for ((key, value) in this.setting.defaultData) {
            nbt[key] = value
        }
    }

    /**
     * Write the block entity data to NBT compound using registrable-NBT.
     *
     * @see WriteView
     * @see RegistrableNbt
     * @see RegistrableNbt.writeData
     *
     * @param writeView the NBT compound that will be writing to
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun writeData(writeView: WriteView) = this.data.writeData(writeView)

    /**
     * Read the block entity data from NBT compound using registrable-NBT.
     *
     * @see ReadView
     * @see RegistrableNbt
     * @see RegistrableNbt.readData
     *
     * @param readView the 'read view' that should be reading from
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
//    override fun readNbt(nbt: NbtCompound, registries: RegistryWrapper.WrapperLookup) = this.data.readFrom(nbt, registries)
    override fun readData(readView: ReadView) = this.data.readData(readView)

    /**
     * To get a non-null data using key name, should ensure it already registered.
     *
     * @see NbtCompound
     * @see RegistrableNbt
     *
     * @param key the data name
     *
     * @return the data value
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
     * @param key the data name
     * @param value the data value
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    operator fun set(key: String, value: Any) {
        this.data[key] = value
    }

    /**
     * To set the component value using the component key, should ensure the input is not a null value.
     *
     * @see NbtCompound
     * @see Component
     * @see ComponentType
     * @see ComponentMap
     * @see MergedComponentMap
     *
     * @param key the component key
     * @param value the component value
     *
     * @author 草二号机
     *
     * @since 1.0.0
     */
    operator fun <T> set(key: ComponentType<T>, value: T) {
        (this.components as? MergedComponentMap)?.let { components ->
            components[key] = value
        }
    }

    /**
     * Create an update packet for update entity to the client.
     *
     * @see BlockEntity.toUpdatePacket
     * @see BlockEntityUpdateS2CPacket
     * @see ClientPlayPacketListener.onBlockEntityUpdate
     *
     * @return the update packet
     *
     * @author 草二号机
     *
     * @since 1.0.0
     */
    override fun toUpdatePacket(): BlockEntityUpdateS2CPacket = BlockEntityUpdateS2CPacket.create(this)
}
