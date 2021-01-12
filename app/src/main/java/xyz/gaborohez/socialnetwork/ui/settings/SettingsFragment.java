package xyz.gaborohez.socialnetwork.ui.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.constants.AppConstants;
import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;
import xyz.gaborohez.socialnetwork.databinding.FragmentSettingsBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.edit_profile.view.EditProfileFragment;
import xyz.gaborohez.socialnetwork.ui.profile.view.ProfileFragment;
import xyz.gaborohez.socialnetwork.ui.session.SessionActivity;
import xyz.gaborohez.socialnetwork.ui.utils.AppUtils;
import xyz.gaborohez.socialnetwork.ui.utils.FileUtil;

import static xyz.gaborohez.socialnetwork.constants.AppConstants.CAMERA_REQUEST_CODE;
import static xyz.gaborohez.socialnetwork.constants.AppConstants.EDIT_SUCCESS_CODE;
import static xyz.gaborohez.socialnetwork.constants.AppConstants.GALLERY_REQUEST_CODE;
import static xyz.gaborohez.socialnetwork.constants.AppConstants.POST_SUCCESS_CODE;
import static xyz.gaborohez.socialnetwork.constants.AppConstants.PROFILE_SUCCESS_CODE;

public class SettingsFragment extends BaseFragment implements FragmentManager.OnBackStackChangedListener {

    private static final String TAG = "SettingsFragment";
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

        setUpEvents();
        getActivity().getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    private void setUpUserInfo() {
        User user = PreferencesManager.getInstance().getUser();
        binding.tvName.setText(user.getName());
        if (user.getImage() != null)
            Glide.with(getContext()).asBitmap().load(user.getImage()).into(binding.ivProfile);

    }

    private void setUpEvents(){

        binding.btnProfile.setOnClickListener(v -> {
            ProfileFragment fragment = new ProfileFragment();
            fragment.setTargetFragment(this, PROFILE_SUCCESS_CODE);
            addFragmentInParentActivity(fragment, ProfileFragment.class.getName(), R.id.contentMain);
        });

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

    @Override
    public void onResume() {
        super.onResume();
        setUpUserInfo();
    }

    @Override
    public void onBackStackChanged() {
        int countStack = getActivity().getSupportFragmentManager().getBackStackEntryCount();
        if (countStack == 0)
            setUpUserInfo();
    }
}