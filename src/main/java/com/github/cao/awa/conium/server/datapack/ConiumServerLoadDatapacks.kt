package com.github.cao.awa.conium.server.datapack

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.util.Identifier

class ConiumServerLoadDatapacks {
    val datapacks: MutableMap<Identifier, ConiumContentDatapack> = CollectionFactor.hashMap()
}
