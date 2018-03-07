package com.zennex.trl3lg.data.entity.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikit on 27.08.2017.
 */

public class AudioBookDto extends RentalBookDto {

    @SerializedName("queue_id")
    private String mQueueId;
    @SerializedName("data")
    private Data mData;
    @SerializedName("storedPosition")
    private String mStoredPosition;

    public String getQueueId() {
        return mQueueId;
    }

    public void setQueueId(String queueId) {
        mQueueId = queueId;
    }

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public String getStoredPosition() {
        return mStoredPosition;
    }

    public void setStoredPosition(String storedPosition) {
        mStoredPosition = storedPosition;
    }

    public static class Data {

        @SerializedName("audioApiCheckoutKey")
        private String mAudioApiCheckoutKey;
        @SerializedName("audioApiBookId")
        private String mAudioApiBookId;
        @SerializedName("audioApiChapters")
        private List<AudioBookChapterDto> mAudioApiChapterDtos;

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

        public List<AudioBookChapterDto> getAudioApiChapterDtos() {
            return mAudioApiChapterDtos;
        }

        public void setAudioApiChapterDtos(List<AudioBookChapterDto> audioApiChapterDtos) {
            mAudioApiChapterDtos = audioApiChapterDtos;
        }
    }
}
