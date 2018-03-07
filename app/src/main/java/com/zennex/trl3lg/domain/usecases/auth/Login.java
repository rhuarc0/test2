package com.zennex.trl3lg.domain.usecases.auth;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.entity.AuthData;
import com.zennex.trl3lg.domain.entities.Module;
import com.zennex.trl3lg.domain.entities.Site;
import com.zennex.trl3lg.data.entity.rest.request.GetSitesRequest;
import com.zennex.trl3lg.data.entity.rest.request.LoginRequest;
import com.zennex.trl3lg.data.entity.rest.response.AuthResponse;
import com.zennex.trl3lg.data.mapper.AuthDataDtoMapper;
import com.zennex.trl3lg.data.repository.connection.auth.IAuthRepository;
import com.zennex.trl3lg.data.repository.connection.site.ISiteRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;
import com.zennex.trl3lg.presentation.helper.StringUtils;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class Login extends UseCase<AuthData, Login.Params> {

    private static final String TAG = Login.class.getSimpleName();

    @Inject
    protected IAuthRepository mAuthRepository;

    @Inject
    protected AuthDataDtoMapper mAuthDataDtoMapper;

    @Inject
    protected ISiteRepository mSiteRepository;

    private List<String> mModuleIdsFromLogin2;


    @Inject
    public Login(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }


    @Override
    protected Observable<AuthData> buildObservable(Params params) {
        return mAuthRepository.auth(createLoginRequest(params))
                .doOnNext(checkResponse())
                .doOnNext(authResponse -> cachedMembershipId(TextUtils.isEmpty(params.getModuleId()) ?
                        authResponse.getMemberLoginResponse().getModuleId() : params.mModuleId))
                .doOnNext(authResponse -> cachedSessionToken(authResponse.getMemberLoginResponse().getSession()))
                .doOnNext(authResponse -> cacheSiteIdAndRentalModuleIds(TextUtils.isEmpty(params.getModuleId()) ?
                        authResponse.getMemberLoginResponse().getModuleId() : params.mModuleId, params.mSites))
                .doOnNext(handleData())
                .map(transform());
    }

    public List<String> getModuleIdsFromLogin2() {
        return mModuleIdsFromLogin2;
    }

    private LoginRequest createLoginRequest(Params params) {
        return LoginRequest.newInstance(
                new LoginRequest.Data(params.mEmail, params.mPassword),
                params.mModuleId);
    }

    private Consumer<AuthResponse> checkResponse() {
        return getSitesResponse -> {

            if (Integer.parseInt(getSitesResponse.getErrorCode()) != 0) {
                throw new RuntimeException(getSitesResponse.getErrorText());
            }
        };
    }

    private Function<AuthResponse, AuthData> transform() {
        return authResponse -> mAuthDataDtoMapper.execute(authResponse.getMemberLoginResponse());
    }

    private void cachedSessionToken(@Nullable String sessionToken) {
        mAuthRepository.saveSessionToken(sessionToken).subscribe();
    }


    private void cachedMembershipId(String moduleId) {
        mAuthRepository.saveModuleId(moduleId).subscribe();
    }


    private void cacheSiteIdAndRentalModuleIds(@Nullable String moduleId, List<Site> sites) {
        if (!StringUtils.isNullOrEmpty(moduleId)) {
            Site site = Stream.of(sites)
                    .filter(s -> Stream.of(s.getModules())
                            .map(module -> module.getType().equals(GetSitesRequest.Data.TYPE_MEMBERSHIP) &&
                                    module.getId().equals(moduleId)).reduce((r, i) -> r || i).get()).single();

            if (site != null) {

                mSiteRepository.saveSiteId(site.getId()).subscribe();
                Set<String> rentalModules = Stream.of(site.getModules())
                        .filter(module -> module.getType().equals(GetSitesRequest.Data.TYPE_RENTAL)
                                && module.getMembershipId().equals(moduleId))
                        .map(Module::getId)
                        .collect(Collectors.toSet());

                if (rentalModules != null) {
                    mAuthRepository.saveRentalModuleIds(rentalModules).subscribe();
                }


            }
        }
    }

    private Consumer<AuthResponse> handleData() {
        return authResponse -> {
            if (authResponse.getMemberLoginResponse().getModuleIds() != null) {
                mModuleIdsFromLogin2 = authResponse.getMemberLoginResponse().getModuleIds();
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
