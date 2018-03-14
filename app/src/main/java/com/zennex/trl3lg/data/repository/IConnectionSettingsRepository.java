package com.zennex.trl3lg.data.repository;


import com.zennex.trl3lg.data.entity.ConnectionSettings;

import io.reactivex.Observable;

/**
 * Created by nikita on 13.03.2017.
 */

public interface IConnectionSettingsRepository {

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
