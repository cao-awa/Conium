package com.github.cao.awa.conium.server.datapack

import net.minecraft.util.Identifier

class ConiumContentDatapack(val identifier: Identifier) {
    val contents: MutableMap<Identifier, String> = HashMap()
}
