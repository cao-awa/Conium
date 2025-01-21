package com.github.cao.awa.conium.server.datapack

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.util.Identifier

class ConiumContentDatapack(val identifier: Identifier) {
    val contents: MutableMap<Identifier, String> = CollectionFactor.linkedHashMap()
}
