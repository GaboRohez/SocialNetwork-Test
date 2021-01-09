package xyz.gaborohez.socialnetwork.ui.update_user_info;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.gaborohez.socialnetwork.R;

public class UpdateInfoFragment extends Fragment {

    private String name;
    private boolean isName;

    public UpdateInfoFragment(String name, boolean isName) {
        this.name = name;
        this.isName = isName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_info, container, false);
    }
}