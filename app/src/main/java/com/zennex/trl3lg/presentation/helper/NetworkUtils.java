package com.zennex.trl3lg.presentation.helper;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Nikita on 13.04.2017.
 */

public class NetworkUtils {


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}
