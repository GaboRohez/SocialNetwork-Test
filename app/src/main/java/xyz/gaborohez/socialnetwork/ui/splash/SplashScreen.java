package xyz.gaborohez.socialnetwork.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import xyz.gaborohez.socialnetwork.ui.base.BaseActivity;
import xyz.gaborohez.socialnetwork.ui.session.SessionActivity;

public class SplashScreen extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            // Using handler with postDelayed called runnable run method
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, SessionActivity.class));
                finish();
            }
        }, 2000); // wait for 2 seconds
    }
}
