package com.newport.app.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tohure on 11/11/17.
 */

public class PreferencesHeper {

    private static final String PREF_NAME = "NewPort";

    private static final String KEY_WIDTH_SYSTEM_PIXEL = "system_widthPixels";
    private static final String KEY_HEIGHT_SYSTEM_PIXEL = "system_heightPixels";
    private static final String KEY_DNI_CURRENT = "system_dniuser";
    private static final String KEY_SAP_CODE_CURRENT = "system_sap_code";

    private static final String KEY_LAST_FRAGMENT = "system_lastfragment";
    private static final String KEY_CURRENT_FRAGMENT = "system_currentfragment";

    private static final String KEY_TYPE_TAB = "system_typetab";

    private static final String KEY_STATUS_SCROLL = "system_scroll_profile";

    private static final String KEY_DAY_EXPIRATION = "user_day_expiration";
    private static final String KEY_MONTH_EXPIRATION = "user_month_expiration";
    private static final String KEY_YEAR_EXPIRATION = "user_year_expiration";

    private static final String KEY_DEVICE_TOKEN = "device_token";

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static void initSessionManager(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static void setWidthSystem(Context context, int width) {
        initSessionManager(context);
        editor.putInt(KEY_WIDTH_SYSTEM_PIXEL, width);
        editor.commit();
    }

    public static int getWidthSystem(Context context) {
        initSessionManager(context);
        return sharedPreferences.getInt(KEY_WIDTH_SYSTEM_PIXEL, 0);
    }

    public static void setHeightSystem(Context context, int height) {
        initSessionManager(context);
        editor.putInt(KEY_HEIGHT_SYSTEM_PIXEL, height);
        editor.commit();
    }

    public static int getHeightSystem(Context context) {
        initSessionManager(context);
        return sharedPreferences.getInt(KEY_HEIGHT_SYSTEM_PIXEL, 0);
    }

    public static void setDniUser(Context context, String dni) {
        initSessionManager(context);
        editor.putString(KEY_DNI_CURRENT, dni);
        editor.commit();
    }

    public static String getDniUser(Context context) {
        initSessionManager(context);
        return sharedPreferences.getString(KEY_DNI_CURRENT, "");
    }

    //Last and Current fragment
    public static void setLastFragmentTag(Context context, String tag) {
        initSessionManager(context);
        editor.putString(KEY_LAST_FRAGMENT, tag);
        editor.commit();
    }

    public static String getLastFragmentTag(Context context) {
        initSessionManager(context);
        return sharedPreferences.getString(KEY_LAST_FRAGMENT, "");
    }

    public static void setCurrentFragmentTag(Context context, String tag) {
        initSessionManager(context);
        editor.putString(KEY_CURRENT_FRAGMENT, tag);
        editor.commit();
    }

    public static String getCurrentFragmentTag(Context context) {
        initSessionManager(context);
        return sharedPreferences.getString(KEY_CURRENT_FRAGMENT, "");
    }

    public static void setTypeTab(Context context, int tab) {
        initSessionManager(context);
        editor.putInt(KEY_TYPE_TAB, tab);
        editor.commit();
    }

    public static int getTypeTab(Context context) {
        initSessionManager(context);
        return sharedPreferences.getInt(KEY_TYPE_TAB, 0);
    }

    //Scroll Status
    public static void setScrollProfileStatus(Context context, boolean status) {
        initSessionManager(context);
        editor.putBoolean(KEY_STATUS_SCROLL, status);
        editor.commit();
    }

    public static boolean getScrollProfileStatus(Context context) {
        initSessionManager(context);
        return sharedPreferences.getBoolean(KEY_STATUS_SCROLL, false);
    }

    //Sap Code user
    public static void setSapCodeUser(Context context, String sapCode) {
        initSessionManager(context);
        editor.putString(KEY_SAP_CODE_CURRENT, sapCode);
        editor.commit();
    }

    public static String getSapCodeUser(Context context) {
        initSessionManager(context);
        return sharedPreferences.getString(KEY_SAP_CODE_CURRENT, "");
    }


    //Carnet de Sanidad Vencimiento
    public static void setDayExpiration(Context context, int day) {
        initSessionManager(context);
        editor.putInt(KEY_DAY_EXPIRATION, day);
        editor.commit();
    }

    public static int getDayExpiration(Context context) {
        initSessionManager(context);
        return sharedPreferences.getInt(KEY_DAY_EXPIRATION, 0);
    }

    public static void setMonthExpiration(Context context, int month) {
        initSessionManager(context);
        editor.putInt(KEY_MONTH_EXPIRATION, month);
        editor.commit();
    }

    public static int getMonthExpiration(Context context) {
        initSessionManager(context);
        return sharedPreferences.getInt(KEY_MONTH_EXPIRATION, 0);
    }

    public static void setYearExpiration(Context context, int year) {
        initSessionManager(context);
        editor.putInt(KEY_YEAR_EXPIRATION, year);
        editor.commit();
    }

    public static int getYearExpiration(Context context) {
        initSessionManager(context);
        return sharedPreferences.getInt(KEY_YEAR_EXPIRATION, 0);
    }

    //Token del dispositivo
    public static void setKeyDeviceToken(Context context, String token){
        initSessionManager(context);
        editor.putString(KEY_DEVICE_TOKEN, token);
        editor.commit();
    }

    public static String getKeyDeviceToken(Context context){
        initSessionManager(context);
        return sharedPreferences.getString(KEY_DEVICE_TOKEN, "");
    }

}
