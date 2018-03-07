package com.zennex.trl3lg.data.mapper;

import android.support.annotation.NonNull;

import com.annimon.stream.function.Supplier;
import com.zennex.trl3lg.domain.entities.RentalBook;
import com.zennex.trl3lg.data.entity.dto.RentalBookDto;

import javax.inject.Inject;

/**
 * Created by nikit on 27.08.2017.
 */

public class RentalBookDtoMapper<S2 extends RentalBookDto, R2 extends RentalBook> extends BookDtoMapper<S2, R2> {


    @Inject
    public RentalBookDtoMapper(@NonNull Supplier<R2> creator) {
        super(creator);
    }

    @NonNull
    @Override
    protected R2 transform(@NonNull S2 bookDto, R2 book) {
        super.transform(bookDto, book);
        book.setRentalStart(bookDto.getRentalStart());
        book.setRentalEnd(bookDto.getRentalEnd());
        return book;
    }
}
