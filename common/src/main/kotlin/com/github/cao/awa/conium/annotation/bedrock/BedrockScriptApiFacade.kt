package com.github.cao.awa.conium.annotation.bedrock

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.TYPE,
    AnnotationTarget.FIELD,
)
annotation class BedrockScriptApiFacade(vararg val sapiType: String)
