package xyz.gaborohez.socialnetwork.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import xyz.gaborohez.socialnetwork.R;


public abstract class BaseActivity<P> extends AppCompatActivity implements BaseView{

    protected P presenter;
    //private LoaderDialog loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (presenter != null){
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
        new MaterialAlertDialogBuilder(this)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    @Override
    public void showAlertDialog(int resId) {
        new MaterialAlertDialogBuilder(this)
                .setMessage(getString(resId))
                .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    /*private LoaderDialog getLoader() {
        if (loader != null)
            return loader;
        else
            return loader = new LoaderDialog(this);
    }*/

    protected void addFragment(Fragment fragment, String TAG, int id) {
        getSupportFragmentManager().beginTransaction()
                .add(id, fragment, TAG)
                .addToBackStack(TAG)
                .commitAllowingStateLoss();
    }


    protected void replaceFragment(Fragment fragment, String TAG, int id) {
        getSupportFragmentManager().beginTransaction()
                .replace(id, fragment, TAG)
                .commit();
    }

    @Override
    public void expiredToken() {
        /*List<GenericCatalog> auxCenters = AppUtils.getBusinessCenter();

        PreferencesManager.getInstance().removePreferences();
        PreferencesManager.getInstance().saveArrayList(auxCenters, AppConstants.KEY_BUSINESS_CENTERS);
        startActivity(new Intent(this, SessionActivity.class));
        finish();

        Toast.makeText(this, getString(R.string.session_expired), Toast.LENGTH_SHORT).show();*/
    }
}
