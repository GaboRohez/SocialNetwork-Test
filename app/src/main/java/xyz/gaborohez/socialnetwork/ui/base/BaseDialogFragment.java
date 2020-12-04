package xyz.gaborohez.socialnetwork.ui.base;

import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public abstract class BaseDialogFragment<P, B> extends DialogFragment implements BaseView {
    protected P presenter;
    protected B binding;
    //private LoaderDialog loader;

    @Override
    public void showLoader(boolean visible) {
        /*if (visible)
            getLoader().show();
        else
            getLoader().dismiss();*/
    }

    /*private LoaderDialog getLoader() {
        if (loader != null)
            return loader;
        else
            return loader = new LoaderDialog(requireActivity());
    }*/

    @Override
    public void showAlertDialog(String message) {
        /*AlertDialog dialog = new AlertDialog(requireContext(), message);
        dialog.show();*/
    }

    @Override
    public void showAlertDialog(int resId) {
        /*lertDialog dialog = new AlertDialog(requireContext(), getString(resId));
        dialog.show();*/
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

    @Override
    public void onDestroyView() {
        if (binding != null) {
            binding = null;
        }

        if (presenter != null) {
            ((BasePresenter) presenter).detachView();
        }
        super.onDestroyView();
    }
}
