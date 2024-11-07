package com.github.cao.awa.conium.kotlin.extent.json

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

val JsonElement.jsonObject: JsonObject? get() = this as? JsonObject

fun <R> JsonElement.ifJsonObject(action: (JsonObject) -> R): R? {
    return ifJsonObject(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.ifJsonObject(action: (JsonObject) -> R, elseAction: (JsonElement) -> R): R? {
    return this.jsonObject?.let {
        action(it)
    } ?: elseAction(this)
}

fun <R> JsonElement.createIfJsonObject(action: () -> R, elseAction: (JsonElement) -> R): R? {
    return this.jsonObject?.let {
        action()
    } ?: elseAction(this)
}

val JsonElement.jsonArray: JsonArray? get() = this as? JsonArray

fun <R> JsonElement.ifJsonArray(action: (JsonArray) -> R): R? {
    return ifJsonArray(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.ifJsonArray(action: (JsonArray) -> R, elseAction: (JsonElement) -> R): R {
    return this.jsonArray?.let {
        action(it)
    } ?: elseAction(this)
}

fun <R> JsonElement.ifJsonArray(action: () -> R, elseAction: (JsonElement) -> R): R {
    return this.jsonArray?.let {
        action()
    } ?: elseAction(this)
}

val JsonElement.float: Float? get() = if (this.isJsonPrimitive) this.asFloat else null

fun <R> JsonElement.ifFloat(action: (Float) -> R): R? {
    return ifFloat(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.ifFloat(action: (Float) -> R, elseAction: (JsonElement) -> R): R? {
    return this.float?.let {
        action(it)
    } ?: elseAction(this)
}

fun <R> JsonElement.ifFloat(action: () -> R, elseAction: (JsonElement) -> R): R? {
    return this.float?.let {
        action()
    } ?: elseAction(this)
}

val JsonElement.string: String? get() = if (this.isJsonPrimitive) this.asString else null

fun <R> JsonElement.ifString(action: (String) -> R): R? {
    return ifString(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.ifString(action: (String) -> R, elseAction: (JsonElement) -> R): R? {
    return this.string?.let {
        action(it)
    } ?: elseAction(this)
}

fun <R> JsonElement.ifString(action: () -> R, elseAction: (JsonElement) -> R): R? {
    return this.string?.let {
        action()
    } ?: elseAction(this)
}

val JsonElement.boolean: Boolean? get() = if (this.isJsonPrimitive) this.asBoolean else null

fun <R> JsonElement.ifBoolean(action: (Boolean) -> R): R? {
    return ifBoolean(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.ifBoolean(action: (Boolean) -> R, elseAction: (JsonElement) -> R): R? {
    return this.boolean?.let {
        action(it)
    } ?: elseAction(this)
}

fun <R> JsonElement.ifBoolean(action: () -> R, elseAction: (JsonElement) -> R): R? {
    return this.boolean?.let {
        action()
    } ?: elseAction(this)
}

val JsonElement.int: Int? get() = if (this.isJsonPrimitive) this.asInt else null

fun <R> JsonElement.ifInt(action: (Int) -> R): R? {
    return ifInt(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.ifInt(action: (Int) -> R, elseAction: (JsonElement) -> R): R? {
    return this.int?.let {
        action(it)
    } ?: elseAction(this)
}

fun <R> JsonElement.ifInt(action: () -> R, elseAction: (JsonElement) -> R): R? {
    return this.int?.let {
        action()
    } ?: elseAction(this)
}

// Or.
fun <R> JsonElement.objectOrFloat(action: (JsonObject) -> R, elseAction: (Float) -> R): R? {
    return ifJsonObject(action) {
        it.ifFloat(elseAction) {
            throw IllegalStateException("This json element need be object or float but got: $this ")
        }
    }
}

fun <R> JsonElement.objectOrString(action: (JsonObject) -> R, elseAction: (String) -> R): R? {
    return ifJsonObject(action) {
        it.ifString(elseAction) {
            throw IllegalStateException("This json element need be object or string but got: $this ")
        }
    }
}

fun <R> JsonElement.objectOrBoolean(action: (JsonObject) -> R, elseAction: (Boolean) -> R): R? {
    return ifJsonObject(action) {
        it.ifBoolean(elseAction) {
            throw IllegalStateException("This json element need be object or boolean but got: $this ")
        }
    }
}

fun <R> JsonElement.objectOrInt(action: (JsonObject) -> R, elseAction: (Int) -> R): R? {
    return ifJsonObject(action) {
        it.ifInt(elseAction) {
            throw IllegalStateException("This json element need be object or boolean but got: $this ")
        }
    }
}

// Operate each element as int.
fun JsonArray.eachInt(action: (Int) -> Unit) {
    for (i in this) {
        action(i.asInt)
    }
}