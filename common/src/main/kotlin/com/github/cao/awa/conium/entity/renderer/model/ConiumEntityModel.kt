package com.github.cao.awa.conium.entity.renderer.model

import com.github.cao.awa.conium.entity.renderer.state.ConiumEntityRenderState
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.model.*
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.client.render.entity.model.EntityModel

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
        fun create(context: Context, json: JsonObject): ConiumEntityModel {
            val modelData = ModelData()

            // A map used to storage parts, a part can be the parent of other parts.
            val modelParts: HashMap<String, ModelPartData> = CollectionFactor.hashMap<String, ModelPartData>().also {
                it["root"] = modelData.root
            }

            // Create the conium model.
            return ConiumEntityModel(createTextureModelData(modelData, modelParts, context, json).createModel())
        }

        /**
         * Create the entity texture model with conium schema.
         *
         * @author cao_awa
         * @author Ryan 100c
         *
         * @since 1.0.0
         */
        fun createTextureModelData(
            modelData: ModelData,
            modelParts: MutableMap<String, ModelPartData>,
            context: Context,
            parts: JsonArray,
            textureWidth: Int,
            textureHeight: Int
        ): TexturedModelData {
            // Create the parts.
            createParts(modelParts, context, parts)

            // Make texture model data.
            return TexturedModelData.of(modelData, textureWidth, textureHeight)
        }

        /**
         * Create the entity texture model with conium schema.
         *
         * @author cao_awa
         * @author Ryan 100c
         *
         * @since 1.0.0
         */
        fun createTextureModelData(modelData: ModelData, modelParts: MutableMap<String, ModelPartData>, context: Context, json: JsonObject): TexturedModelData {
            // Texture data, the texture path is handled in the entity model template.
            val texture: JsonObject = json["texture"].asJsonObject

            // Create model.
            return createTextureModelData(
                modelData,
                modelParts,
                context,
                json["parts"].asJsonArray,
                texture["width"].asInt,
                texture["height"].asInt
            )
        }

        /**
         * Create the entity texture model with bedrock schema.
         *
         * @author cao_awa
         * @author Ryan 100c
         *
         * @since 1.0.0
         */
        fun createBedrockTextureModelData(modelData: ModelData, modelParts: MutableMap<String, ModelPartData>, context: Context, geometries: JsonArray): TexturedModelData {
            // Only allow one of geometry to creating.
            val geometry: JsonObject = geometries[0].asJsonObject

            // Bedrock description.
            val description: JsonObject = geometry["description"].asJsonObject

            // Create model.
            return createTextureModelData(
                modelData,
                modelParts,
                context,
                geometry["bones"].asJsonArray,
                description["texture_width"].asInt,
                description["texture_height"].asInt
            )
        }

        /**
         * Create the entity texture model parts.
         *
         * @author cao_awa
         * @author Ryan 100c
         *
         * @since 1.0.0
         */
        fun createParts(modelParts: MutableMap<String, ModelPartData>, context: Context, parts: JsonArray) {
            parts.map(JsonElement::getAsJsonObject).forEach { part: JsonObject ->
                // Setting the name of this part, used to storage and then can be the parent part of other parts.
                val name: String = part["name"].asString
                // Setting the parent part of this part.
                val parent: String = part["parent"]?.asString ?: "root"
                // Setting the part transform pivot.
                val pivot: JsonArray = part["pivot"].asJsonArray
                // Setting the part is mirrored or default is not mirrored.
                val mirror: Boolean = part["mirror"]?.asBoolean ?: false

                // Transform data.
                val transform: ModelTransform = pivot.let {
                    ModelTransform.rotation(
                        it[0].asFloat, it[1].asFloat, it[2].asFloat,
                    )
                }

                // Model part data.
                ModelPartBuilder.create().mirrored(mirror).let { partBuilder: ModelPartBuilder ->
                    part["cubes"].asJsonArray.map(JsonElement::getAsJsonObject).forEach { cube: JsonObject ->
                        val uv = cube["uv"].asJsonArray
                        val origin = cube["origin"].asJsonArray
                        val size = cube["size"].asJsonArray

                        partBuilder.uv(
                            uv[0].asInt, uv[1].asInt
                        ).cuboid(
                            origin[0].asFloat, origin[1].asFloat, origin[2].asFloat,
                            size[0].asFloat, size[1].asFloat, size[2].asFloat,
                        )
                    }

                    // Create child data and put it back to model parts.
                    // That may be the parent part of other child model part.
                    modelParts[parent]!!.addChild(
                        name,
                        partBuilder,
                        transform
                    )
                }
            }
        }
    }
}
