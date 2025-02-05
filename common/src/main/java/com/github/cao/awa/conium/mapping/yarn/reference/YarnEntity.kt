@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import net.minecraft.entity.Entity
import net.minecraft.server.MinecraftServer
import net.minecraft.world.World

/**
 * See the mapping [Entity](https://mappings.dev/1.21.4/net/minecraft/world/entity/Entity.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val Entity.world: World by Entity::world
val Entity.server: MinecraftServer? by Entity::server
val Entity.isAlive: Boolean
    get() = this.isAlive