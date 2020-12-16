package xyz.gaborohez.socialnetwork.ui.tabbed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.databinding.FragmentTabbedBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.profile.ProfileFragment;
import xyz.gaborohez.socialnetwork.ui.settings.SettingsFragment;

public class TabbedFragment extends BaseFragment<BasePresenter, FragmentTabbedBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTabbedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpTabLayout();
    }

    private void setUpTabLayout() {
        //Add tabs icon with setIcon() or simple text with .setText()
        binding.tabs.addTab(binding.tabs.newTab().setIcon(R.drawable.ic_menu_settings));
        binding.tabs.addTab(binding.tabs.newTab().setIcon(R.drawable.ic_menu_settings));

        //Add fragments
        TabbedAdapter adapter = new TabbedAdapter(getFragmentManager(), 2);
        adapter.addFragment(new ProfileFragment());
        adapter.addFragment(new SettingsFragment());

        //Setting adapter
        binding.viewpager.setAdapter(adapter);
        binding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
        binding.tabs.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.viewpager));
    }

}