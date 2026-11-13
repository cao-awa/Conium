package com.github.cao.awa.conium.util

import java.io.File
import java.io.InputStream
import java.nio.charset.StandardCharsets

object IOUtil {
    fun readString(resource: InputStream): String {
        return String(resource.readBytes(), StandardCharsets.UTF_8)
    }

    fun readString(file: File): String {
        return readString(file.inputStream())
    }

    fun readLines(resource: InputStream): List<String> {
        return readString(resource).lines()
    }

    fun write(file: File, content: String) {
        file.writer().write(content)
    }
}