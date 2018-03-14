package com.zennex.trl3lg.data.util.gson.deserialize;

import com.annimon.stream.Stream;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.zennex.trl3lg.data.rest.response.book.FetchReviewsResponse;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by nikita on 20.10.17.
 */

public class FetchReviewsDeserializeAdapter implements JsonDeserializer<List<FetchReviewsResponse>> {

    @Override
    public List<FetchReviewsResponse> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<FetchReviewsResponse> result;
        if (json.isJsonArray()) {
            result = Stream.of(json.getAsJsonArray())
                    .map(jsonElement -> context.<FetchReviewsResponse>deserialize(jsonElement, FetchReviewsResponse.class))
                    .toList();
        } else {
            result = Stream.of(json)
                    .map(jsonElement -> context.<FetchReviewsResponse>deserialize(jsonElement, FetchReviewsResponse.class))
                    .toList();
        }

        return result;
    }
}