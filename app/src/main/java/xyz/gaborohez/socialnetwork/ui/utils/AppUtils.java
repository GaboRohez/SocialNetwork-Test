package xyz.gaborohez.socialnetwork.ui.utils;

import android.content.Context;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;

import xyz.gaborohez.socialnetwork.constants.AppConstants;

/**
 * Created by Gabriel Rodríguez on 07/12/20.
 * Email gabow95k@gmail.com
 */
public class AppUtils {

    public static Boolean isValidEmail(String email){
        Matcher matcher = AppConstants.patternEmail.matcher(email);
        return matcher.matches();
    }

    public static String toBase64(Context context, File file){
        byte[] imBytes = CompressorBitmapImage.getImage(context, file.getPath(), 500, 500);
        return Base64.encodeToString(imBytes, Base64.DEFAULT);
    }

}
