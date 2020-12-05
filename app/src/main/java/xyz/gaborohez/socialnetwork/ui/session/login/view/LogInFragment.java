package xyz.gaborohez.socialnetwork.ui.session.login.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.network.model.LogInRequest;
import xyz.gaborohez.socialnetwork.databinding.FragmentLogInBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.session.login.presenter.LogInContract;
import xyz.gaborohez.socialnetwork.ui.session.login.presenter.LogInPresenter;

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
        binding.btnLogIn.setOnClickListener(v -> {
            LogInRequest request = new LogInRequest();
            request.setEmail(binding.layoutEmail.getEditText().getText().toString().trim());
            request.setPassword(binding.layoutPassword.getEditText().getText().toString().trim());
            presenter.logIn(request);
        });
    }
}