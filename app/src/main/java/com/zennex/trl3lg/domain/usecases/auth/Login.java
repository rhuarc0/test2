package com.zennex.trl3lg.domain.usecases.auth;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zennex.trl3lg.domain.entities.AuthData;
import com.zennex.trl3lg.domain.entities.Site;
import com.zennex.trl3lg.domain.repository.IAuthRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;

public class Login extends UseCase<AuthData, Login.Params> {

    private static final String TAG = Login.class.getSimpleName();

    @Inject
    protected IAuthRepository authRepository;

    private List<String> mModuleIdsFromLogin2;


    @Inject
    public Login(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }


    @Override
    protected Observable<AuthData> buildObservable(Params params) {
        return authRepository.auth(params.getEmail(),
                                    params.getPassword(),
                                    params.getModuleId(),
                                    params.getSites())
                .doOnNext(handleData());
    }

    public List<String> getModuleIdsFromLogin2() {
        return mModuleIdsFromLogin2;
    }

    private Consumer<AuthData> handleData() {
        return authData -> {
            if (authData.getModuleIds() != null) {
                mModuleIdsFromLogin2 = authData.getModuleIds();
            }
        };
    }

    public static class Params {

        @NonNull
        private final String mEmail;
        @NonNull
        private final String mPassword;
        @Nullable
        private final String mModuleId;
        @Nullable
        private final List<Site> mSites;


        public Params(@NonNull String email,
                      @NonNull String password,
                      @Nullable String moduleId,
                      @Nullable List<Site> sites) {
            mEmail = email;
            mPassword = password;
            mModuleId = moduleId;
            mSites = sites;
        }

        @NonNull
        public String getEmail() {
            return mEmail;
        }

        @NonNull
        public String getPassword() {
            return mPassword;
        }

        @Nullable
        public String getModuleId() {
            return mModuleId;
        }


        @Nullable
        public List<Site> getSites() {
            return mSites;
        }
    }

}
