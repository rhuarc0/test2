package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 15.03.18.
 */

public class RateAndReviewBookRequest extends BaseRequest<RateAndReviewBookRequest.Data> {

    private static final String API_METHOD = "rateItem";

    public static RateAndReviewBookRequest newInstance(String moduleId,
                                                       String sessionId,
                                                       String bookId,
                                                       String rating,
                                                       String textReview) {

        RateAndReviewBookRequest request = new RateAndReviewBookRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(sessionId, bookId, rating, textReview));
        return request;
    }

    private RateAndReviewBookRequest() {
    }

    public static class Data {

        private static final String APPROVED = "1"; // 1 or 0. 0 means that site owner will approve(or not) this review
        private static final String IGNORE_NOTIFICATION = "1"; // (optional) if set, notification to site owner will not be sent

        @SerializedName("SessionId")
        private String mSessionId;

        @SerializedName("Id")
        private String mBookId;

        @SerializedName("Rate")
        private String mRating;

        @SerializedName("Comments")
        private String mTextReview;

        @SerializedName("Approved")
        private String mApproved;

        @SerializedName("IgnoreNotification")
        private String mIgnoreNotification;

        public Data(String sessionId,
                    String bookId,
                    String rating,
                    String textReview) {
            mSessionId = sessionId;
            mBookId = bookId;
            mRating = rating;
            mTextReview = textReview;
            mApproved = APPROVED;
            mIgnoreNotification = IGNORE_NOTIFICATION;
        }

    }

}
