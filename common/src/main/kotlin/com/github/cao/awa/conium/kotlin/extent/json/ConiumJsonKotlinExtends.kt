package com.github.cao.awa.conium.kotlin.extent.json

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

val JsonElement.jsonObject: JsonObject? get() = runCatching { this as JsonObject }.getOrNull()
val JsonElement.jsonArray: JsonArray? get() = runCatching { this as JsonArray }.getOrNull()
val JsonElement.string: String? get() = runCatching { this.asString }.getOrNull()
val JsonElement.int: Int? get() = runCatching { this.asInt }.getOrNull()
val JsonElement.float: Float? get() = runCatching { this.asFloat }.getOrNull()
val JsonElement.boolean: Boolean? get() = runCatching { this.asBoolean }.getOrNull()

fun <R> JsonElement.ifJsonObject(action: (JsonObject) -> R): R? {
    return ifJsonObject(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.jsonObjectOrThrow(action: (JsonObject) -> R, throwable: Throwable): R? {
    return ifJsonObject(action) {
        throw throwable
    }
}

fun <R> JsonElement.jsonObjectOrThrow(action: (JsonObject) -> R, throwable: () -> Throwable): R? {
    return ifJsonObject(action) {
        throw throwable()
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

fun <R> JsonElement.ifJsonArray(action: (JsonArray) -> R): R? {
    return ifJsonArray(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.jsonArrayOrThrow(action: (JsonArray) -> R, throwable: Throwable): R? {
    return ifJsonArray(action) {
        throw throwable
    }
}

fun <R> JsonElement.jsonArrayOrThrow(action: (JsonArray) -> R, throwable: () -> Throwable): R? {
    return ifJsonArray(action) {
        throw throwable()
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

fun <R> JsonElement.ifFloat(action: (Float) -> R): R? {
    return ifFloat(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.floatOrThrow(action: (Float) -> R, throwable: Throwable): R? {
    return ifFloat(action) {
        throw throwable
    }
}

fun <R> JsonElement.floatOrThrow(action: (Float) -> R, throwable: () -> Throwable): R? {
    return ifFloat(action) {
        throw throwable()
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

fun <R> JsonElement.ifString(action: (String) -> R): R? {
    return ifString(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.stringOrThrow(action: (String) -> R, throwable: Throwable): R? {
    return ifString(action) {
        throw throwable
    }
}

fun <R> JsonElement.stringOrThrow(action: (String) -> R, throwable: () -> Throwable): R? {
    return ifString(action) {
        throw throwable()
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

fun <R> JsonElement.ifBoolean(action: (Boolean) -> R): R? {
    return ifBoolean(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.booleanOrThrow(action: (Boolean) -> R, throwable: Throwable): R? {
    return ifBoolean(action) {
        throw throwable
    }
}

fun <R> JsonElement.booleanOrThrow(action: (Boolean) -> R, throwable: () -> Throwable): R? {
    return ifBoolean(action) {
        throw throwable()
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

fun <R> JsonElement.ifInt(action: (Int) -> R): R? {
    return ifInt(action) {
        // Do nothing.
        null
    }
}

fun <R> JsonElement.intOrThrow(action: (Int) -> R, throwable: Throwable): R? {
    return ifInt(action) {
        throw throwable
    }
}

fun <R> JsonElement.intOrThrow(action: (Int) -> R, throwable: () -> Throwable): R? {
    return ifInt(action) {
        throw throwable()
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

// JSON object or other.
fun <R> JsonElement.objectOrFloat(action: (JsonObject) -> R, elseAction: (Float) -> R): R? {
    return ifJsonObject(action) {
        it.ifFloat(elseAction) {
            throw IllegalStateException("This json element need be JSOn object or float but got: $this ")
        }
    }
}

fun <R> JsonElement.objectOrString(action: (JsonObject) -> R, elseAction: (String) -> R): R? {
    return ifJsonObject(action) {
        it.ifString(elseAction) {
            throw IllegalStateException("This json element need be JSON object or string but got: $this ")
        }
    }
}

fun <R> JsonElement.objectOrBoolean(action: (JsonObject) -> R, elseAction: (Boolean) -> R): R? {
    return ifJsonObject(action) {
        it.ifBoolean(elseAction) {
            throw IllegalStateException("This json element need be JSON object or boolean but got: $this ")
        }
    }
}

fun <R> JsonElement.objectOrInt(action: (JsonObject) -> R, elseAction: (Int) -> R): R? {
    return ifJsonObject(action) {
        it.ifInt(elseAction) {
            throw IllegalStateException("This json element need be JSON object or boolean but got: $this ")
        }
    }
}

// JSON array or other.
fun <R> JsonElement.arrayOrFloat(action: (JsonArray) -> R, elseAction: (Float) -> R): R? {
    return ifJsonArray(action) {
        it.ifFloat(elseAction) {
            throw IllegalStateException("This json element need be JSON array or float but got: $this ")
        }
    }
}

fun <R> JsonElement.arrayOrString(action: (JsonArray) -> R, elseAction: (String) -> R): R? {
    return ifJsonArray(action) {
        it.ifString(elseAction) {
            throw IllegalStateException("This json element need be JSON array or string but got: $this ")
        }
    }
}

fun <R> JsonElement.arrayOrBoolean(action: (JsonArray) -> R, elseAction: (Boolean) -> R): R? {
    return ifJsonArray(action) {
        it.ifBoolean(elseAction) {
            throw IllegalStateException("This json element need be JSON array ar or boolean but got: $this ")
        }
    }
}

fun <R> JsonElement.arrayOrInt(action: (JsonArray) -> R, elseAction: (Int) -> R): R? {
    return ifJsonArray(action) {
        it.ifInt(elseAction) {
            throw IllegalStateException("This json element need be JSON array or int but got: $this ")
        }
    }
}

// Operate each element as int.
fun JsonArray.eachInt(action: (Int) -> Unit) {
    for (i in this) {
        action(i.asInt)
    }
}

fun <R> JsonObject.mapArray(key: String, mapper: (JsonElement) -> R): List<R> {
    return (this[key] as JsonArray).map(mapper)
}

inline fun <reified R> asObject(element: JsonElement, action: JsonObject.() -> R): R {
    return action(element as JsonObject)
}