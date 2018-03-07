package com.zennex.trl3lg.data.mapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.entity.Module;
import com.zennex.trl3lg.data.entity.dto.ModuleDto;

import javax.inject.Inject;

/**
 * Created by nikita on 03.06.17.
 */

public class ModuleDtoMapper extends Mapper<ModuleDto, Module> {

    @Inject
    public ModuleDtoMapper() {
        super(Module::new);
    }

    @NonNull
    @Override
    protected Module transform(@NonNull ModuleDto moduleDto, Module module) {
        module.setId(moduleDto.getId());
        module.setType(moduleDto.getType());
        module.setName(moduleDto.getName());
        module.setNumber(module.getNumber());
        module.setMembershipId(moduleDto.getMembershipId());
        return module;
    }

}
