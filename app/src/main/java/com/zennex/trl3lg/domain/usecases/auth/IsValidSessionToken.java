package com.zennex.trl3lg.domain.usecases.auth;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.rest.request.auth.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.rest.response.auth.IsValidSessionTokenResponse;
import com.zennex.trl3lg.data.repository.connection.auth.IAuthRepository;
import com.zennex.trl3lg.data.repository.connection.site.ISiteRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;
import com.zennex.trl3lg.presentation.helper.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class IsValidSessionToken extends UseCase<Boolean, Void> {

    @Inject
    IAuthRepository mAuthRepository;

    @Inject
    ISiteRepository mSiteRepository;

    @Inject
    public IsValidSessionToken(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<Boolean> buildObservable(Void aVoid) {
        return Observable.fromCallable(() -> {
                String membershipId = mAuthRepository.getModuleId().blockingSingle();
                String sessionToken = mAuthRepository.getSessionToken().blockingSingle();
                String siteId = mSiteRepository.getSiteId().blockingSingle();

                if (!StringUtils.isNullOrEmpty(membershipId) && !StringUtils.isNullOrEmpty(sessionToken) && !StringUtils.isNullOrEmpty(sessionToken)) {
                    return new String[]{membershipId, siteId, sessionToken};
                } else {
                    return new String[0];
                }
            })
            .flatMap(array -> array.length == 3 ?
                    mAuthRepository.isValidSessionToken(IsValidSessionTokenRequest.newInstance(array[0], array[1], array[2]))
                        .map(response -> response.getData() == IsValidSessionTokenResponse.SESSION_VALID):
                    Observable.just(false));
    }
}
