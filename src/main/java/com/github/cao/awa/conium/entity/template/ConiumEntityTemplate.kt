package com.github.cao.awa.conium.entity.template

import com.github.cao.awa.conium.entity.ConiumMobEntity
import com.github.cao.awa.conium.template.ConiumTemplate

abstract class ConiumEntityTemplate(name: String) : ConiumTemplate<ConiumMobEntity>(name) {
    override fun attach(target: ConiumMobEntity) {
        // Do nothing.
    }

    override fun complete(target: ConiumMobEntity) {
        // Do nothing.
    }
}
