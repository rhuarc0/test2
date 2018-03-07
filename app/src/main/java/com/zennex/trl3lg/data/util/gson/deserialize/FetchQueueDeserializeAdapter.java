package com.zennex.trl3lg.data.util.gson.deserialize;

import com.annimon.stream.Stream;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.zennex.trl3lg.data.entity.rest.response.FetchQueueBooksResponse;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by nikit on 27.08.2017.
 */

public class FetchQueueDeserializeAdapter implements JsonDeserializer<List<FetchQueueBooksResponse>> {

    @Override
    public List<FetchQueueBooksResponse> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<FetchQueueBooksResponse> result;
        if (json.isJsonArray()) {
            result = Stream.of(json.getAsJsonArray())
                    .map(jsonElement -> context.<FetchQueueBooksResponse>deserialize(jsonElement, FetchQueueBooksResponse.class))
                    .toList();
        } else {
            result = Stream.of(json)
                    .map(jsonElement -> context.<FetchQueueBooksResponse>deserialize(jsonElement, FetchQueueBooksResponse.class))
                    .toList();
        }

        return result;
    }
}
