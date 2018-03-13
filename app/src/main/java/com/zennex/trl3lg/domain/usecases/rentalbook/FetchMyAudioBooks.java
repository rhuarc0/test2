package com.zennex.trl3lg.domain.usecases.rentalbook;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.domain.repository.BookRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class FetchMyAudioBooks extends UseCase<List<AudioBook>, Void> {

    @Inject
    BookRepository bookRepository;

    @Inject
    public FetchMyAudioBooks(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<AudioBook>> buildObservable(Void aVoid) {
        return bookRepository.fetchMyAudioBooks();
    }
}
