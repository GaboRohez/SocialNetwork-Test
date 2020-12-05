package xyz.gaborohez.socialnetwork.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class PreferencesManager {
    private static PreferencesManager INSTANCE = null;
    private SharedPreferences sharedPreferences;
    private static final String PREFERENCES_NAME = "cos_preferences";

    private PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static PreferencesManager getInstance() {
        return INSTANCE;
    }

    public static PreferencesManager getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PreferencesManager.class) {

                if (INSTANCE == null) {
                    INSTANCE = new PreferencesManager(context);
                }
            }
        }
        return INSTANCE;
    }

    public void saveString(String key, String value) {
        sharedPreferences.edit()
                .putString(key, value)
                .apply();
    }

    public void saveBoolean(String key, Boolean value) {
        sharedPreferences.edit()
                .putBoolean(key, value)
                .apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void removePreferences() {
        sharedPreferences.edit().clear().apply();
    }
}
