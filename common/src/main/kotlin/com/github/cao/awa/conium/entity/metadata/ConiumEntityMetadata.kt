package com.github.cao.awa.conium.entity.metadata

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import net.minecraft.entity.EntityType

class ConiumEntityMetadata(
    val type: EntityType<ConiumEntity>,
    val settings: ConiumEntitySettings
)