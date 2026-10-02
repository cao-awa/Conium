package com.github.cao.awa.conium.blockentity

import com.github.cao.awa.conium.blockentity.setting.ConiumBlockEntitySettings
import com.github.cao.awa.conium.nbt.data.RegistrableNbt
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.core.component.DataComponentMap
import net.minecraft.core.component.DataComponentType
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput
import net.minecraft.core.BlockPos

class ConiumBlockEntity(
    private val setting: ConiumBlockEntitySettings,
    pos: BlockPos,
    state: BlockState
) : BlockEntity(setting.type!!, pos, state) {
    private val data: RegistrableNbt = RegistrableNbt(
        // Setting allows keys.
        this.setting.registeredData,
        // Use setChanged to listens data updates.
        ::setChanged
    ).also { nbt: RegistrableNbt ->
        // Setting default data.
        for ((key, value) in this.setting.defaultData) {
            nbt[key] = value
        }
    }

    override fun saveAdditional(writeView: ValueOutput) {
        super.saveAdditional(writeView)
        this.data.writeData(writeView)
    }

    override fun loadAdditional(readView: ValueInput) {
        super.loadAdditional(readView)
        this.data.readData(readView)
    }

    operator fun <X> get(key: String): X = this.data[key]

    operator fun set(key: String, value: Any) {
        this.data[key] = value
    }

    operator fun <T : Any> set(key: DataComponentType<T>, value: T) {
        val current = components()
        val builder = DataComponentMap.builder().addAll(current)
        builder.set(key, value)
        setComponents(builder.build())
    }

    override fun getUpdatePacket(): Packet<ClientGamePacketListener>? = ClientboundBlockEntityDataPacket.create(this)
}
