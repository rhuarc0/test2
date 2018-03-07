package com.zennex.trl3lg.data.entity;

import android.os.Parcel;

/**
 * Created by nikit on 27.08.2017.
 */

public class RentalBook extends Book {

    String mRentalStart;
    String mRentalEnd;

    protected RentalBook(Parcel in) {
        super(in);
        mRentalStart = in.readString();
        mRentalEnd = in.readString();
    }

    public RentalBook() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mRentalStart);
        dest.writeString(mRentalEnd);
    }

    public String getRentalStart() {
        return mRentalStart;
    }

    public void setRentalStart(String rentalStart) {
        mRentalStart = rentalStart;
    }

    public String getRentalEnd() {
        return mRentalEnd;
    }

    public void setRentalEnd(String rentalEnd) {
        mRentalEnd = rentalEnd;
    }
}
