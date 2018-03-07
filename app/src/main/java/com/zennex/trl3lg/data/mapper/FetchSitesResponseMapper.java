package com.zennex.trl3lg.data.mapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.entity.Site;
import com.zennex.trl3lg.data.entity.dto.SiteItemDto;

import javax.inject.Inject;

/**
 * Created by nikita on 03.06.17.
 */

public class FetchSitesResponseMapper extends Mapper<SiteItemDto, Site> {

    @Inject
    protected ModuleDtoMapper mModuleDtoMapper;

    @Inject
    public FetchSitesResponseMapper() {
        super(Site::new);
    }


    @NonNull
    @Override
    protected Site transform(@NonNull SiteItemDto siteItemDto, Site site) {
        site.setId(siteItemDto.getId());
        site.setDomain(siteItemDto.getDomain());
        site.setUrl(siteItemDto.getUrl());
        site.setModules(mModuleDtoMapper.execute(siteItemDto.getModuleDtos()));
        return site;
    }


}
