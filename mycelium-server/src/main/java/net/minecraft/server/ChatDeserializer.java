package net.minecraft.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang3.StringUtils;

public class ChatDeserializer {

    public static boolean d(JsonObject jsonobject, String s) {
        return !g(jsonobject, s) ? false : jsonobject.get(s).isJsonArray();
    }

    public static boolean g(JsonObject jsonobject, String s) {
        return jsonobject == null ? false : jsonobject.get(s) != null;
    }

    public static String a(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonPrimitive()) {
            return jsonelement.getAsString();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a string, was " + d(jsonelement));
        }
    }

    public static String h(JsonObject jsonobject, String s) {
        if (jsonobject.has(s)) {
            return a(jsonobject.get(s), s);
        } else {
            throw new JsonSyntaxException("Missing " + s + ", expected to find a string");
        }
    }

    public static boolean b(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonPrimitive()) {
            return jsonelement.getAsBoolean();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a Boolean, was " + d(jsonelement));
        }
    }

    public static boolean a(JsonObject jsonobject, String s, boolean flag) {
        return jsonobject.has(s) ? b(jsonobject.get(s), s) : flag;
    }

    public static float d(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonPrimitive() && jsonelement.getAsJsonPrimitive().isNumber()) {
            return jsonelement.getAsFloat();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a Float, was " + d(jsonelement));
        }
    }

    public static float a(JsonObject jsonobject, String s, float f) {
        return jsonobject.has(s) ? d(jsonobject.get(s), s) : f;
    }

    public static int f(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonPrimitive() && jsonelement.getAsJsonPrimitive().isNumber()) {
            return jsonelement.getAsInt();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a Int, was " + d(jsonelement));
        }
    }

    public static int m(JsonObject jsonobject, String s) {
        if (jsonobject.has(s)) {
            return f(jsonobject.get(s), s);
        } else {
            throw new JsonSyntaxException("Missing " + s + ", expected to find a Int");
        }
    }

    public static int a(JsonObject jsonobject, String s, int i) {
        return jsonobject.has(s) ? f(jsonobject.get(s), s) : i;
    }

    public static JsonObject l(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonObject()) {
            return jsonelement.getAsJsonObject();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a JsonObject, was " + d(jsonelement));
        }
    }

    public static JsonArray m(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonArray()) {
            return jsonelement.getAsJsonArray();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a JsonArray, was " + d(jsonelement));
        }
    }

    public static JsonArray t(JsonObject jsonobject, String s) {
        if (jsonobject.has(s)) {
            return m(jsonobject.get(s), s);
        } else {
            throw new JsonSyntaxException("Missing " + s + ", expected to find a JsonArray");
        }
    }

    public static String d(JsonElement jsonelement) {
        String s = StringUtils.abbreviateMiddle(String.valueOf(jsonelement), "...", 10);

        if (jsonelement == null) {
            return "null (missing)";
        } else if (jsonelement.isJsonNull()) {
            return "null (json)";
        } else if (jsonelement.isJsonArray()) {
            return "an array (" + s + ")";
        } else if (jsonelement.isJsonObject()) {
            return "an object (" + s + ")";
        } else {
            if (jsonelement.isJsonPrimitive()) {
                JsonPrimitive jsonprimitive = jsonelement.getAsJsonPrimitive();

                if (jsonprimitive.isNumber()) {
                    return "a number (" + s + ")";
                }

                if (jsonprimitive.isBoolean()) {
                    return "a boolean (" + s + ")";
                }
            }

            return s;
        }
    }
}
