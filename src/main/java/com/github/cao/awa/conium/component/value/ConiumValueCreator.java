package com.github.cao.awa.conium.component.value;

import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import com.google.gson.JsonElement;

public interface ConiumValueCreator<T> {
    T createValue(JsonElement element);

    default T castValue(Object value) {
        if (value instanceof JsonElement jsonElement) {
            return createValue(jsonElement);
        }
        return Manipulate.cast(value);
    }
}
