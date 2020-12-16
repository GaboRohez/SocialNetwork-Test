package xyz.gaborohez.socialnetwork.ui.settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.constants.AppConstants;
import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;
import xyz.gaborohez.socialnetwork.databinding.FragmentSettingsBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.session.SessionActivity;

public class SettingsFragment extends BaseFragment {

    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpUserInfo();
        setUpEvents();
    }

    private void setUpUserInfo() {
        User user = PreferencesManager.getInstance().getUser();
        binding.tvName.setText(user.getName());
        if (user.getImage() != null)
            Glide.with(getContext()).load(AppConstants.BASE_IMAGE_URL+user.getImage()).into(binding.ivProfile);

    }

    private void setUpEvents(){
        binding.btnLogOut.setOnClickListener(v -> {

            new MaterialAlertDialogBuilder(getContext())
                    .setTitle(getString(R.string.title_log_out))
                    .setMessage(getString(R.string.body_log_out))
                    .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                        PreferencesManager.getInstance().removePreferences();
                        startActivity(new Intent(getActivity(), SessionActivity.class));
                        getActivity().finish();
                    })
                    .setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }
}