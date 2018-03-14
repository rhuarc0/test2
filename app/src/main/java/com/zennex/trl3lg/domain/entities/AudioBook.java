package com.zennex.trl3lg.domain.entities;

import android.os.Parcel;

import java.util.List;

/**
 * Created by nikit on 27.08.2017.
 */

public class AudioBook extends RentalBook {

    protected String mQueueId;
    protected String mStoredPosition;
    protected Data mData;

    public AudioBook(){

    }


    protected AudioBook(Parcel in) {
        super(in);
        mQueueId = in.readString();
        mStoredPosition = in.readString();
     //   mData = (Data) in.readValue(Data.class.getClassLoader());
    }

    //region Parcelable

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mQueueId);
        dest.writeString(mStoredPosition);
    //    dest.writeValue(mData);
    }

    //endregion Parcelable


    public String getQueueId() {
        return mQueueId;
    }

    public void setQueueId(String queueId) {
        mQueueId = queueId;
    }

    public String getStoredPosition() {
        return mStoredPosition;
    }

    public void setStoredPosition(String storedPosition) {
        mStoredPosition = storedPosition;
    }

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public static class Data {
        int mId;
        String mAudioApiCheckoutKey;
        String mAudioApiBookId;
        List<AudioBookChapter> mChapterList;

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            mId = id;
        }

        public String getAudioApiCheckoutKey() {
            return mAudioApiCheckoutKey;
        }

        public void setAudioApiCheckoutKey(String audioApiCheckoutKey) {
            mAudioApiCheckoutKey = audioApiCheckoutKey;
        }

        public String getAudioApiBookId() {
            return mAudioApiBookId;
        }

        public void setAudioApiBookId(String audioApiBookId) {
            mAudioApiBookId = audioApiBookId;
        }

        public List<AudioBookChapter> getChapterList() {
            return mChapterList;
        }

        public void setChapterList(List<AudioBookChapter> chapterList) {
            mChapterList = chapterList;
        }
    }

}
