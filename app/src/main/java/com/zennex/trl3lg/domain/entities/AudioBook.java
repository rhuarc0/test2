package com.zennex.trl3lg.domain.entities;

import android.os.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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

    private static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:s";
    private static final String TIME_ZONE = "America/New_York"; // EDT - Серверное время
    private static final String FINISH_RENT_BOOK_TIME = " 23:59:59"; // Конец дня, завершение аренды книги
    private static final int MS_IN_DAY = 1000 * 60 * 60 * 24;

    public int getDaysLeft() {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE, Locale.US);
        TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));

        if (getRentalEnd() != null) {

            Date today = new Date();
            int days = 0;
            long difference;
            try {
                Date date = format.parse(getRentalEnd() + FINISH_RENT_BOOK_TIME);
                difference = date.getTime() - today.getTime();
                days = (int) (difference / MS_IN_DAY);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return days + 1;
        }
        return 0;
    }

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
