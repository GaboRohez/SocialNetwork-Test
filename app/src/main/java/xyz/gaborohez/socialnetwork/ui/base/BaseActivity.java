package xyz.gaborohez.socialnetwork.ui.base;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;
import xyz.gaborohez.socialnetwork.ui.custom.LoaderDialog;
import xyz.gaborohez.socialnetwork.ui.session.SessionActivity;


public abstract class BaseActivity<P> extends AppCompatActivity implements BaseView{

    protected P presenter;
    private LoaderDialog loader;

    @Override
    protected void onDestroy() {
        if (presenter != null){
            ((BasePresenter) presenter).detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showLoader(boolean visible) {
        if (visible)
            getLoader().show();
        else
            getLoader().dismiss();
    }

    @Override
    public void showAlertDialog(String message) {
        new MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.title_dialog))
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    @Override
    public void showAlertDialog(int resId) {
        new MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.title_dialog))
                .setMessage(getString(resId))
                .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    private LoaderDialog getLoader() {
        if (loader != null)
            return loader;
        else
            return loader = new LoaderDialog(this);
    }

    protected void addFragment(Fragment fragment, String tag, int id) {
        /*getSupportFragmentManager().beginTransaction()
                .add(id, fragment, tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss();*/

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        //fragmentTransaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_bottom,R.anim.slide_out_bottom );
        fragmentTransaction.add(id, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }


    protected void replaceFragment(Fragment fragment, String tag, int id) {
        getSupportFragmentManager().beginTransaction()
                .replace(id, fragment, tag)
                .commit();
    }

    @Override
    public void expiredToken() {
        PreferencesManager.getInstance().removePreferences();
        startActivity(new Intent(this, SessionActivity.class));
        finish();

        Toast.makeText(this, getString(R.string.session_expired), Toast.LENGTH_SHORT).show();
    }
}
