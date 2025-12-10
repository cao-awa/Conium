package com.github.cao.awa.conium.block.template.bedrock.collision

import com.github.cao.awa.conium.block.template.collision.ConiumBlockCollisionTemplate
import com.github.cao.awa.conium.block.template.collision.ConiumBlockCollisionTemplate.Companion.createWithCubed
import com.github.cao.awa.conium.kotlin.extent.json.eachInt
import com.github.cao.awa.conium.kotlin.extent.json.objectOrBoolean
import com.github.cao.awa.conium.template.block.bedrock.BedrockBlockComponents.COLLISION_BOX
import com.google.gson.JsonElement

/**
 * The block collision box template for bedrock schema.
 *
 * For details, see [ConiumBlockCollisionTemplate].
 *
 * @see ConiumBlockCollisionTemplate
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
object BedrockBlockCollisionBoxComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumBlockCollisionTemplate = element.objectOrBoolean(
        {
            // Create the custom cubed block.
            createWithCubed(COLLISION_BOX) { cubed ->
                // Push all int to cubed array.
                it["origin"].asJsonArray.let {
                    // Bedrock base offset of x and z axis is '-8', make it be the conium offset '0'.
                    // But y axis is zero that same to the conium offset.
                    cubed(it[0].asInt + 8)
                    cubed(it[1].asInt)
                    cubed(it[2].asInt + 8)
                }
                it["size"].asJsonArray.eachInt(cubed)
            }
        },
        // Create the full cubed block when origin and size not specified.
        { ConiumBlockCollisionTemplate(
            it,
            0, 0, 0,
            16, 16, 16,
            COLLISION_BOX
        ) }
    )!!
}
