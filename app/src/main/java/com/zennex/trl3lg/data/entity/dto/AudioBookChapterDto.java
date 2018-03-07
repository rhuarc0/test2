package com.zennex.trl3lg.data.entity.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikit on 27.08.2017.
 */

public class AudioBookChapterDto {

    @SerializedName("duration")
    private String mDuration;
    @SerializedName("part_number")
    private String mPartNumber;
    @SerializedName("chapter_number")
    private String mChapterNumber;

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public String getPartNumber() {
        return mPartNumber;
    }

    public void setPartNumber(String partNumber) {
        mPartNumber = partNumber;
    }

    public String getChapterNumber() {
        return mChapterNumber;
    }

    public void setChapterNumber(String chapterNumber) {
        mChapterNumber = chapterNumber;
    }

}
