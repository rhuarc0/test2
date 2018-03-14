package com.zennex.trl3lg.data.rest.response.auth;

import com.zennex.trl3lg.data.rest.response.BaseResponse;

/**
 * Created by nikita on 26.07.17.
 */

public class IsValidSessionTokenResponse extends BaseResponse<Integer> {
    public static final int SESSION_VALID = 1;
    public static final int SESSION_NOT_VALID = 0;
}
