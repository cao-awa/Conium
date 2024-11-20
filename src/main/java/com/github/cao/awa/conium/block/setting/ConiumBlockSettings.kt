package com.github.cao.awa.conium.block.setting

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.setting.ConiumSettings
import net.minecraft.block.AbstractBlock
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.RaycastContext

object ConiumBlockSettingsValue {
    val color: Int = 0
    val outlineShape: VoxelShape = VoxelShapes.fullCube()
    val landPathThrough: Boolean = false
    val waterPathThrough: Boolean = false
    val airPathThrough: Boolean = false
}

class ConiumBlockSettings(val vanillaSettings: Settings) : ConiumSettings<ConiumBlockSettings>() {
    companion object {
        @JvmStatic
        fun create(templates: MutableList<ConiumBlockTemplate>, settings: Settings): ConiumBlockSettings {
            return ConiumBlockSettings(settings).also {
                templates.forEach { template ->
                    template.prepare(it)
                }
            }
        }
    }

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
        get() = this._color ?: ConiumBlockSettingsValue.color
        set(value) {
            this._color = value

        }

    // The delegate.
    private var _color: Int? = null

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
        get() = this._landPathThrough ?: ConiumBlockSettingsValue.landPathThrough
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
        get() = this._waterPathThrough ?: ConiumBlockSettingsValue.waterPathThrough
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
        get() = this._airPathThrough ?: ConiumBlockSettingsValue.airPathThrough
        set(value) {
            this._airPathThrough = value
        }

    // The delegate.
    private var _airPathThrough: Boolean? = null

    override fun migrateTo(settings: ConiumBlockSettings): ConiumBlockSettings {
        return settings.also {
            // Apply settings(only configured, no default).
        }
    }

    override fun newInstance(): ConiumBlockSettings = ConiumBlockSettings(this.vanillaSettings)
}
