package com.github.cao.awa.conium.entity.renderer.model

import com.github.cao.awa.conium.entity.renderer.state.ConiumEntityRenderState
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonObject
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.model.*
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.util.Identifier
import java.util.function.Function

@Environment(EnvType.CLIENT)
class ConiumEntityModel(root: ModelPart) : EntityModel<ConiumEntityRenderState>(root, RenderLayer::getEntityCutout) {
    companion object {
        val emptyModel = ConiumEntityModel(
            ModelPart(
                CollectionFactor.arrayList(),
                CollectionFactor.hashMap()
            )
        )

        @JvmStatic
        fun create(context: EntityRendererFactory.Context, json: JsonObject): ConiumEntityModel {
            return ConiumEntityModel(createTextureModelData(context, json).createModel())
        }

        fun createTextureModelData(context: EntityRendererFactory.Context, json: JsonObject): TexturedModelData {
            // TODO here is model deserializing.
            val modelData = ModelData()
            val modelPartData = modelData.root
            val modelPartData2 = modelPartData.addChild(
                EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 0).cuboid(-1.5f, 0.0f, -1.0f, 3.0f, 5.0f, 2.0f), ModelTransform.pivot(0.0f, 17.0f, 0.0f)
            )
            val modelPartData3 = modelPartData.addChild(
                EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 7).cuboid(-2.0f, -3.0f, -1.0f, 4.0f, 3.0f, 2.0f), ModelTransform.pivot(0.0f, 17.0f, 0.0f)
            )
            modelPartData3.addChild(
                EntityModelPartNames.RIGHT_EAR, ModelPartBuilder.create().uv(1, 15).cuboid(-2.5f, -4.0f, 0.0f, 3.0f, 5.0f, 0.0f), ModelTransform.pivot(-1.5f, -2.0f, 0.0f)
            )
            modelPartData3.addChild(
                EntityModelPartNames.LEFT_EAR, ModelPartBuilder.create().uv(8, 15).cuboid(-0.1f, -3.0f, 0.0f, 3.0f, 5.0f, 0.0f), ModelTransform.pivot(1.1f, -3.0f, 0.0f)
            )
            val modelPartData4 = modelPartData2.addChild(
                EntityModelPartNames.RIGHT_WING, ModelPartBuilder.create().uv(12, 0).cuboid(-2.0f, -2.0f, 0.0f, 2.0f, 7.0f, 0.0f), ModelTransform.pivot(-1.5f, 0.0f, 0.0f)
            )
            modelPartData4.addChild(
                EntityModelPartNames.RIGHT_WING_TIP,
                ModelPartBuilder.create().uv(16, 0).cuboid(-6.0f, -2.0f, 0.0f, 6.0f, 8.0f, 0.0f),
                ModelTransform.pivot(-2.0f, 0.0f, 0.0f)
            )
            val modelPartData5 = modelPartData2.addChild(
                EntityModelPartNames.LEFT_WING, ModelPartBuilder.create().uv(12, 7).cuboid(0.0f, -2.0f, 0.0f, 2.0f, 7.0f, 0.0f), ModelTransform.pivot(1.5f, 0.0f, 0.0f)
            )
            modelPartData5.addChild(
                EntityModelPartNames.LEFT_WING_TIP, ModelPartBuilder.create().uv(16, 8).cuboid(0.0f, -2.0f, 0.0f, 6.0f, 8.0f, 0.0f), ModelTransform.pivot(2.0f, 0.0f, 0.0f)
            )
            modelPartData2.addChild(
                EntityModelPartNames.FEET, ModelPartBuilder.create().uv(16, 16).cuboid(-1.5f, 0.0f, 0.0f, 3.0f, 2.0f, 0.0f), ModelTransform.pivot(0.0f, 5.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 32, 32)
        }
    }
}
