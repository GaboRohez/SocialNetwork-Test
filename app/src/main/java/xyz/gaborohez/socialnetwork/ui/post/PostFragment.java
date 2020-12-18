package xyz.gaborohez.socialnetwork.ui.post;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.databinding.FragmentPostBinding;
import xyz.gaborohez.socialnetwork.databinding.FragmentProfileBinding;
import xyz.gaborohez.socialnetwork.ui.base.BaseFragment;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;

public class PostFragment extends BaseFragment<BasePresenter, FragmentPostBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v -> {
            Intent intent = new Intent();
            getTargetFragment().onActivityResult(getTargetRequestCode(), 1, intent);
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }
}