package com.zennex.trl3lg.domain.usecases.rentalbook;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.zennex.trl3lg.domain.repository.IBookRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;


public class FetchQueueIdsAndHistory extends UseCase<Pair<List<String>, List<String>>, Void> {

    @Inject
    IBookRepository bookRepository;

    @Inject
    public FetchQueueIdsAndHistory(@Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
                                   @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<Pair<List<String>, List<String>>> buildObservable(Void aVoid) {
        return Observable.zip(bookRepository.fetchQueueBookIds(),
                              bookRepository.fetchHistory(),
                              this::zipResults);
    }

    private Pair<List<String>, List<String>> zipResults(List<String> queueIds, List<String> history) {
        return Pair.create(queueIds, history);
    }
}
