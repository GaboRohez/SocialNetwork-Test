package xyz.gaborohez.socialnetwork.constants;

import java.util.regex.Pattern;

public class AppConstants {
    //  request code
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int GALLERY_REQUEST_CODE = 200;
    public static final int POST_SUCCESS_CODE = 001;

    //  response code
    public static final int EXPIRED = 401;
    public static final String SUCCESS = "00";

    //  shared preferences
    public static String isLogged = "isLogged";
    public static final String KEY_TOKEN = "key_token";

    //  retrofit header
    public static final String Authorization = "Authorization";

    //  url's
    public static final String BASE_URL = "http://10.0.2.2:3800/api/";  //emulator
    //public static final String BASE_URL = "http://192.168.100.12:3800/api/";
    public static final String BASE_IMAGE_URL = "http://192.168.100.12:3800/api/uploads/users/";

    //  regex
    public static final Pattern patternEmail = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

}
