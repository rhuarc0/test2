package com.zennex.trl3lg.presentation.mapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.entities.Site;
import com.zennex.trl3lg.data.mapper.base.Mapper;
import com.zennex.trl3lg.presentation.model.SiteModel;

import javax.inject.Inject;

/**
 * Created by nikita on 03.06.17.
 */

public class SiteMapper extends Mapper<Site, SiteModel> {

    @Inject
    public SiteMapper() {
        super(SiteModel::new);
    }

    @NonNull
    @Override
    protected SiteModel transform(@NonNull Site site, SiteModel siteModel) {
        siteModel.setDomain(site.getDomain());
        siteModel.setUrl(site.getUrl());
        return siteModel;
    }
}
