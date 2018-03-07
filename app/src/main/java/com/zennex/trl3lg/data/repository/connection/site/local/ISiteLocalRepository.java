package com.zennex.trl3lg.data.repository.connection.site.local;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

/**
 * Created by nikita on 12.06.17.
 */

public interface ISiteLocalRepository {

    Observable<Boolean> saveSiteId(String siteId);

    Observable<String> getSiteId();



}
