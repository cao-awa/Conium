package com.github.cao.awa.conium.entity.renderer.model

import com.github.cao.awa.conium.entity.renderer.state.ConiumEntityRenderState
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.client.model.ModelPart
import net.minecraft.client.render.entity.model.EntityModel

class ConiumEntityModel(root: ModelPart) : EntityModel<ConiumEntityRenderState>(root) {
    companion object {
        val emptyModel = ConiumEntityModel(
            ModelPart(
                CollectionFactor.arrayList(),
                CollectionFactor.hashMap()
            )
        )
    }
}
