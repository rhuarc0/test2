package com.zennex.trl3lg.data.util.gson.deserialize;

import com.annimon.stream.Stream;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.zennex.trl3lg.data.rest.response.FetchRentalGroupsResponse;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zennex on 27.07.17.
 */

public class FetchRentalGroupsDeserializeAdapter implements JsonDeserializer<List<FetchRentalGroupsResponse>> {

    @Override
    public List<FetchRentalGroupsResponse> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<FetchRentalGroupsResponse> result;
        if (json.isJsonArray()) {
            result = Stream.of(json.getAsJsonArray())
                    .map(jsonElement -> context.<FetchRentalGroupsResponse>deserialize(jsonElement, FetchRentalGroupsResponse.class))
                    .toList();
        } else {
            result = Stream.of(json)
                    .map(jsonElement -> context.<FetchRentalGroupsResponse>deserialize(jsonElement, FetchRentalGroupsResponse.class))
                    .toList();
        }

        return result;
    }
}