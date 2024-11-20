package com.github.cao.awa.conium.block.template.collision

import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.eachInt
import com.github.cao.awa.conium.kotlin.extent.json.objectOrBoolean
import com.github.cao.awa.conium.template.ConiumTemplates.Block.COLLISION
import com.google.gson.JsonElement
import net.minecraft.block.AbstractBlock
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.shape.VoxelShapes

class ConiumBlockCollisionTemplate(
    private val noCollision: Boolean = false,
    private val px1: Int = 0,
    private val py1: Int = 0,
    private val pz1: Int = 0,
    private val px2: Int = 16,
    private val py2: Int = 16,
    private val pz2: Int = 16,
    name: String = COLLISION
) : ConiumBlockTemplate(name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBlockCollisionTemplate = element.objectOrBoolean(
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
            //    - size x, size y, size z
            return IntArray(6).also { array ->
                var index = 0
                // Create cubed from callback.
                cube {
                    // Push to array.
                    array[index++] = it
                }
            }.let {
                // Create the block collision.
                ConiumBlockCollisionTemplate(
                    false,
                    // Origins.
                    it[0],
                    it[1],
                    it[2],
                    // Sizes.
                    it[3],
                    it[4],
                    it[5],
                    name
                )
            }
        }
    }

    override fun settings(settings: AbstractBlock.Settings) {
        if (this.noCollision) {
            settings.noCollision()
        }
    }

    override fun settings(settings: ConiumBlockSettings) {
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
