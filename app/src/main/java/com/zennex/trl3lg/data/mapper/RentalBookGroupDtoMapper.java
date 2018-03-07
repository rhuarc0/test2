package com.zennex.trl3lg.data.mapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.entity.RentalGroup;
import com.zennex.trl3lg.data.entity.dto.RentalGroupDto;

import javax.inject.Inject;

/**
 * Created by zennex on 27.07.17.
 */

public class RentalBookGroupDtoMapper extends Mapper<RentalGroupDto, RentalGroup> {

    @Inject
    public RentalBookGroupDtoMapper() {
        super(RentalGroup::new);
    }

    @NonNull
    @Override
    protected RentalGroup transform(@NonNull RentalGroupDto rentalGroupDto, RentalGroup rentalGroup) {
        rentalGroup.setId(Long.parseLong(rentalGroupDto.getId()));
        rentalGroup.setParentId(Long.parseLong(rentalGroupDto.getParentId()));
        rentalGroup.setTitle(rentalGroupDto.getTitle());
        rentalGroup.setPath(rentalGroup.getPath());
        rentalGroup.setActive(rentalGroupDto.getActive().equals("1"));
        return rentalGroup;
    }
}
