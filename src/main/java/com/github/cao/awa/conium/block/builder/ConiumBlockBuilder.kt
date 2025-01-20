package com.github.cao.awa.conium.block.builder

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.ConiumBuilderWithTemplates
import net.minecraft.util.Identifier

abstract class ConiumBlockBuilder(val identifier: Identifier) : ConiumBuilderWithTemplates<
        ConiumBlockBuilder,
        ConiumBlockSettings,
        ConiumBlock,
        ConiumBlockTemplate>(
    ConiumBlock::create
)