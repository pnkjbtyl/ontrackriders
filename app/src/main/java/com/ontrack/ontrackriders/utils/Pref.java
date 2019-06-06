package com.ontrack.ontrackriders.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Pref {
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_TOKEN = "user_token";
    public static final String USER_REFRESH_TOKEN = "user_refresh_token";
    public static final String USER_PROFILE_PIC_URL="profile_pic_url";


    public static SharedPreferences getPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //put username no in shared pref
        public static void putUserName(Context context,String userName)
    {
        getPref(context).edit().putString(USER_NAME,userName).commit();
    }
    //put username no in shared pref
    public static void putUserEmail(Context context,String userEmail)
    {
        getPref(context).edit().putString(USER_EMAIL,userEmail).commit();
    }

    //put profile pic url in shared pref
    public static void putUserProfilePicUrl(Context context,String userProfilePicUrl)
    {
        getPref(context).edit().putString(USER_PROFILE_PIC_URL,userProfilePicUrl).commit();
    }
    //put username no in shared pref
    public static void putToken(Context context,String token)
    {
        getPref(context).edit().putString(USER_TOKEN,token).commit();
    }
    //put username no in shared pref
    public static void putRefreshToken(Context context,String refreshToken)
    {
        getPref(context).edit().putString(USER_REFRESH_TOKEN,refreshToken).commit();
    }

    public static void removeRefreshToken(Context context)
    {
        getPref(context).edit().remove(USER_REFRESH_TOKEN).commit();
    }
    public static void removeToken(Context context)
    {
        getPref(context).edit().remove(USER_TOKEN).commit();
    }

    //get user profile pic url
    public static String getUserProfilePicURL(Context context) {
        return getPref(context).getString(USER_PROFILE_PIC_URL, null);
    }

      //get user phone number
    public static String getUserName(Context context) {
        return getPref(context).getString(USER_NAME, null);
    }
    //get user email
    public static String getUserEmail(Context context) {
        return getPref(context).getString(USER_EMAIL, null);
    }   //get user token
    public static String getUserToken(Context context) {
        return getPref(context).getString(USER_TOKEN, null);
    }   //get user refresh token
    public static String getUserRefreshToken(Context context) {
        return getPref(context).getString(USER_REFRESH_TOKEN, null);
    }

     //clear preference after logout
    public static void clearPref(Context context) {
        getPref(context).edit().clear().commit();
    }

}
