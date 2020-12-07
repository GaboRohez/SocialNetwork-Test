package xyz.gaborohez.socialnetwork.app;

import android.app.Application;
import android.content.res.Resources;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;

/**
 * Created by Gabriel Rodríguez on 07/12/20.
 * Email gabow95k@gmail.com
 */
public class SocialApp extends Application {

    public static ResourcesManager resourcesManager;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesManager.getInstance(this);
        resourcesManager = new ResourcesManager(getResources());
    }

    public static class ResourcesManager {
        private Resources resources;

        public ResourcesManager(Resources resources) {
            this.resources = resources;
        }

        public String getErrorServer() {
            return resources.getString(R.string.server_error);
        }


        public String getEmailErrorMessage() {
            return resources.getString(R.string.email_error_message);
        }

        public String getPasswordError() {
            return resources.getString(R.string.password_error_message);
        }
    }
}
