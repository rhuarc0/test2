package com.zennex.trl3lg.data.exception;

/**
 * Created by nikit on 27.08.2017.
 */

public class WebApiException extends RuntimeException {

    private String mCode;

    public WebApiException(String message, String code) {
        super(message);
        mCode = code;
    }

    public String getCode() {
        return mCode;
    }
}
