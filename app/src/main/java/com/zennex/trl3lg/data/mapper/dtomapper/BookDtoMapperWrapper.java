package com.zennex.trl3lg.data.mapper.dtomapper;

import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.data.entity.dto.BookDto;

import javax.inject.Inject;

/**
 * Created by nikit on 27.08.2017.
 */

public class BookDtoMapperWrapper extends BookDtoMapper<BookDto, Book> {
    @Inject
    public BookDtoMapperWrapper() {
        super(Book::new);
    }
}
