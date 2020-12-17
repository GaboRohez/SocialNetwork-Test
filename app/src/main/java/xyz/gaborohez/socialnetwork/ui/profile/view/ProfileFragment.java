package xyz.gaborohez.socialnetwork.ui.profile.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.shape.CornerFamily;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.models.Follow;
import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;
import xyz.gaborohez.socialnetwork.databinding.FragmentProfileBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.profile.presenter.ProfileContract;
import xyz.gaborohez.socialnetwork.ui.profile.presenter.ProfilePresenter;

public class ProfileFragment extends BaseFragment<ProfileContract.Presenter, FragmentProfileBinding> implements ProfileContract.View {

    private User user;

    private static final String TAG = "ProfileFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = PreferencesManager.getInstance().getUser();
        Log.d(TAG, "onCreate: "+user.toString());
        presenter = new ProfilePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpCover();
        setUpUserInfo();
        presenter.getCounters();
    }

    private void setUpCover() {
        float radius = getResources().getDimension(R.dimen.dimen_small);
        binding.ivCover.setShapeAppearanceModel(binding.ivCover.getShapeAppearanceModel()
                .toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED,radius)
                .setTopLeftCorner(CornerFamily.ROUNDED,radius)
                .build());
    }

    @SuppressLint("StringFormatMatches")
    private void setUpUserInfo() {
        if (user.getCover() != null)
            Glide.with(getContext()).asBitmap().load(user.getImage()).into(binding.ivProfile);

        if (user.getImage() != null)
            Glide.with(getContext()).asBitmap().load(user.getImage()).into(binding.ivProfile);

        binding.tvName.setText(String.format(getString(R.string.user_name), user.getName(), user.getSurname()));
        binding.tvNickname.setText(user.getNick());
    }

    @Override
    public void setCounters(Follow result) {
        binding.postCounter.setText(String.valueOf(result.getPost()));
        binding.followingCounter.setText(String.valueOf(result.getFollowing()));
        binding.followersCounter.setText(String.valueOf(result.getFollowed()));
    }
}