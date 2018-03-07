package com.zennex.trl3lg.data.repository.connection.local;


import com.zennex.trl3lg.data.entity.ConnectionSettings;

import io.reactivex.Observable;

/**
 * Created by Nikita on 01.05.2017.
 */

public interface IConnectionSettingsLocalRepository {

    void setIpAdress(String ipAdress);

    String getIpAddress();

    void setPort(String port);

    String getPort();

    void setConnectTimeout(String connectTimeout);

    String getConnectTimeout();

    void setRequestTimeout(String requestTimeout);

    String getRequestTimeout();

    void setResponseTimeout(String responseTimeout);

    String getResponseTimeout();

    Observable<ConnectionSettings> getConnectionSettings();

    Observable<ConnectionSettings> saveConnectionSettings(ConnectionSettings connectionSettings);

}
