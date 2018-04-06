package com.zennex.trl3lg.data.util.repository;

import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.exception.WebApiException;

import java.util.List;

public class WebRepositoryUtils {

    private static String NO_ERROR_CODE = "0";
    private static String NO_ERROR_TEXT = "";

    public static <T extends BaseResponse> void checkResponse(List<T> responses) {
        for (BaseResponse response : responses) {
            checkResponse(response);
        }
    }

    public static <T extends BaseResponse> void checkResponse(T response) {
        WebApiException exception = null;
            if (!response.getErrorCode().equals(NO_ERROR_CODE) && !response.getErrorText().equals(NO_ERROR_TEXT)) {
                exception = new WebApiException(response.getErrorText(), response.getErrorCode());
            }
        if (exception != null)
            throw exception;
    }


}
