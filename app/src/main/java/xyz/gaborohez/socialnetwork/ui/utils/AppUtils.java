package xyz.gaborohez.socialnetwork.ui.utils;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.app.SocialApp;
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

    public static String getElapsedTime(String dateAt) {
        Date date = new Date(Long.parseLong(dateAt)*1000L);

        long diff = date.getTime() - new Date().getTime();

        long segsMilli = 1000;
        long minsMilli = segsMilli * 60;
        long hoursMilli = minsMilli * 60;
        long daysMilli = hoursMilli * 24;

        long daysElapsed = (diff / daysMilli) * -1;
        diff = diff % daysMilli;

        long hoursElapsed = (diff / hoursMilli) * -1;
        diff = diff % hoursMilli;

        long minElapsed = (diff / minsMilli) * -1;
        diff = diff % minsMilli;

        long secondsElapsed = (diff / segsMilli) * -1;

        if (daysElapsed != 0)
            return daysElapsed+"d";
        else if (hoursElapsed != 0)
            return hoursElapsed+"h";
        else if (minElapsed != 0)
            return minElapsed+"m";
        else
            return SocialApp.resourcesManager.getJustNow();
    }
}
