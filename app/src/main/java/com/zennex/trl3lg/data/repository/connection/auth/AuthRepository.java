package com.zennex.trl3lg.data.repository.connection.auth;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.datasource.site.ISiteDataSourceLocal;
import com.zennex.trl3lg.data.mapper.dtomapper.AuthDataDtoMapper;
import com.zennex.trl3lg.data.rest.request.auth.GetSitesRequest;
import com.zennex.trl3lg.domain.entities.AuthData;
import com.zennex.trl3lg.data.datasource.auth.IAuthDataSourceLocal;
import com.zennex.trl3lg.data.datasource.auth.IAuthDataSourceRemote;
import com.zennex.trl3lg.domain.entities.Module;
import com.zennex.trl3lg.domain.entities.Site;
import com.zennex.trl3lg.domain.repository.IAuthRepository;
import com.zennex.trl3lg.data.rest.request.auth.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.rest.request.auth.LoginRequest;
import com.zennex.trl3lg.data.rest.response.auth.AuthResponse;
import com.zennex.trl3lg.data.rest.response.auth.IsValidSessionTokenResponse;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by nikita on 02.06.17.
 */

public class AuthRepository implements IAuthRepository {

    private static final int NONE_ERROR = 0;
    @Inject
    protected IAuthDataSourceLocal authDataSourceLocal;

    @Inject
    protected IAuthDataSourceRemote authDataSourceRemote;

    @Inject
    protected AuthDataDtoMapper authDataDtoMapper;

    @Inject
    protected ISiteDataSourceLocal siteDataSourceLocal;


    @Inject
    public AuthRepository() {

    }

    @Override
    public Observable<AuthData> auth(String email, String password, String moduleId, List<Site> sites) {
        return authDataSourceRemote.auth(createLoginRequest(email, password, moduleId))
                .doOnNext(checkResponse())
                .doOnNext(authResponse -> {
                    String moduleIdUsed = TextUtils.isEmpty(moduleId) ?
                            authResponse.getMemberLoginResponse().getModuleId() :
                            moduleId;
                    cachedMembershipId(moduleIdUsed);
                    cachedSessionToken(authResponse.getMemberLoginResponse().getSession());
                    cacheSiteIdAndRentalModuleIds(moduleIdUsed, sites);
                })
                .map(transform());
    }

    @Override
    public Observable<Boolean> saveSessionToken(String sessionToken) {
        return authDataSourceLocal.saveSessionToken(sessionToken);
    }

    @Override
    public Observable<Boolean> saveModuleId(String moduleId) {
        return authDataSourceLocal.saveModuleId(moduleId);
    }

    @Override
    public Observable<String> getSessionToken() {
        return authDataSourceLocal.getSessionToken();
    }

    @Override
    public Observable<String> getModuleId() {
        return authDataSourceLocal.getModuleId();
    }

    @Override
    public Observable<Boolean> saveRentalModuleIds(Set<String> moduleIds) {
        return authDataSourceLocal.saveRentalModuleIds(moduleIds);
    }

    @Override
    public Observable<Set<String>> getRentalModuleIds() {
        return authDataSourceLocal.getRentalModuleIds();
    }

    @Override
    public Observable<Boolean> isValidSessionToken() {
        String membershipId = getModuleId().blockingSingle();
        String sessionToken = getSessionToken().blockingSingle();
        String siteId = siteDataSourceLocal.getSiteId().blockingSingle();

        if (!TextUtils.isEmpty(membershipId) &&
                !TextUtils.isEmpty(siteId) &&
                !TextUtils.isEmpty(sessionToken)) {

            IsValidSessionTokenRequest request =
                    IsValidSessionTokenRequest.newInstance(membershipId, siteId, sessionToken);

            return authDataSourceRemote.isValidSessionToken(request)
                    .map(response -> response.getData() == IsValidSessionTokenResponse.SESSION_VALID);
        } else {
            return Observable.just(false);
        }
    }

    // aux methods

    private LoginRequest createLoginRequest(String email, String password, String moduleId) {
        LoginRequest.Data data = new LoginRequest.Data(email, password);
        return LoginRequest.newInstance(data, moduleId);
    }

    private Consumer<AuthResponse> checkResponse() {
        return getSitesResponse -> {

            if (Integer.parseInt(getSitesResponse.getErrorCode()) != NONE_ERROR) {
                throw new RuntimeException(getSitesResponse.getErrorText());
            }
        };
    }

    private Function<AuthResponse, AuthData> transform() {
        return authResponse -> authDataDtoMapper.execute(authResponse.getMemberLoginResponse());
    }

    private void cachedSessionToken(@Nullable String sessionToken) {
        saveSessionToken(sessionToken).subscribe();
    }


    private void cachedMembershipId(String moduleId) {
        saveModuleId(moduleId).subscribe();
    }


    private void cacheSiteIdAndRentalModuleIds(@Nullable String moduleId, List<Site> sites) {
        if (!TextUtils.isEmpty(moduleId)) {
            Site site = Stream.of(sites)
                    .filter(s -> Stream.of(s.getModules())
                            .map(module -> module.getType().equals(GetSitesRequest.Data.TYPE_MEMBERSHIP) &&
                                    module.getId().equals(moduleId)).reduce((r, i) -> r || i).get()).single();

            if (site != null) {

                siteDataSourceLocal.saveSiteId(site.getId()).subscribe();
                Set<String> rentalModules = Stream.of(site.getModules())
                        .filter(module -> module.getType().equals(GetSitesRequest.Data.TYPE_RENTAL)
                                && module.getMembershipId().equals(moduleId))
                        .map(Module::getId)
                        .collect(Collectors.toSet());

                if (rentalModules != null) {
                    saveRentalModuleIds(rentalModules).subscribe();
                }


            }
        }
    }
}
