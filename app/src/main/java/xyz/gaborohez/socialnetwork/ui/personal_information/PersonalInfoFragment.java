package xyz.gaborohez.socialnetwork.ui.personal_information;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.databinding.FragmentPersonalInfoBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.update_user_info.view.UpdateInfoFragment;

public class PersonalInfoFragment extends BaseFragment<BasePresenter, FragmentPersonalInfoBinding> {

    private String name;
    private String email;

    public PersonalInfoFragment(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonalInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpEvents();
        binding.tvName.setText(name);
    }

    private void setUpEvents() {
        binding.btnName.setOnClickListener(v -> addFragmentInParentActivity(new UpdateInfoFragment(name, true), UpdateInfoFragment.class.getName(), R.id.contentMain));
        binding.btnEmail.setOnClickListener(v -> addFragmentInParentActivity(new UpdateInfoFragment(email, false), UpdateInfoFragment.class.getName(), R.id.contentMain));
    }
}