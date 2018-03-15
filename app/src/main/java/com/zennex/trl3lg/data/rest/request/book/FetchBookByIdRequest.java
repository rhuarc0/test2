package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 15.03.18.
 */

public class FetchBookByIdRequest extends BaseRequest<FetchBookByIdRequest.Data> {

    private static final String API_METHOD = "getRentalItem";

    public static FetchBookByIdRequest newInstance(String moduleId,
                                                   String bookId) {

        FetchBookByIdRequest request = new FetchBookByIdRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(bookId));
        return request;
    }

    private FetchBookByIdRequest() {}

    public static class Data {

        @SerializedName("Id")
        private String mBookId;

        public Data(String bookId) {
            mBookId = bookId;
        }

    }

}