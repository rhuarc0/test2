package com.zennex.trl3lg.data.util.gson.deserialize;

import com.annimon.stream.Stream;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.zennex.trl3lg.data.rest.response.book.FetchBooksQueueResponse;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by nikit on 27.08.2017.
 */

public class FetchQueueDeserializeAdapter implements JsonDeserializer<List<FetchBooksQueueResponse>> {

    @Override
    public List<FetchBooksQueueResponse> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<FetchBooksQueueResponse> result;
        if (json.isJsonArray()) {
            result = Stream.of(json.getAsJsonArray())
                    .map(jsonElement -> context.<FetchBooksQueueResponse>deserialize(jsonElement, FetchBooksQueueResponse.class))
                    .toList();
        } else {
            result = Stream.of(json)
                    .map(jsonElement -> context.<FetchBooksQueueResponse>deserialize(jsonElement, FetchBooksQueueResponse.class))
                    .toList();
        }

        return result;
    }
}
