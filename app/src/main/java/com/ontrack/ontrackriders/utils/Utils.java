package com.ontrack.ontrackriders.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {
    /*
     * Check Network Connection
     * */
    public static boolean isOnline(Context mcontex) {
        ConnectivityManager cm = (ConnectivityManager) mcontex.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

}
