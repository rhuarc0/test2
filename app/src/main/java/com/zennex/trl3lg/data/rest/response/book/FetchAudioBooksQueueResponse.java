package com.zennex.trl3lg.data.rest.response.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.entity.dto.AudioBookDto;
import com.zennex.trl3lg.data.entity.dto.CDBookDto;
import com.zennex.trl3lg.data.rest.response.BaseResponse;

import java.util.List;

/**
 * Created by nikit on 27.08.2017.
 */

public class FetchAudioBooksQueueResponse extends BaseResponse<FetchAudioBooksQueueResponse.Data> {

    public static class Data {

        @SerializedName("Queue")
        private List<CDBookDto> cdBookDtoList;

        @SerializedName("LiveQueue")
        private List<AudioBookDto> audioBookDtoList;

        @SerializedName("AudioApiSessionKey")
        private String audioApiSessionKey;

        @SerializedName("AudioApiAccountId")
        private String audioApiAccountId;

        public List<CDBookDto> getCdBookDtoList() {
            return cdBookDtoList;
        }

        public void setCdBookDtoList(List<CDBookDto> mCdBookDtoList) {
            this.cdBookDtoList = mCdBookDtoList;
        }

        public List<AudioBookDto> getAudioBookDtoList() {
            return audioBookDtoList;
        }

        public void setAudioBookDtoList(List<AudioBookDto> audioBookDtoList) {
            this.audioBookDtoList = audioBookDtoList;
        }

        public String getAudioApiSessionKey() {
            return audioApiSessionKey;
        }

        public void setAudioApiSessionKey(String audioApiSessionKey) {
            this.audioApiSessionKey = audioApiSessionKey;
        }

        public String getAudioApiAccountId() {
            return audioApiAccountId;
        }

        public void setAudioApiAccountId(String audioApiAccountId) {
            this.audioApiAccountId = audioApiAccountId;
        }
    }
}
