package com.zennex.trl3lg.data.rest.response.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.entity.dto.AudioBookDto;
import com.zennex.trl3lg.data.rest.response.BaseResponse;

import java.util.List;

/**
 * Created by nikit on 27.08.2017.
 */

public class FetchQueueBooksResponse extends BaseResponse<FetchQueueBooksResponse.Data> {

    public static class Data {

        @SerializedName("LiveQueue")
        private List<AudioBookDto> mAudioBookDtoList;

        @SerializedName("AudioApiSessionKey")
        private String mAudioApiSessionKey;

        @SerializedName("AudioApiAccountId")
        private String mAudioApiAccountId;

        public List<AudioBookDto> getAudioBookDtoList() {
            return mAudioBookDtoList;
        }

        public void setAudioBookDtoList(List<AudioBookDto> audioBookDtoList) {
            mAudioBookDtoList = audioBookDtoList;
        }

        public String getAudioApiSessionKey() {
            return mAudioApiSessionKey;
        }

        public void setAudioApiSessionKey(String audioApiSessionKey) {
            mAudioApiSessionKey = audioApiSessionKey;
        }

        public String getAudioApiAccountId() {
            return mAudioApiAccountId;
        }

        public void setAudioApiAccountId(String audioApiAccountId) {
            mAudioApiAccountId = audioApiAccountId;
        }
    }
}
