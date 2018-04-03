package com.zennex.trl3lg.data.rest.response.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.entity.dto.CDBookDto;
import com.zennex.trl3lg.data.rest.response.BaseResponse;

import java.util.List;

/**
 * Created by nikita on 14.03.18.
 */

public class FetchCDBooksQueueResponse extends BaseResponse<FetchBooksQueueResponse.Data> {

    public static class Data {

        @SerializedName("LiveQueue")
        private List<CDBookDto> mCDBookDtoList;

        public List<CDBookDto> getAudioBookDtoList() {
            return mCDBookDtoList;
        }

        public void setAudioBookDtoList(List<CDBookDto> cdBookDtoList) {
            mCDBookDtoList = cdBookDtoList;
        }

    }
}