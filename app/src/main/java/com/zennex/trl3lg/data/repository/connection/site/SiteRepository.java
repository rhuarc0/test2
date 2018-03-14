package com.zennex.trl3lg.data.repository.connection.site;

import com.zennex.trl3lg.data.entity.rest.request.GetSitesRequest;
import com.zennex.trl3lg.data.entity.rest.response.GetSitesResponse;
import com.zennex.trl3lg.data.datasource.site.ISiteDataSourceLocal;
import com.zennex.trl3lg.data.datasource.site.ISiteDataSourceRemote;
import com.zennex.trl3lg.data.mapper.FetchSitesResponseMapper;
import com.zennex.trl3lg.domain.entities.Site;
import com.zennex.trl3lg.domain.repository.ISiteRepository;

import java.util.ArrayList;
import java.util.List;
import com.zennex.trl3lg.data.rest.request.auth.GetSitesRequest;
import com.zennex.trl3lg.data.rest.response.auth.GetSitesResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by nikita on 03.06.17.
 */

public class SiteRepository implements ISiteRepository {

    @Inject
    protected ISiteDataSourceRemote siteDataSourceRemote;

    @Inject
    protected ISiteDataSourceLocal siteDataSourceLocal;

    @Inject
    protected FetchSitesResponseMapper mGetSitesResponseMapper;


    @Inject
    public SiteRepository() {
    }

    @Override
    public Observable<List<Site>> getSites() {
        return siteDataSourceRemote.getSites(createGetSitesRequest())
                .doOnNext(checkResponse())
                .map(transform());
    }

    @Override
    public Observable<Boolean> saveSiteId(String siteId) {
        return siteDataSourceLocal.saveSiteId(siteId);
    }

    @Override
    public Observable<String> getSiteId() {
        return siteDataSourceLocal.getSiteId();
    }


    private GetSitesRequest createGetSitesRequest() {
        GetSitesRequest.Data data = new GetSitesRequest.Data();
        data.addType(GetSitesRequest.Data.TYPE_RENTAL);
        data.addType(GetSitesRequest.Data.TYPE_MEMBERSHIP);
        return GetSitesRequest.newInstance(data);
    }

    private Consumer<GetSitesResponse> checkResponse() {
        return getSitesResponse -> {

            if (Integer.parseInt(getSitesResponse.getErrorCode()) != 0) {
                throw new RuntimeException(getSitesResponse.getErrorText());
            }
        };
    }

    private Function<GetSitesResponse, List<Site>> transform() {
        return getSitesResponse -> {
            if (getSitesResponse.getData() == null)
                return new ArrayList<>();
            return mGetSitesResponseMapper.execute(getSitesResponse.getData().getSiteItemDtos());
        };
    }

}
