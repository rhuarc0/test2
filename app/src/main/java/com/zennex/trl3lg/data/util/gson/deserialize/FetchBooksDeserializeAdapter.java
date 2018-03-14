package com.zennex.trl3lg.data.util.gson.deserialize;

import com.annimon.stream.Stream;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.zennex.trl3lg.data.rest.response.FetchBookListResponse;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by nikita on 08.08.17.
 */

public class FetchBooksDeserializeAdapter implements JsonDeserializer<List<FetchBookListResponse>> {


    @Override
    public List<FetchBookListResponse> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<FetchBookListResponse> result;
        if (json.isJsonArray()) {
            result = Stream.of(json.getAsJsonArray())
                    .map(jsonElement -> context.<FetchBookListResponse>deserialize(jsonElement, FetchBookListResponse.class))
                    .toList();
        } else {
            result = Stream.of(json)
                    .map(jsonElement -> context.<FetchBookListResponse>deserialize(jsonElement, FetchBookListResponse.class))
                    .toList();
        }

        return result;
    }


}
