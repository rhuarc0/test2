package com.zennex.trl3lg.presentation.mapper.rx;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.entity.rest.response.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;
import com.zennex.trl3lg.presentation.mapper.FieldModelMapper;
import com.zennex.trl3lg.presentation.model.FieldModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by nikita on 24.06.17.
 */

public class FieldModelsMapperInteractor extends UseCase<List<FieldModel>, List<GetFieldsForSignUpResponse.DataMemberField>> {

    @Inject
    FieldModelMapper mFieldModelMapper;

    @Inject
    public FieldModelsMapperInteractor(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<FieldModel>> buildObservable(List<GetFieldsForSignUpResponse.DataMemberField> dataMemberFields) {
        return Observable.fromCallable(() -> mFieldModelMapper.execute(dataMemberFields));
    }
}
