package xyz.gaborohez.socialnetwork.ui.session.signin.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.databinding.FragmentLogInBinding;
import xyz.gaborohez.socialnetwork.databinding.FragmentSignInBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.session.login.presenter.LogInContract;
import xyz.gaborohez.socialnetwork.ui.session.signin.presenter.SignInContract;
import xyz.gaborohez.socialnetwork.ui.session.signin.presenter.SignInPresenter;

public class SignInFragment extends BaseFragment<SignInContract.Presenter, FragmentSignInBinding> implements SignInContract.View {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignInPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpEvents();
    }

    private void setUpEvents() {

        binding.layoutName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.layoutName.setError(null);
                checkEnableButton();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.layoutSurname.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.layoutSurname.setError(null);
                checkEnableButton();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.layoutNickname.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.layoutNickname.setError(null);
                checkEnableButton();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.layoutEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.layoutEmail.setError(null);
                checkEnableButton();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.layoutPass.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.layoutPass.setError(null);
                checkEnableButton();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.layoutCPass.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.layoutCPass.setError(null);
                checkEnableButton();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        binding.btnCreate.setOnClickListener(v ->{
            presenter.signIn(
                    binding.layoutName.getEditText().getText().toString().trim(),
                    binding.layoutSurname.getEditText().getText().toString().trim(),
                    binding.layoutNickname.getEditText().getText().toString().trim(),
                    binding.layoutEmail.getEditText().getText().toString().trim(),
                    binding.layoutPass.getEditText().getText().toString().trim(),
                    binding.layoutCPass.getEditText().getText().toString().trim());
        });
    }

    private void checkEnableButton(){
        if (!binding.layoutName.getEditText().getText().toString().isEmpty() && !binding.layoutSurname.getEditText().getText().toString().isEmpty() && !binding.layoutNickname.getEditText().getText().toString().isEmpty() && !binding.layoutEmail.getEditText().getText().toString().isEmpty() && !binding.layoutPass.getEditText().getText().toString().isEmpty() && !binding.layoutCPass.getEditText().getText().toString().isEmpty()){
            binding.btnCreate.setEnabled(true);
        }else
            binding.btnCreate.setEnabled(false);
    }

    @Override
    public void emailError(String message) {
        binding.layoutEmail.setError(message);
    }

    @Override
    public void passError(String message) {
        binding.layoutPass.setError(message);
    }

    @Override
    public void cPassError(String message) {
        binding.layoutCPass.setError(message);
    }

    @Override
    public void userCreated() {
        Toast.makeText(requireContext(), getString(R.string.user_created), Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStack();
    }
}