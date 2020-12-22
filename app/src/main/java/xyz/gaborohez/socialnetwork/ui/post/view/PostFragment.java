package xyz.gaborohez.socialnetwork.ui.post.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
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
import xyz.gaborohez.socialnetwork.data.network.model.post.PostRequest;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;
import xyz.gaborohez.socialnetwork.databinding.FragmentPostBinding;
import xyz.gaborohez.socialnetwork.databinding.FragmentProfileBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.post.presenter.PostContract;
import xyz.gaborohez.socialnetwork.ui.post.presenter.PostPresenter;
import xyz.gaborohez.socialnetwork.ui.profile.presenter.ProfileContract;
import xyz.gaborohez.socialnetwork.ui.profile.presenter.ProfilePresenter;
import xyz.gaborohez.socialnetwork.ui.utils.AppUtils;
import xyz.gaborohez.socialnetwork.ui.utils.FileUtil;

import static xyz.gaborohez.socialnetwork.constants.AppConstants.CAMERA_REQUEST_CODE;
import static xyz.gaborohez.socialnetwork.constants.AppConstants.GALLERY_REQUEST_CODE;
import static xyz.gaborohez.socialnetwork.constants.AppConstants.POST_SUCCESS_CODE;

public class PostFragment extends BaseFragment<PostContract.Presenter, FragmentPostBinding> implements PostContract.View {

    private static final String TAG = "PostFragment";

    private String image;
    private String mAbsolutePhotoPath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PostPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpProfile();
        setUpEvents();
    }

    private void setUpProfile() {
        User user = PreferencesManager.getInstance().getUser();
        if (user.getImage() != null)
            Glide.with(getContext()).asBitmap().load(user.getImage()).into(binding.ivProfile);

        binding.tvName.setText(String.format(getString(R.string.user_name), user.getName(), user.getSurname()));

    }

    private void setUpEvents() {

        binding.etComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.btnPost.setEnabled(s.toString().length() > 0);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }@Override
            public void afterTextChanged(Editable s) { }
        });

        binding.btnImage.setOnClickListener(v -> showImageDialog());

        binding.btnDelete.setOnClickListener(v -> {
            image = null;
            binding.image.setVisibility(View.GONE);
        });

        binding.btnPost.setOnClickListener(v -> {

            PostRequest request = new PostRequest();
            request.setImage(image);
            request.setText(binding.etComment.getText().toString().trim());

            presenter.createPost(request);
        });
    }

    /*
    show dialog to
     */
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
    public void postPublished(String message) {
        Intent intent = new Intent();
        getTargetFragment().onActivityResult(getTargetRequestCode(), POST_SUCCESS_CODE, intent);
        getActivity().getSupportFragmentManager().popBackStack();

        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case GALLERY_REQUEST_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    try {
                        File file = FileUtil.from(requireActivity(), data.getData());
                        image = AppUtils.toBase64(requireContext(), file);

                        Glide.with(requireContext()).load(Base64.decode(image, Base64.DEFAULT)).into(binding.image);
                        binding.contentImage.setVisibility(View.VISIBLE);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case CAMERA_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK){
                    try {
                        File file = new File(mAbsolutePhotoPath);
                        image = AppUtils.toBase64(requireContext(), file);

                        Glide.with(requireContext()).load(Base64.decode(image, Base64.DEFAULT)).into(binding.image);
                        binding.contentImage.setVisibility(View.VISIBLE);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

}