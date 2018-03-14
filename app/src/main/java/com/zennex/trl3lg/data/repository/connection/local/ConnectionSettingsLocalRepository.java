package com.zennex.trl3lg.data.repository.connection.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.entity.ConnectionSettings;
import com.zennex.trl3lg.data.store.LocalRepository;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by Nikita on 01.05.2017.
 */

public class ConnectionSettingsLocalRepository extends LocalRepository implements IConnectionSettingsLocalRepository {


    @Inject
    public ConnectionSettingsLocalRepository(@NonNull Context context,
                                             @NonNull SharedPreferences sharedPreferences) {
        super(context, sharedPreferences);
    }

    @Override
    public void setIpAddress(String ipAddress) {

    }

    @Override
    public String getIpAddress() {
        return null;
    }

    @Override
    public void setPort(String port) {

    }

    @Override
    public String getPort() {
        return null;
    }

    @Override
    public void setConnectTimeout(String connectTimeout) {

    }

    @Override
    public String getConnectTimeout() {
        return null;
    }

    @Override
    public void setRequestTimeout(String requestTimeout) {

    }

    @Override
    public String getRequestTimeout() {
        return null;
    }

    @Override
    public void setResponseTimeout(String responseTimeout) {

    }

    @Override
    public String getResponseTimeout() {
        return null;
    }

    @Override
    public Observable<ConnectionSettings> getConnectionSettings() {
        return null;
    }

    @Override
    public Observable<ConnectionSettings> saveConnectionSettings(ConnectionSettings connectionSettings) {
        return null;
    }
}
