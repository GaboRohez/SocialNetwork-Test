package xyz.gaborohez.socialnetwork.ui.update_user_info.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.network.model.user.UpdateNameRequest;
import xyz.gaborohez.socialnetwork.databinding.FragmentProfileBinding;
import xyz.gaborohez.socialnetwork.databinding.FragmentUpdateInfoBinding;
import xyz.gaborohez.socialnetwork.ui.adapter.PostAdapter;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.profile.presenter.ProfileContract;
import xyz.gaborohez.socialnetwork.ui.profile.presenter.ProfilePresenter;
import xyz.gaborohez.socialnetwork.ui.update_user_info.presenter.UpdateInfoContract;
import xyz.gaborohez.socialnetwork.ui.update_user_info.presenter.UpdateInfoPresenter;
import xyz.gaborohez.socialnetwork.ui.utils.AppUtils;

public class UpdateInfoFragment extends BaseFragment<UpdateInfoContract.Presenter, FragmentUpdateInfoBinding> implements UpdateInfoContract.View {

    private String userInfo;
    private boolean isName;

    public UpdateInfoFragment(String userInfo, boolean isName) {
        this.userInfo = userInfo;
        this.isName = isName;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new UpdateInfoPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialConfig();
        setUpEvents();
    }

    private void initialConfig() {
        binding.tvInfo.setText(userInfo);

        if (!isName){
            binding.title.setText(getString(R.string.email));
            binding.tvSubtitle.setText(getString(R.string.current_email));
            binding.tvMessage.setText(getString(R.string.edit_email_message));
            binding.etName.setHint(getString(R.string.email));
            binding.tvMessageEmail.setVisibility(View.VISIBLE);
            binding.etName.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            binding.etSurname.setVisibility(View.GONE);
            binding.btnAction.setText(getString(R.string.change_email));
        }
    }

    public void setUpEvents() {

        binding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkBtnEnable();
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }@Override
            public void afterTextChanged(Editable editable) { }
        });

        binding.etSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkBtnEnable();
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }@Override
            public void afterTextChanged(Editable editable) { }
        });

        binding.btnAction.setOnClickListener(v -> {
            if (isName){
                UpdateNameRequest request = new UpdateNameRequest();
                request.setName(binding.etName.getText().toString().trim());
                request.setSurname(binding.etSurname.getText().toString().trim());
                presenter.updateName(request);
            }else {
                presenter.updateEmail(binding.etName.getText().toString().trim());
            }
        });
    }

    private void checkBtnEnable() {
        if (isName)
            binding.btnAction.setEnabled(!binding.etName.getText().toString().trim().isEmpty() && !binding.etSurname.getText().toString().trim().isEmpty());
        else
            binding.btnAction.setEnabled(!binding.etName.getText().toString().trim().isEmpty() && AppUtils.isValidEmail(binding.etName.getText().toString().trim()));
    }

    @Override
    public void updated(String message) {
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}