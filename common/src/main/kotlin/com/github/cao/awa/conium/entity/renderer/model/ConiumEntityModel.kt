package com.github.cao.awa.conium.entity.renderer.model

import com.github.cao.awa.conium.entity.renderer.state.ConiumEntityRenderState
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.model.geom.builders.PartDefinition
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.rendertype.RenderTypes
import java.util.HashMap

@Environment(EnvType.CLIENT)
class ConiumEntityModel(root: ModelPart) : EntityModel<ConiumEntityRenderState>(root, { RenderTypes.entityCutout(it) }) {
    companion object {
        val emptyModel = ConiumEntityModel(
            ModelPart(
                emptyList(),
                emptyMap()
            )
        )

        @JvmStatic
        fun create(context: Context, json: JsonObject): ConiumEntityModel {
            val modelData = MeshDefinition()

            // A map used to storage parts, a part can be the parent of other parts.
            val modelParts: HashMap<String, PartDefinition> = HashMap<String, PartDefinition>().also {
                it["root"] = modelData.root
            }

            // Create the conium model.
            return ConiumEntityModel(createTextureModelData(modelData, modelParts, context, json).bakeRoot())
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
            modelData: MeshDefinition,
            modelParts: MutableMap<String, PartDefinition>,
            context: Context,
            parts: JsonArray,
            textureWidth: Int,
            textureHeight: Int
        ): LayerDefinition {
            // Create the parts.
            createParts(modelParts, context, parts)

            // Make texture model data.
            return LayerDefinition.create(modelData, textureWidth, textureHeight)
        }

        /**
         * Create the entity texture model with conium schema.
         *
         * @author cao_awa
         * @author Ryan 100c
         *
         * @since 1.0.0
         */
        fun createTextureModelData(modelData: MeshDefinition, modelParts: MutableMap<String, PartDefinition>, context: Context, json: JsonObject): LayerDefinition {
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
        fun createBedrockTextureModelData(modelData: MeshDefinition, modelParts: MutableMap<String, PartDefinition>, context: Context, geometries: JsonArray): LayerDefinition {
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
        fun createParts(modelParts: MutableMap<String, PartDefinition>, context: Context, parts: JsonArray) {
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
                val transform: PartPose = pivot.let {
                    PartPose.rotation(
                        it[0].asFloat, it[1].asFloat, it[2].asFloat,
                    )
                }

                // Model part data.
                CubeListBuilder.create().mirror(mirror).let { partBuilder: CubeListBuilder ->
                    part["cubes"].asJsonArray.map(JsonElement::getAsJsonObject).forEach { cube: JsonObject ->
                        val uv = cube["uv"].asJsonArray
                        val origin = cube["origin"].asJsonArray
                        val size = cube["size"].asJsonArray

                        partBuilder.texOffs(
                            uv[0].asInt, uv[1].asInt
                        ).addBox(
                            origin[0].asFloat, origin[1].asFloat, origin[2].asFloat,
                            size[0].asFloat, size[1].asFloat, size[2].asFloat,
                        )
                    }

                    // Create child data and put it back to model parts.
                    // That may be the parent part of other child model part.
                    modelParts[parent]!!.addOrReplaceChild(
                        name,
                        partBuilder,
                        transform
                    )
                }
            }
        }
    }
}
