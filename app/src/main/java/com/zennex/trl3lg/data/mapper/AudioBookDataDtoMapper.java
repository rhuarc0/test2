package com.zennex.trl3lg.data.mapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.entity.AudioBook;
import com.zennex.trl3lg.data.entity.dto.AudioBookDto;

import javax.inject.Inject;

/**
 * Created by nikit on 27.08.2017.
 */

public class AudioBookDataDtoMapper extends Mapper<AudioBookDto.Data, AudioBook.Data> {

    @Inject
    AudioBookChapterDtoMapper mAudioBookChapterDtoMapper;

    @Inject
    public AudioBookDataDtoMapper() {
        super(AudioBook.Data::new);
    }


    @NonNull
    @Override
    protected AudioBook.Data transform(@NonNull AudioBookDto.Data dataDto, AudioBook.Data data) {
        data.setAudioApiBookId(dataDto.getAudioApiBookId());
        data.setAudioApiCheckoutKey(dataDto.getAudioApiCheckoutKey());
        if (dataDto.getAudioApiChapterDtos() != null) {
            data.setChapterList(mAudioBookChapterDtoMapper.execute(dataDto.getAudioApiChapterDtos()));
        }
        return data;
    }

}
