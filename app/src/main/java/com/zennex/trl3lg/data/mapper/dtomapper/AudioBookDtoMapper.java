package com.zennex.trl3lg.data.mapper.dtomapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.data.entity.dto.AudioBookDto;

import javax.inject.Inject;

/**
 * Created by nikit on 27.08.2017.
 */

public class AudioBookDtoMapper extends RentalBookDtoMapper<AudioBookDto, AudioBook> {

    @Inject
    AudioBookDataDtoMapper mAudioBookDataDtoMapper;

    @Inject
    public AudioBookDtoMapper() {
        super(AudioBook::new);
    }

    @NonNull
    @Override
    protected AudioBook transform(@NonNull AudioBookDto bookDto, AudioBook book) {
        super.transform(bookDto, book);
        book.setQueueId(bookDto.getQueueId());
        book.setStoredPosition(bookDto.getStoredPosition());
        if (bookDto.getData() != null) {
            book.setData(mAudioBookDataDtoMapper.execute(bookDto.getData()));
        }
        return book;
    }
}
