package com.zennex.trl3lg.domain.entities;

import android.os.Parcel;

/**
 * Created by nikita on 14.03.18.
 */

public class CDBook extends RentalBook {

    private String mShippingDate;
    private String mTrackingNumber;
    private String mTrackingUrl;

    public CDBook(){

    }

    protected CDBook(Parcel in) {
        super(in);
        mShippingDate = in.readString();
        mTrackingNumber = in.readString();
        mTrackingUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mShippingDate);
        dest.writeString(mTrackingNumber);
        dest.writeString(mTrackingUrl);
    }

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
