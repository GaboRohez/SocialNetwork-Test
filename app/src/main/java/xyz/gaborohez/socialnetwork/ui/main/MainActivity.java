package xyz.gaborohez.socialnetwork.ui.main;

import android.os.Bundle;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.ui.base.BaseActivity;
import xyz.gaborohez.socialnetwork.ui.tabbed.TabbedFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        replaceFragment(new TabbedFragment(), TabbedFragment.class.getName(), R.id.contentMain);
    }
}