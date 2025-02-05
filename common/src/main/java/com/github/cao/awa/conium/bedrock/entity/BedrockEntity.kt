package com.github.cao.awa.conium.bedrock.entity

import net.minecraft.entity.Entity

open class BedrockEntity(private val delegate: Entity) {

}

fun Entity.toBedrock(): BedrockEntity = BedrockEntity(this)