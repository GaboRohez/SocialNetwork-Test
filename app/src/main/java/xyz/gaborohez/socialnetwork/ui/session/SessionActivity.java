package xyz.gaborohez.socialnetwork.ui.session;

import android.os.Bundle;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.ui.base.BaseActivity;
import xyz.gaborohez.socialnetwork.ui.session.login.view.LogInFragment;

public class SessionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        replaceFragment(new LogInFragment(), LogInFragment.class.getName(), R.id.contentSession);
    }
}