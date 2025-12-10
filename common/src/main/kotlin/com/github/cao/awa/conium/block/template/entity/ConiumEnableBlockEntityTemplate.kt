package com.github.cao.awa.conium.block.template.entity

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.blockentity.ConiumBlockEntity
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.block.template.data.ConiumBlockDataTemplate
import com.github.cao.awa.conium.block.template.preset.ConiumBlockEntityPresetsTemplate
import com.github.cao.awa.conium.template.block.conium.ConiumBlockTemplates
import com.google.gson.JsonElement
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType

/**
 * Setting a block flag make it could construct the block entity, this template is necessary where others block entity templates defined.
 *
 * The block will ignore all other block entity templates if this template doesn't loaded.
 *
 * @see ConiumBlock
 * @see ConiumBlockEntity
 * @see ConiumBlockSettings
 * @see BlockEntity
 * @see BlockEntityType
 * @see ConiumBlockDataTemplate
 * @see ConiumBlockEntityPresetsTemplate
 *
 * @author cao_aw
 * @author 草二号机
 *
 * @since 1.0.0
 */
class ConiumEnableBlockEntityTemplate(private val identifier: String): ConiumBlockTemplate(name = ConiumBlockTemplates.ENABLE_BLOCK_ENTITY) {
    companion object {
        /**
         * Create the template with identifier to setting the block entity type.
         *
         * @see ConiumBlock
         * @see ConiumBlockEntity
         * @see ConiumBlockSettings
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        @JvmStatic
        fun create(element: JsonElement): ConiumEnableBlockEntityTemplate {
            // Create the template with identifier.
            return ConiumEnableBlockEntityTemplate(element.asString)
        }
    }

    /**
     * Setting the block entity flag to allow the block entity be constructs in the future.
     *
     * @see ConiumBlock
     * @see ConiumBlockEntity
     * @see ConiumBlockSettings
     *
     * @param settings the block settings
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun settings(settings: ConiumBlockSettings) {
        // Enable the block entity flag.
        settings.enableBlockEntity = true
    }

    /**
     * Setting the block entity type when enabled block entity with the identifier.
     *
     * @see ConiumBlock
     * @see ConiumBlockEntity
     * @see BlockEntity
     * @see BlockEntityType
     *
     * @param target the block of the block entity
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    override fun complete(target: ConiumBlock) {
        // Setting the block entity type.
        target.setting.blockEntity.let { blockEntitySettings ->
            // Create the block entity type and setting it to block entity settings.
            // If you saw 'BlockEntityType.create' cannot access, please run the Gradle task 'validateAccessWidener'.
            blockEntitySettings.type = BlockEntityType.create(
                this.identifier,
                { pos, state ->
                    ConiumBlockEntity(
                        blockEntitySettings,
                        pos,
                        state
                    )
                },
                target
            )
        }
    }
}
