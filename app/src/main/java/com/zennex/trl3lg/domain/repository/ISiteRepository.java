package com.zennex.trl3lg.domain.repository;

import com.zennex.trl3lg.domain.entities.Site;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by nikita on 03.06.17.
 */

public interface ISiteRepository {

    Observable<List<Site>> getSites();

    Observable<Boolean> saveSiteId(String siteId);

    Observable<String> getSiteId();

}
