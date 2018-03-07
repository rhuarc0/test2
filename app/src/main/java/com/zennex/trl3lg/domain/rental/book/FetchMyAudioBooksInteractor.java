package com.zennex.trl3lg.domain.rental.book;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.entity.AudioBook;
import com.zennex.trl3lg.data.repository.connection.rental.book.IRentalBookRepository;
import com.zennex.trl3lg.domain.common.BaseInteractor;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by nikit on 27.08.2017.
 */

public class FetchMyAudioBooksInteractor extends BaseInteractor<List<AudioBook>, Void> {

    @Inject
    IRentalBookRepository mRentalBookRepository;


    @Inject
    public FetchMyAudioBooksInteractor(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<AudioBook>> buildObservable(Void aVoid) {
        return mRentalBookRepository.fetchMyAudioBooks();
    }
}
