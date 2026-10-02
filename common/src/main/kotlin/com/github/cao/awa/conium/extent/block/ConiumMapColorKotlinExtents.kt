package com.github.cao.awa.conium.kotlin.extent.block

import com.github.cao.awa.conium.kotlin.extent.innate.int
import net.minecraft.world.level.material.MapColor

fun parseAndFindColor(code: String): MapColor = findColor(parseColor(code))

// TODO Not completed because map color are not argb style.
fun findColor(code: Int): MapColor {
    var last: MapColor = MapColor.NONE
    val colors = ArrayList<MapColor>()
    // Collect all colors.
    for (i in 0..61) {
        colors.add(MapColor.byId(i))
    }

    // Sort by value and get nearly preset color.
    for (color in colors.toSortedSet { c1, c2 -> c1.col.compareTo(c2.col) }) {
        // Don't let clear join the selects.
        if (color == MapColor.NONE && code != 0) {
            continue
        }
        // If in range of there two colors.
        if (code in last.col..color.col) {
            // Find the color with the smaller difference between current color and last color.
            return if (code - last.col > color.col - code) {
                // Which current color is closer.
                color
            } else {
                // Which last color is closer.
                last
            }
        }

        last = color
    }

    // If not found, the last one color is result.
    return last
}

fun parseColor(string: String): Int = string.substring(1).toLong(16).int
