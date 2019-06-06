package com.ontrack.ontrackriders.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ProfilePicImageUrlPref {
    public static final String USER_PROFILE_PIC_URL="profile_pic_url";

    public static SharedPreferences getPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //put profile pic url in shared pref
    public static void putUserProfilePicUrl(Context context,String userProfilePicUrl)
    {
        getPref(context).edit().putString(USER_PROFILE_PIC_URL,userProfilePicUrl).commit();
    }


    //get user profile pic url
    public static String getUserProfilePicURL(Context context) {
        return getPref(context).getString(USER_PROFILE_PIC_URL, null);
    }



}
