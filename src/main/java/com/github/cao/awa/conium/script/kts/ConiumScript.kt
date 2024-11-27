package com.github.cao.awa.conium.script.kts

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(fileExtension = "kts")
abstract class ConiumScript

fun clearDuplicateImports(script: String): String {
    val imports: MutableSet<String> = CollectionFactor.hashSet()
    val scriptLines: MutableList<String> = CollectionFactor.linkedList()
    script.lines().forEach {
        if (it.startsWith("import ")) {
            imports.add(it)
        } else if (it.isNotBlank()) {
            scriptLines.add(it)
        }
    }

    val builder: StringBuilder = StringBuilder()

    for (import: String in imports) {
        builder.append(import)
        builder.append("\n")
    }

    for (scriptLine: String in scriptLines) {
        builder.append(scriptLine)
        builder.append("\n")
    }

    return builder.toString()
}

fun filterImports(script: String): Set<String> {
    val imports: MutableSet<String> = CollectionFactor.hashSet()
    script.lines().forEach {
        if (it.startsWith("import ")) {
            imports.add(it)
        }
    }
    return imports
}