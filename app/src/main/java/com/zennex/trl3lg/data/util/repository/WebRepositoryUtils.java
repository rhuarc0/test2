package com.zennex.trl3lg.data.util.repository;

import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.exception.WebApiException;

import java.util.List;

/**
 * Created by nikita on 20.10.17.
 */

public class WebRepositoryUtils {

    private static String NO_ERROR_CODE = "0";
    private static String ERROR_TEXT_BOOKS_NOT_FOUND = "";

    public  static  <T extends BaseResponse> void checkResponse(List<T> responses) {
        WebApiException exception = null;
        for (BaseResponse response : responses) {
            if (!response.getErrorCode().equals(NO_ERROR_CODE)
                    && !response.getErrorText().equals(ERROR_TEXT_BOOKS_NOT_FOUND)) {
                exception = new WebApiException(response.getErrorText(), response.getErrorCode());
            }
        }
        if (exception != null) throw exception;
    }


}
