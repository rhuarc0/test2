package com.zennex.trl3lg.presentation.mapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.entity.Site;
import com.zennex.trl3lg.data.mapper.Mapper;
import com.zennex.trl3lg.presentation.model.SiteModel;

import javax.inject.Inject;

/**
 * Created by nikita on 05.06.17.
 */

public class SiteModelMapper extends Mapper<SiteModel, Site> {

    @Inject
    public SiteModelMapper() {
        super(Site::new);
    }

    @NonNull
    @Override
    protected Site transform(@NonNull SiteModel siteModel, Site site) {
        site.setDomain(siteModel.getDomain());
        site.setUrl(siteModel.getUrl());
        return site;
    }

}
