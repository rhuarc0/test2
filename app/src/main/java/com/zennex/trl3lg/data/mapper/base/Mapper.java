package com.zennex.trl3lg.data.mapper.base;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Supplier;

import java.util.List;

/**
 * Created by nikita on 03.06.17.
 */

public abstract class Mapper<Source, Destination> {

    @NonNull
    private Supplier<Destination> mDestCreator;

    /**
     * @param creator интерфейс предоставляющий объект для заполения полей
     */
    public Mapper(@NonNull Supplier<Destination> creator) {
        mDestCreator = creator;
    }

    @NonNull
    protected abstract Destination transform(@NonNull Source source, Destination destination);

    @NonNull
    public List<Destination> execute(@NonNull List<Source> sourceList) {
        return Stream.of(sourceList).map(this::execute).toList();
    }

    @NonNull
    public Destination execute(@NonNull Source source) {
        return transform(source, mDestCreator.get());
    }
}