package com.zennex.trl3lg.data.datasource.site;

import io.reactivex.Observable;

/**
 * Created by nikita on 12.06.17.
 */

public interface ISiteDataSourceLocal {

    Observable<Boolean> saveSiteId(String siteId);

    Observable<String> getSiteId();



}
