package xyz.gaborohez.socialnetwork.ui.tabbed;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import xyz.gaborohez.socialnetwork.R;

public class TabbedAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();

    public TabbedAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
