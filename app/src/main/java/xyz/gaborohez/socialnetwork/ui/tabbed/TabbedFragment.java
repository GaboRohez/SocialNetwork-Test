package xyz.gaborohez.socialnetwork.ui.tabbed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.data.network.RetrofitClient;
import xyz.gaborohez.socialnetwork.data.network.SocialNetworkAPI;
import xyz.gaborohez.socialnetwork.databinding.FragmentTabbedBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.people.PeopleFragment;
import xyz.gaborohez.socialnetwork.ui.settings.SettingsFragment;

import static xyz.gaborohez.socialnetwork.constants.AppConstants.SUCCESS;

public class TabbedFragment extends BaseFragment<BasePresenter, FragmentTabbedBinding> {

    private static final String TAG = "TabbedFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTabbedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPeople();
    }

    private void getPeople() {
        List<User> peopleList = new ArrayList<>();

        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(RetrofitClient.singleAPI()
                .create(SocialNetworkAPI.class)
                .getPeople()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.getCode().equals(SUCCESS)){
                        peopleList.addAll(response.getUsers());
                        setUpTabLayout(peopleList);
                    }else  {
                        setUpTabLayout(peopleList);
                        Log.d(TAG, "getUsers: "+response.getMessage());
                    }
                }, throwable -> {
                    setUpTabLayout(peopleList);
                    Log.d(TAG, "getUsers: "+throwable.getMessage());
                }));
    }

    private void setUpTabLayout(List<User> peopleList) {
        //Add tabs icon with setIcon() or simple text with .setText()
        binding.tabs.addTab(binding.tabs.newTab().setIcon(R.drawable.ic_people));
        binding.tabs.addTab(binding.tabs.newTab().setIcon(R.drawable.ic_menu_settings));

        //Add fragments
        TabbedAdapter adapter = new TabbedAdapter(getFragmentManager(), 2);
        adapter.addFragment(new PeopleFragment(peopleList));
        adapter.addFragment(new SettingsFragment());

        //Setting adapter
        binding.viewpager.setAdapter(adapter);
        binding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
        binding.tabs.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.viewpager));
    }

}