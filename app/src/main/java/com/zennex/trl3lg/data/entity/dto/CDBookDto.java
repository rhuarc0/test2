package com.zennex.trl3lg.data.entity.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 14.03.18.
 */

public class CDBookDto extends RentalBookDto {

    @SerializedName("ShippingDate")
    private String mShippingDate;
    @SerializedName("TrackingNumber")
    private String mTrackingNumber;
    @SerializedName("TrackingUrl")
    private String mTrackingUrl;

    public String getShippingDate() {
        return mShippingDate;
    }

    public void setShippingDate(String shippingDate) {
        mShippingDate = shippingDate;
    }

    public String getTrackingNumber() {
        return mTrackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        mTrackingNumber = trackingNumber;
    }

    public String getTrackingUrl() {
        return mTrackingUrl;
    }

    public void setTrackingUrl(String trackingUrl) {
        mTrackingUrl = trackingUrl;
    }
}
