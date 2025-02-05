@file:Suppress("unused")
@file:Remap

package com.github.cao.awa.conium.mapping.yarn.reference

import com.github.cao.awa.conium.annotation.mapping.Remap
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld

/**
 * See the mapping [ServerPlayerEntity](https://mappings.dev/1.21.4/net/minecraft/server/level/ServerPlayer.html).
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */

val ServerPlayerEntity.serverWorld: ServerWorld
    get() = this.serverWorld