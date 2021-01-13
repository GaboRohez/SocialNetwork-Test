package xyz.gaborohez.socialnetwork.ui.people;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.databinding.FragmentPeopleBinding;
import xyz.gaborohez.socialnetwork.ui.adapter.PeopleAdapter;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;

public class PeopleFragment extends BaseFragment<BasePresenter, FragmentPeopleBinding> implements PeopleAdapter.PeopleIn {

    private static final String TAG = "PeopleFragment";

    private List<User> list;

    public PeopleFragment(List<User> list) {
        this.list = list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPeopleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPeople();
    }

    private void setPeople() {

        PeopleAdapter adapter = new PeopleAdapter( list, requireContext(), this);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setNestedScrollingEnabled(false);
        binding.recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /*if (binding.swipe.isRefreshing())
            binding.swipe.setRefreshing(false);*/
    }

    @Override
    public void showProfile(User user) {
        Log.d(TAG, "showProfile: "+user.toString());
    }
}