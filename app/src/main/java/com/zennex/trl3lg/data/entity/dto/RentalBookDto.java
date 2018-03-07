package com.zennex.trl3lg.data.entity.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikit on 27.08.2017.
 */

public class RentalBookDto extends BookDto {

    @SerializedName("RentalStart")
    private String mRentalStart;
    @SerializedName("RentalEnd")
    private String mRentalEnd;


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
