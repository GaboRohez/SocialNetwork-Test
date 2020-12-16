package xyz.gaborohez.socialnetwork.ui.session.login.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.network.model.login.LogInRequest;
import xyz.gaborohez.socialnetwork.databinding.FragmentLogInBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.main.MainActivity;
import xyz.gaborohez.socialnetwork.ui.session.login.presenter.LogInContract;
import xyz.gaborohez.socialnetwork.ui.session.login.presenter.LogInPresenter;
import xyz.gaborohez.socialnetwork.ui.session.signin.view.SignInFragment;

public class LogInFragment extends BaseFragment<LogInContract.Presenter, FragmentLogInBinding> implements LogInContract.View {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LogInPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLogInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpEvents();
    }

    private void setUpEvents() {

        binding.layoutEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.layoutEmail.setError(null);
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }@Override
            public void afterTextChanged(Editable editable) { }
        });

        binding.layoutPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.layoutPassword.setError(null);
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }@Override
            public void afterTextChanged(Editable editable) { }
        });

        binding.btnSignIn.setOnClickListener(v -> addFragmentInParentActivity(new SignInFragment(), SignInFragment.class.getName(), R.id.contentSession));
        binding.btnLogIn.setOnClickListener(v -> {
            LogInRequest request = new LogInRequest();
            request.setEmail(binding.layoutEmail.getEditText().getText().toString().trim());
            request.setPassword(binding.layoutPassword.getEditText().getText().toString().trim());
            presenter.logIn(request);
        });
    }

    @Override
    public void emailError(String message) {
        binding.layoutEmail.setError(message);
    }

    @Override
    public void passwordError(String message) {
        binding.layoutPassword.setError(message);
    }

    @Override
    public void openMain() {
        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }
}