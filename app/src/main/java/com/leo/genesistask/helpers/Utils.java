package com.leo.genesistask.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by leonid on 2/12/17.
 */

public class Utils {

    /**
     * Returns true if network is available or about to become available
     *
     * @param context Context used to get ConnectivityManager
     * @return true if device is online
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwor = cm.getActiveNetworkInfo();
        return (activeNetwor != null && activeNetwor.isConnectedOrConnecting());
    }

}
