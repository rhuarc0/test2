package com.zennex.trl3lg.data.mapper.dtomapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.mapper.base.Mapper;
import com.zennex.trl3lg.domain.entities.AudioBookChapter;
import com.zennex.trl3lg.data.entity.dto.AudioBookChapterDto;

import javax.inject.Inject;

/**
 * Created by nikit on 27.08.2017.
 */

public class AudioBookChapterDtoMapper extends Mapper<AudioBookChapterDto, AudioBookChapter> {

    @Inject
    public AudioBookChapterDtoMapper() {
        super(AudioBookChapter::new);
    }

    @NonNull
    @Override
    protected AudioBookChapter transform(@NonNull AudioBookChapterDto audioBookChapterDto, AudioBookChapter audioBookChapter) {
        audioBookChapter.setChapterNumber(audioBookChapterDto.getChapterNumber());
        audioBookChapter.setDuration(audioBookChapterDto.getDuration());
        audioBookChapter.setPartNumber(audioBookChapterDto.getPartNumber());
        return audioBookChapter;
    }


}
