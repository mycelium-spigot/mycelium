package net.minecraft.server;

import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Set;

public class AchievementSet extends ForwardingSet<String> implements IJsonStatistic {
    private final Set<String> a = Sets.newHashSet();

    public void a(JsonElement paramJsonElement) {
        if (paramJsonElement.isJsonArray())
            for (JsonElement jsonElement : paramJsonElement.getAsJsonArray())
                add(jsonElement.getAsString());
    }

    public JsonElement a() {
        JsonArray jsonArray = new JsonArray();
        for (String str : this)
            jsonArray.add((JsonElement) new JsonPrimitive(str));
        return (JsonElement) jsonArray;
    }

    protected Set<String> delegate() {
        return this.a;
    }
}