package com.github.cao.awa.conium.annotation.bedrock

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.TYPE,
    AnnotationTarget.FIELD,
)
annotation class BedrockScriptApiFacade(vararg val sapiType: String)
