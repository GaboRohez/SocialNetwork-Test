package xyz.gaborohez.socialnetwork.ui.base;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * Helper class for Viewer based on MVP pattern and View Binding
 *
 * @param <T> - Presenter Class </T>
 * @param <B> - ViewBinding Class </B>
 */

public abstract class BaseFragment<T, B> extends Fragment implements BaseView{

    protected T presenter;
    protected B binding;

    //private LoaderDialog loader;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {

        if (binding != null) {
            binding = null;
        }

        if (presenter != null) {
            ((BasePresenter) presenter).detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showLoader(boolean visible) {
        /*if (visible)
            getLoader().show();
        else
            getLoader().dismiss();*/
    }

    @Override
    public void showAlertDialog(String message) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showAlertDialog(message);
        }
    }

    @Override
    public void showAlertDialog(int resId) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showAlertDialog(resId);
        }
    }

    /*private LoaderDialog getLoader() {
        if (loader != null)
            return loader;
        else
            return loader = new LoaderDialog(requireContext());
    }*/

    protected void addFragmentInParentActivity(Fragment fragment, String TAG, int id) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).addFragment(fragment, TAG, id);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
    }

    @Override
    public void expiredToken() {
        /*List<GenericCatalog> auxCenters = AppUtils.getBusinessCenter();

        PreferencesManager.getInstance().removePreferences();
        PreferencesManager.getInstance().saveArrayList(auxCenters, AppConstants.KEY_BUSINESS_CENTERS);
        startActivity(new Intent(requireActivity(), SessionActivity.class));
        requireActivity().finish();

        Toast.makeText(requireContext(), getString(R.string.session_expired), Toast.LENGTH_SHORT).show();*/
    }
}