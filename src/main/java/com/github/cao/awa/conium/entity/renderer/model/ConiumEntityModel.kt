package com.github.cao.awa.conium.entity.renderer.model

import com.github.cao.awa.conium.entity.renderer.state.ConiumEntityRenderState
import com.github.cao.awa.conium.kotlin.extent.json.jsonArray
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
            try {
                val modelData = ModelData()
                val root = modelData.root

                val modelParts = CollectionFactor.hashMap<String, ModelPartData>()

                modelParts["root"] = root

                json["parts"]!!.jsonArray!!.forEach { part ->
                    part as JsonObject

                    val name = part["name"]!!.asString
                    val parent = part["parent"]?.asString ?: "root"
                    val pivot = part["pivot"]!!.asJsonArray
                    val mirror = part["mirror"]?.asBoolean ?: false

                    val transform = pivot.let {
                        ModelTransform.pivot(
                            it[0].asFloat,
                            it[1].asFloat,
                            it[2].asFloat,
                        )
                    }

                    val partBuilder = ModelPartBuilder.create().mirrored(mirror)

                    part.let { model ->
                        val cubes = model["cubes"]!!.asJsonArray

                        cubes.forEach { cube ->
                            cube as JsonObject

                            val uv = cube["uv"]!!.asJsonArray
                            val origin = cube["origin"]!!.asJsonArray
                            val size = cube["size"]!!.asJsonArray

                            partBuilder.uv(
                                uv[0].asInt,
                                uv[1].asInt
                            ).cuboid(
                                origin[0].asFloat,
                                origin[1].asFloat,
                                origin[2].asFloat,
                                size[0].asFloat,
                                size[1].asFloat,
                                size[2].asFloat,
                            )
                        }
                    }

                    modelParts[name] = modelParts[parent]!!.addChild(
                        name,
                        partBuilder,
                        transform
                    )
                }

                return TexturedModelData.of(modelData, 32, 32)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }
}
