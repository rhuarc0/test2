package com.zennex.trl3lg.data.rest.response.auth;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.entity.dto.SiteDto;
import com.zennex.trl3lg.data.rest.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by nikita on 03.06.17.
 */

public class GetSitesResponse extends BaseResponse<GetSitesResponse.Sites> {

    public static class Sites {
        @SerializedName("Sites")
        private ArrayList<SiteDto> mSiteDtos;

        public ArrayList<SiteDto> getSiteDtos() {
            return mSiteDtos;
        }

        public void setSiteDtos(ArrayList<SiteDto> siteDtos) {
            mSiteDtos = siteDtos;
        }
    }

}
