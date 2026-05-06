package com.github.cao.awa.conium.resource

import java.io.File
import java.io.InputStream

object ResourceLoader {
    fun get(resource: String): InputStream {
        return ResourceLoader::class.java.getClassLoader().getResourceAsStream(resource)!!
    }

    fun asFile(resource: String): File {
        return File(ResourceLoader::class.java.getResource(resource)!!.toString())
    }
}