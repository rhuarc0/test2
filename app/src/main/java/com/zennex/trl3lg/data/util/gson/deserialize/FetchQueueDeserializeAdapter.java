package com.zennex.trl3lg.data.util.gson.deserialize;

import com.annimon.stream.Stream;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.zennex.trl3lg.data.rest.response.book.FetchAudioBooksQueueResponse;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by nikit on 27.08.2017.
 */

public class FetchQueueDeserializeAdapter implements JsonDeserializer<List<FetchAudioBooksQueueResponse>> {

    @Override
    public List<FetchAudioBooksQueueResponse> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<FetchAudioBooksQueueResponse> result;
        if (json.isJsonArray()) {
            result = Stream.of(json.getAsJsonArray())
                    .map(jsonElement -> context.<FetchAudioBooksQueueResponse>deserialize(jsonElement, FetchAudioBooksQueueResponse.class))
                    .toList();
        } else {
            result = Stream.of(json)
                    .map(jsonElement -> context.<FetchAudioBooksQueueResponse>deserialize(jsonElement, FetchAudioBooksQueueResponse.class))
                    .toList();
        }

        return result;
    }
}
