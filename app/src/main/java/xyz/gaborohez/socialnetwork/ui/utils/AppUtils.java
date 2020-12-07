package xyz.gaborohez.socialnetwork.ui.utils;

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
}
