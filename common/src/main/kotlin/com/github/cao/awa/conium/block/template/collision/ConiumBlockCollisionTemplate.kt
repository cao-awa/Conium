package com.github.cao.awa.conium.block.template.collision

import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.block.template.bedrock.collision.ConiumBedrockBlockCollisionBoxTemplate
import com.github.cao.awa.conium.kotlin.extent.json.eachInt
import com.github.cao.awa.conium.kotlin.extent.json.objectOrBoolean
import com.github.cao.awa.conium.template.ConiumTemplates.Block.COLLISION
import com.google.gson.JsonElement
import net.minecraft.block.AbstractBlock
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.block.Block

/**
 * The base block collision box template.
 *
 * Default data of template is constructing a full cube block that has the entity collision.
 *
 * For default key, 'collision' is only for conium schema.
 *
 * @param noCollision mark this block is no entity collisions
 * @param px1 the position of origin x
 * @param py1 the position of origin y
 * @param pz1 the position of origin z
 * @param px2 the offset of x
 * @param py2 the offset of y
 * @param pz2 the offset of z
 * @param name the template key
 *
 * @see VoxelShape
 * @see Block.getOutlineShape
 * @see ConiumBedrockBlockCollisionBoxTemplate
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
open class ConiumBlockCollisionTemplate(
    private val noCollision: Boolean = false,
    // the origins and offsets value is minimum 0 to maximum 16,
    // the sum of origin and offset maximum value is also 16.
    private val px1: Int = 0,
    private val py1: Int = 0,
    private val pz1: Int = 0,
    private val px2: Int = 16,
    private val py2: Int = 16,
    private val pz2: Int = 16,
    // The key name.
    name: String = COLLISION
) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumBlockCollisionTemplate = element.objectOrBoolean(
            {
                // Create the custom cubed block.
                createWithCubed { cubed ->
                    // Push all int to cubed array.
                    it["origin"].asJsonArray.eachInt(cubed)
                    it["size"].asJsonArray.eachInt(cubed)
                }
            },
            // Create the full cubed block when origin and size not specified.
            ::ConiumBlockCollisionTemplate
        )!!

        @JvmStatic
        fun createWithCubed(name: String = COLLISION, cube: ((Int) -> Unit) -> Unit): ConiumBlockCollisionTemplate {
            // Only 6 cube data:
            //    - origin x, origin y, origin z
            //    - offset x, offset y, offset z
            // The 'origin' is the origin position of block cube, usually is 0 if full cube.
            // The 'offset' is the size offset of block cube, usually is 16 if full cube.
            return IntArray(6).also { data: IntArray ->
                var index = 0
                // Create cubed data from callback.
                cube {
                    // Push data to the array.
                    data[index++] = it
                }
            }.let { data: IntArray ->
                // Create the block collision template.
                ConiumBlockCollisionTemplate(
                    noCollision = false,
                    // Origins.
                    px1 = data[0],
                    py1 = data[1],
                    pz1 = data[2],
                    // Offsets.
                    px2 = data[3],
                    py2 = data[4],
                    pz2 = data[5],
                    name = name
                )
            }
        }
    }

    override fun settings(settings: AbstractBlock.Settings) {
        if (this.noCollision) {
            // Setting block no collision.
            settings.noCollision()
        }
    }

    override fun settings(settings: ConiumBlockSettings) {
        // Setting block outline shape.
        settings.outlineShape = VoxelShapes.cuboid(
            this.px1 / 16.0,
            this.py1 / 16.0,
            this.pz1 / 16.0,
            this.px2 / 16.0,
            this.py2 / 16.0,
            this.pz2 / 16.0,
        )
    }
}
