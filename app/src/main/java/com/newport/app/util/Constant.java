package com.newport.app.util;

import com.newport.app.BuildConfig;

/**
 * Created by tohure on 02/11/17.
 */

public class Constant {
    public static final String MENU_NAME_ITEM_NEWS = "tabNews";
    public static final String MENU_NAME_ITEM_EVENTS = "tabEvents";
    public static final String MENU_NAME_ITEM_QUESTIONS = "tabQuestions";
    public static final String MENU_NAME_ITEM_SCHEDULES = "tabSchedules";
    public static final String MENU_NAME_ITEM_PROFILE = "tabPerfil";

    public static final String FRAGMENT_NEWS_DETAIL = "fragmentNewDetail";
    public static final String FRAGMENT_NEWS_DETAIL_GALLERY = "fragmentNewDetailGallery";

    public static final String BASE64_HEADER = "data:image/jpeg;base64,";

    public static final int REQUEST_OBJECTS_CODE = 7;
    public static int REQUEST_GROUP_PERMISSIONS = 77;
    public static final int REQUEST_CAMERA = 777;
    public static final String EXTRA_PHOTO_ITEM = "extra_photo";
    public static final String EXTRA_PHOTO_TRANSITION_NAME = "extra_photo_name_transition";

    public static final int DNI_LENGTH= 8;

    public static final String HEADER_AUTHORIZATION = "Authorization: " + BuildConfig.HEADER;
}
