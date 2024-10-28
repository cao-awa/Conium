package com.github.cao.awa.conium.script.kts

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
    fileExtension = "kts"
)
abstract class ConiumScript

fun clearDuplicateImports(script: String): String {
    val imports = CollectionFactor.hashSet<String>()
    val scriptLines = CollectionFactor.linkedList<String>()
    script.lines().forEach {
        if (it.startsWith("import ")) {
            imports.add(it)
        } else if (it.isNotBlank()){
            scriptLines.add(it)
        }
    }

    val builder = StringBuilder()

    for (import in imports) {
        builder.append(import)
        builder.append("\n")
    }

    for (scriptLine in scriptLines) {
        builder.append(scriptLine)
        builder.append("\n")
    }

    return builder.toString()
}