package xyz.gaborohez.socialnetwork.ui.profile.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.shape.CornerFamily;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import xyz.gaborohez.socialnetwork.BuildConfig;
import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.models.Follow;
import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;
import xyz.gaborohez.socialnetwork.databinding.FragmentProfileBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.profile.presenter.ProfileContract;
import xyz.gaborohez.socialnetwork.ui.profile.presenter.ProfilePresenter;
import xyz.gaborohez.socialnetwork.ui.utils.AppUtils;
import xyz.gaborohez.socialnetwork.ui.utils.FileUtil;

public class ProfileFragment extends BaseFragment<ProfileContract.Presenter, FragmentProfileBinding> implements ProfileContract.View {

    private User user;
    private boolean isProfile;
    private String imageCover;
    private String imageProfile;

    private String mAbsolutePhotoPath;
    private int CAMERA_REQUEST_CODE = 100;
    private int GALLERY_REQUEST_CODE = 200;

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
        setUpEvents();
        setUpCover();
        setUpUserInfo();
        presenter.getCounters();
    }

    private void setUpEvents() {

        binding.btnProfile.setOnClickListener(v -> {
            isProfile = true;
            showImageDialog();
        });

        binding.btnCover.setOnClickListener(v -> {
            isProfile = false;
            showImageDialog();
        });
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
            Glide.with(getContext()).asBitmap().load(user.getCover()).into(binding.ivCover);

        if (user.getImage() != null)
            Glide.with(getContext()).asBitmap().load(user.getImage()).into(binding.ivProfile);

        binding.tvName.setText(String.format(getString(R.string.user_name), user.getName(), user.getSurname()));
        binding.tvNickname.setText(user.getNick());
    }

    private void showImageDialog() {
        CharSequence[] options = new CharSequence[]{getString(R.string.from_gallery), getString(R.string.take_pick)};

        new MaterialAlertDialogBuilder(getContext())
                .setTitle(getString(R.string.select_option))
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            openGallery();
                        }else {
                            openCamera();
                        }
                    }
                }).show();
    }

    /**
     * gallery is displayed to select an image
     */
    private void openGallery() {
        Intent i = new Intent();
        i.setType("image/*");
        i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,getResources().getString(R.string.select_option)), GALLERY_REQUEST_CODE);
    }

    /**
     * The device's camera opens so that the user can take a photo
     */
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try {
            photoFile = createPhotoFile();
        }catch (Exception e){
            showAlertDialog(getString(R.string.common_mistake));
            e.printStackTrace();
        }
        if (photoFile != null){
            Uri photoUri = FileProvider.getUriForFile(requireActivity(), BuildConfig.APPLICATION_ID +".provider", photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        }
    }

    private File createPhotoFile() throws IOException {
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String prefix = (new Date().toString()).replace(":", "").replace("?", "");
        File photoFile = File.createTempFile(
                prefix + "_photo",
                ".jpg",
                storageDir
        );
        mAbsolutePhotoPath = photoFile.getAbsolutePath();
        return photoFile;
    }

    @Override
    public void setCounters(Follow result) {
        binding.postCounter.setText(String.valueOf(result.getPost()));
        binding.followingCounter.setText(String.valueOf(result.getFollowing()));
        binding.followersCounter.setText(String.valueOf(result.getFollowed()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == GALLERY_REQUEST_CODE &&  resultCode == Activity.RESULT_OK) {
            try {
                File file = FileUtil.from(requireActivity(), data.getData());

                if (isProfile){
                    imageProfile = AppUtils.toBase64(requireContext(), file);
                    presenter.updateImageProfile(imageProfile);
                } else{
                    imageCover = AppUtils.toBase64(requireContext(), file);
                    presenter.updateImageCover(imageCover);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //  image from camera
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            try {
                File file = new File(mAbsolutePhotoPath);
                if (isProfile){
                    imageProfile = AppUtils.toBase64(requireContext(), file);
                    presenter.updateImageProfile(imageProfile);
                } else{
                    imageCover = AppUtils.toBase64(requireContext(), file);
                    presenter.updateImageCover(imageCover);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void imageUpdated(String message) {
        if (isProfile){
            user.setImage(imageProfile);
            PreferencesManager.getInstance().saveUser(user);
            Glide.with(requireContext()).load(Base64.decode(imageProfile, Base64.DEFAULT)).into(binding.ivProfile);
        } else{
            user.setCover(imageCover);
            PreferencesManager.getInstance().saveUser(user);
            Glide.with(requireContext()).load(Base64.decode(imageCover, Base64.DEFAULT)).into(binding.ivCover);
        }

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}