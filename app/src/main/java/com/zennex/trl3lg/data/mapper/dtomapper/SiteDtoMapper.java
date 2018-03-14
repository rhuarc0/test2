package com.zennex.trl3lg.data.mapper.dtomapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.mapper.base.Mapper;
import com.zennex.trl3lg.domain.entities.Site;
import com.zennex.trl3lg.data.entity.dto.SiteDto;

import javax.inject.Inject;

/**
 * Created by nikita on 03.06.17.
 */

public class SiteDtoMapper extends Mapper<SiteDto, Site> {

    @Inject
    protected ModuleDtoMapper mModuleDtoMapper;

    @Inject
    public SiteDtoMapper() {
        super(Site::new);
    }


    @NonNull
    @Override
    protected Site transform(@NonNull SiteDto siteDto, Site site) {
        site.setId(siteDto.getId());
        site.setDomain(siteDto.getDomain());
        site.setUrl(siteDto.getUrl());
        site.setModules(mModuleDtoMapper.execute(siteDto.getModuleDtos()));
        return site;
    }


}
