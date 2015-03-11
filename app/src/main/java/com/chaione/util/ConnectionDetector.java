package com.chaione.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by niliman on 1/20/2015.
 */
public class ConnectionDetector {

    /**
     * Context context.
     */
    private Context context;

    public ConnectionDetector(Context context) {
        this.context = context;
    }

    /**
     * A simple Method for Internet Connection.
     */
    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }
}
