package com.github.cao.awa.conium.server.datapack

import net.minecraft.resources.Identifier

class ConiumContentDatapack(val identifier: Identifier) {
    val contents: MutableMap<Identifier, String> = HashMap()
}
