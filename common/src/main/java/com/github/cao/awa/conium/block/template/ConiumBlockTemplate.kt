package com.github.cao.awa.conium.block.template

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.trigger.ListTriggerable
import com.github.cao.awa.conium.template.ConiumTemplate
import net.minecraft.block.AbstractBlock

/**
 * The abstract conium block template, constraint something methods used to setting and build block.
 *
 * @see ConiumTemplate
 * @see ConiumBlock
 * @see ConiumBlockSettings
 * @see AbstractBlock.Settings
 * @see ConiumEvent
 * @see ListTriggerable
 *
 * @param isClient mark this template is only loads on client side
 * @param name the template name
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
abstract class ConiumBlockTemplate(
    isClient: Boolean = false,
    name: String
) : ConiumTemplate<ConiumBlock, ConiumBlockSettings>(isClient, name) {
    /**
     * Do events attach and attachable data setup at here.
     *
     * @see ConiumBlock
     * @see ConiumEvent
     * @see ConiumEvent.subscribe
     * @see ListTriggerable.subscribe
     *
     * @param target the conium block
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun attach(target: ConiumBlock) {
        // Do nothing.
    }

    /**
     * Do final steps at here.
     *
     * @see ConiumBlock
     *
     * @param target the conium block
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun complete(target: ConiumBlock) {
        // Do nothing.
    }

    /**
     * Prepare and setting the block settings.
     *
     * @see ConiumBlock
     * @see ConiumBlockSettings
     * @see AbstractBlock.Settings
     *
     * @param target the conium block settings
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    override fun prepare(target: ConiumBlockSettings) {
        // Setting vanilla block settings.
        settings(target.vanillaSettings)
        // Setting conium block settings.
        settings(target)
    }

    /**
     * Setting the vanilla block, do not call this method directly, use the method 'prepare'.
     *
     * @see ConiumBlock
     * @see AbstractBlock.Settings
     *
     * @param settings the vanilla block settings
     *
     * @author 草二号机
     *
     * @since 1.0.0
     */
    open fun settings(settings: AbstractBlock.Settings) {
        // Do nothing.
    }

    /**
     * Setting the conium block, do not call this method directly, use the method 'prepare'.
     *
     * @see ConiumBlock
     * @see ConiumBlockSettings
     *
     * @param settings the conium block settings
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    open fun settings(settings: ConiumBlockSettings) {
        // Do nothing.
    }
}
