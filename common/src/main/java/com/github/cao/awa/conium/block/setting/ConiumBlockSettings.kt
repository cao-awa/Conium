package com.github.cao.awa.conium.block.setting

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.entity.ConiumBlockEntity
import com.github.cao.awa.conium.block.entity.setting.ConiumBlockEntitySettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.setting.ConiumSettings
import net.minecraft.block.AbstractBlock
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.RaycastContext

object ConiumBlockSettingsValue {
    const val COLOR: Int = 0
    val outlineShape: VoxelShape = VoxelShapes.fullCube()
    const val LAND_PATH_THROUGH: Boolean = false
    const val WATER_PATH_THROUGH: Boolean = false
    const val AIR_PATH_THROUGH: Boolean = false
    const val ENABLE_BLOCK_ENTITY: Boolean = false
    val NO_REDSTONE_POWER_PROVIDER: (BlockState, BlockView, BlockPos, Direction) -> Int = { state, world, pos, direction -> 0 }
    const val EMITS_REDSTONE_POWER = false;
}

abstract class ConiumAbstractBlockSettings<B : ConiumAbstractBlockSettings<B>>(val vanillaSettings: Settings) : ConiumSettings<ConiumAbstractBlockSettings<B>, B>() {
    /**
     * Setting the outline shape of block.
     *
     * Default is ``fullCube``.
     *
     * @see AbstractBlock.getOutlineShape
     * @see AbstractBlock.AbstractBlockState.getOutlineShape
     * @see RaycastContext.ShapeType.OUTLINE
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var outlineShape: VoxelShape
        get() = this._outlineShape ?: ConiumBlockSettingsValue.outlineShape
        set(value) {
            this._outlineShape = value
        }

    // The delegate.
    private var _outlineShape: VoxelShape? = null

    /**
     * Setting the land path through-able of block.
     *
     * Default is ``false``.
     *
     * @see AbstractBlock.canPathfindThrough
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var landPathThrough: Boolean
        get() = this._landPathThrough ?: ConiumBlockSettingsValue.LAND_PATH_THROUGH
        set(value) {
            this._landPathThrough = value
        }

    // The delegate.
    private var _landPathThrough: Boolean? = null

    /**
     * Setting the water path through-able of block.
     *
     * Default is ``false``.
     *
     * @see AbstractBlock.canPathfindThrough
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var waterPathThrough: Boolean
        get() = this._waterPathThrough ?: ConiumBlockSettingsValue.WATER_PATH_THROUGH
        set(value) {
            this._waterPathThrough = value
        }

    // The delegate.
    private var _waterPathThrough: Boolean? = null

    /**
     * Setting the air path through-able of block.
     *
     * Default is ``false``.
     *
     * @see AbstractBlock.canPathfindThrough
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var airPathThrough: Boolean
        get() = this._airPathThrough ?: ConiumBlockSettingsValue.AIR_PATH_THROUGH
        set(value) {
            this._airPathThrough = value
        }

    // The delegate.
    private var _airPathThrough: Boolean? = null

    /**
     * Setting the block entity flag of the block.
     *
     * Default is ``false``.
     *
     * @see BlockEntityProvider.createBlockEntity
     * @see ConiumBlock.createBlockEntity
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var enableBlockEntity: Boolean
        get() = this._enableBlockEntity ?: ConiumBlockSettingsValue.ENABLE_BLOCK_ENTITY
        set(value) {
            this._enableBlockEntity = value
        }

    // The delegate.
    private var _enableBlockEntity: Boolean? = null

    /**
     * Setting the block entity of the block.
     *
     * Default is null.
     *
     * @see BlockEntityProvider.createBlockEntity
     * @see ConiumBlock.createBlockEntity
     * @see ConiumBlockEntity
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var blockEntity: ConiumBlockEntitySettings
        get() {
            if (this._blockEntity == null) {
                this._blockEntity = ConiumBlockEntitySettings(this)
            }
            return this._blockEntity!!
        }
        set(value) {
            this._blockEntity = value
        }

    // The delegate.
    private var _blockEntity: ConiumBlockEntitySettings? = null

    /**
     * Setting the redstone weak power provider of block.
     *
     * Default is ``() -> 0``.
     *
     * @see AbstractBlock.emitsRedstonePower
     * @see AbstractBlock.getWeakRedstonePower
     * @see AbstractBlock.getStrongRedstonePower
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var redstoneWeakPowerProvider: (BlockState, BlockView, BlockPos, Direction) -> Int
        get() = this._redstoneWeakPowerProvider ?: ConiumBlockSettingsValue.NO_REDSTONE_POWER_PROVIDER
        set(value) {
            this._redstoneWeakPowerProvider = value
        }

    // The delegate.
    private var _redstoneWeakPowerProvider: ((BlockState, BlockView, BlockPos, Direction) -> Int)? = null

    /**
     * Setting the redstone strong power provider of block.
     *
     * Default is ``() -> 0``.
     *
     * @see AbstractBlock.emitsRedstonePower
     * @see AbstractBlock.getWeakRedstonePower
     * @see AbstractBlock.getStrongRedstonePower
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var redstoneStrongPowerProvider: (BlockState, BlockView, BlockPos, Direction) -> Int
        get() = this._redstoneStrongPowerProvider ?: ConiumBlockSettingsValue.NO_REDSTONE_POWER_PROVIDER
        set(value) {
            this._redstoneStrongPowerProvider = value
        }

    // The delegate.
    private var _redstoneStrongPowerProvider: ((BlockState, BlockView, BlockPos, Direction) -> Int)? = null

    /**
     * Setting the redstone power emits-able of block.
     *
     * Default is ``false``.
     *
     * @see AbstractBlock.emitsRedstonePower
     * @see AbstractBlock.getWeakRedstonePower
     * @see AbstractBlock.getStrongRedstonePower
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var emitsRedstonePower: Boolean
        get() = this._emitsRedstonePower ?: ConiumBlockSettingsValue.EMITS_REDSTONE_POWER
        set(value) {
            this._emitsRedstonePower = value
        }

    // The delegate.
    private var _emitsRedstonePower: Boolean? = null

    override fun migrateTo(settings: B): B {
        return settings.also {
            // Apply settings (only configured, no default).
            this._outlineShape?.apply { it.outlineShape = this }
            this._landPathThrough?.apply { it.landPathThrough = this }
            this._waterPathThrough?.apply { it.waterPathThrough = this }
            this._airPathThrough?.apply { it.airPathThrough = this }
            this._enableBlockEntity?.apply { it.enableBlockEntity = this }
            this._blockEntity?.apply { it.blockEntity = this }
            this._redstoneWeakPowerProvider?.apply { it.redstoneWeakPowerProvider = this }
            this._redstoneStrongPowerProvider?.apply { it.redstoneStrongPowerProvider = this }
            this._emitsRedstonePower?.apply { it.emitsRedstonePower = this }
        }
    }
}

class ConiumClientBlockSettings(vanillaSettings: Settings) : ConiumAbstractBlockSettings<ConiumClientBlockSettings>(vanillaSettings) {
    /**
     * Setting the color code of block.
     *
     * Default is 0.
     *
     * @see BlockColorProvider.getColor
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    var color: Int
        get() = this._color ?: ConiumBlockSettingsValue.COLOR
        set(value) {
            this._color = value

        }

    // The delegate.
    private var _color: Int? = null

    override fun newInstance(): ConiumClientBlockSettings = ConiumClientBlockSettings(this.vanillaSettings)

    override fun migrateTo(settings: ConiumClientBlockSettings): ConiumClientBlockSettings {
        return super.migrateTo(settings.also {
            // Apply settings(only configured, no default).
            this._color?.apply { it.color = this }
        })
    }
}

class ConiumBlockSettings(vanillaSettings: Settings) : ConiumAbstractBlockSettings<ConiumBlockSettings>(vanillaSettings) {
    companion object {
        @JvmStatic
        fun create(templates: Collection<ConiumBlockTemplate>, settings: Settings): ConiumBlockSettings {
            return ConiumBlockSettings(settings).also {
                templates.forEach { template ->
                    template.prepare(it)
                }
            }
        }
    }

    override fun newInstance(): ConiumBlockSettings = ConiumBlockSettings(this.vanillaSettings)
}
