package com.zennex.trl3lg.data.mapper.dtomapper;

import android.support.annotation.NonNull;

import com.annimon.stream.function.Supplier;
import com.zennex.trl3lg.data.entity.dto.CDBookDto;
import com.zennex.trl3lg.data.mapper.base.Mapper;
import com.zennex.trl3lg.domain.entities.CDBook;

/**
 * Created by nikita on 14.03.18.
 */

public class CDBookDtoMapper extends RentalBookDtoMapper<CDBookDto, CDBook> {

    public CDBookDtoMapper() {
        super(CDBook::new);
    }

    @NonNull
    @Override
    protected CDBook transform(@NonNull CDBookDto cdBookDto, CDBook cdBook) {
        super.transform(cdBookDto, cdBook);
        cdBook.setShippingDate(cdBookDto.getShippingDate());
        cdBook.setTrackingNumber(cdBookDto.getTrackingNumber());
        cdBook.setTrackingUrl(cdBookDto.getTrackingUrl());
        return cdBook;
    }
}
