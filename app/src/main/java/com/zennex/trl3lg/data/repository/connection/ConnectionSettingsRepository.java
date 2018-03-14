package com.zennex.trl3lg.data.repository.connection;


import com.zennex.trl3lg.data.entity.ConnectionSettings;
import com.zennex.trl3lg.data.repository.connection.local.IConnectionSettingsLocalRepository;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by nikita on 13.03.2017.
 */

public class ConnectionSettingsRepository implements IConnectionSettingsRepository {


    @Inject
    protected IConnectionSettingsLocalRepository mConnectionSettingsLocalRepository;

    @Inject
    public ConnectionSettingsRepository() {
    }


    @Override
    public void setIpAdress(String ipAdress) {
        mConnectionSettingsLocalRepository.setIpAddress(ipAdress);
    }

    @Override
    public String getIpAddress() {
        return mConnectionSettingsLocalRepository.getIpAddress();
    }

    @Override
    public void setPort(String port) {
        mConnectionSettingsLocalRepository.setPort(port);
    }

    @Override
    public String getPort() {
        return mConnectionSettingsLocalRepository.getPort();
    }

    @Override
    public void setConnectTimeout(String connectTimeout) {
        mConnectionSettingsLocalRepository.setConnectTimeout(connectTimeout);
    }

    @Override
    public String getConnectTimeout() {
        return mConnectionSettingsLocalRepository.getConnectTimeout();
    }

    @Override
    public void setRequestTimeout(String requestTimeout) {
        mConnectionSettingsLocalRepository.setRequestTimeout(requestTimeout);
    }

    @Override
    public String getRequestTimeout() {
        return mConnectionSettingsLocalRepository.getRequestTimeout();
    }

    @Override
    public void setResponseTimeout(String responseTimeout) {
        mConnectionSettingsLocalRepository.setResponseTimeout(responseTimeout);
    }

    @Override
    public String getResponseTimeout() {
        return mConnectionSettingsLocalRepository.getResponseTimeout();
    }

    @Override
    public Observable<ConnectionSettings> getConnectionSettings() {
        return mConnectionSettingsLocalRepository.getConnectionSettings();
    }

    @Override
    public Observable<ConnectionSettings> saveConnectionSettings(ConnectionSettings connectionSettings) {
        return mConnectionSettingsLocalRepository.saveConnectionSettings(connectionSettings);
    }
}
