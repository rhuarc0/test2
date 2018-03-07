package com.zennex.trl3lg.data.entity;

/**
 * Created by nikit on 27.08.2017.
 */

public class AudioBookChapter {

    int mId;
    String mDuration;
    String mPartNumber;
    String mChapterNumber;


    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

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
