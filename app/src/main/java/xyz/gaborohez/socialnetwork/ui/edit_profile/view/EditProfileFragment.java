package xyz.gaborohez.socialnetwork.ui.edit_profile.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import xyz.gaborohez.socialnetwork.BuildConfig;
import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;
import xyz.gaborohez.socialnetwork.databinding.FragmentEditProfileBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.edit_profile.presenter.EditProfileContract;
import xyz.gaborohez.socialnetwork.ui.edit_profile.presenter.EditProfilePresenter;
import xyz.gaborohez.socialnetwork.ui.personal_information.PersonalInfoFragment;
import xyz.gaborohez.socialnetwork.ui.utils.AppUtils;
import xyz.gaborohez.socialnetwork.ui.utils.FileUtil;

import static xyz.gaborohez.socialnetwork.constants.AppConstants.CAMERA_REQUEST_CODE;
import static xyz.gaborohez.socialnetwork.constants.AppConstants.EDIT_SUCCESS_CODE;
import static xyz.gaborohez.socialnetwork.constants.AppConstants.GALLERY_REQUEST_CODE;

public class EditProfileFragment extends BaseFragment<EditProfileContract.Presenter, FragmentEditProfileBinding> implements EditProfileContract.View, View.OnClickListener {

    private User user;
    private boolean isProfile;
    private String imageCover;
    private String imageProfile;
    private String mAbsolutePhotoPath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = PreferencesManager.getInstance().getUser();
        presenter = new EditProfilePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpUserInfo();
        setUpEvents();
    }

    private void setUpUserInfo() {
        if (user.getCover() != null)
            Glide.with(requireContext()).asBitmap().load(user.getCover()).into(binding.ivCover);

        if (user.getImage() != null){
            Glide.with(requireContext()).asBitmap().load(user.getImage()).into(binding.ivProfile);
        }

    }

    private void setUpEvents() {
        binding.btnEditProfilePhoto.setOnClickListener(this);
        binding.btnEditCover.setOnClickListener(this);
        binding.btnPersonalInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEditProfilePhoto:
                isProfile = true;
                showImageDialog();
                break;
            case R.id.btnEditCover:
                isProfile = false;
                showImageDialog();
                break;
            case R.id.btnPersonalInfo:
                addFragmentInParentActivity(new PersonalInfoFragment(String.format(getString(R.string.user_name), user.getName(), user.getSurname()), user.getEmail()), PersonalInfoFragment.class.getName(), R.id.contentMain);
                return;
            default:
                return;
        }
    }

    private void showImageDialog() {
        CharSequence[] options = new CharSequence[]{getString(R.string.from_gallery), getString(R.string.take_pick)};

        new MaterialAlertDialogBuilder(getContext())
                .setTitle(getString(R.string.select_option))
                .setItems(options, (dialog, which) -> {
                    if (which == 0){
                        openGallery();
                    }else {
                        openCamera();
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case GALLERY_REQUEST_CODE:
                if(resultCode == Activity.RESULT_OK) {
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
                break;
            case CAMERA_REQUEST_CODE:
                //  image from camera
                if (resultCode == Activity.RESULT_OK){
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
                    return;
                }
                break;
        }
    }

    @Override
    public void imageUpdated(String message) {
        if (isProfile){
            user.setImage(imageProfile);
            PreferencesManager.getInstance().saveUser(user);
            Glide.with(requireContext()).load(Base64.decode(imageProfile, Base64.DEFAULT)).into(binding.ivProfile);

            Intent intent = new Intent();
            intent.putExtra("isProfile", true);
            intent.putExtra("image", imageProfile);
            getTargetFragment().onActivityResult(getTargetRequestCode(), EDIT_SUCCESS_CODE, intent);

        } else{
            user.setCover(imageCover);
            PreferencesManager.getInstance().saveUser(user);
            Glide.with(requireContext()).load(Base64.decode(imageCover, Base64.DEFAULT)).into(binding.ivCover);

            Intent intent = new Intent();
            intent.putExtra("isProfile", false);
            intent.putExtra("image", imageCover);
            getTargetFragment().onActivityResult(getTargetRequestCode(), EDIT_SUCCESS_CODE, intent);
        }

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}