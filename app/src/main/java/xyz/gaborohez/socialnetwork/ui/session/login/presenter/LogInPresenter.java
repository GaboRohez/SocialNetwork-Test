package xyz.gaborohez.socialnetwork.ui.session.login.presenter;

import android.util.Log;

import retrofit2.HttpException;
import xyz.gaborohez.socialnetwork.constants.AppConstants;
import xyz.gaborohez.socialnetwork.data.network.model.LogInRequest;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.session.login.interactor.LogInInteractor;

import static xyz.gaborohez.socialnetwork.constants.AppConstants.SUCCESS;

public class LogInPresenter extends BasePresenter<LogInContract.View> implements LogInContract.Presenter {

    private static final String TAG = "LogInPresenter";

    private LogInContract.Interactor interactor;

    public LogInPresenter(LogInContract.View view) {
        super(view);
        interactor = new LogInInteractor();
    }

    @Override
    public void logIn(LogInRequest request) {

        if (request.getEmail().isEmpty()){

            return;
        }

        if (request.getPassword().isEmpty()){

            return;
        }

        addSubscription(interactor.logIn(request)
                .doOnSubscribe(disposable -> view.showLoader(true))
                .doAfterTerminate(() -> view.showLoader(false))
                .subscribe(response -> {
                    if (response.getCode().equals(SUCCESS)){
                        PreferencesManager.getInstance().saveString(AppConstants.KEY_TOKEN, response.getToken());//  se almacena el token
                        //getUserInfo();
                    }else  {
                        Log.d(TAG, "logIn: "+response.getMessage());
                    }
                }, throwable -> {
                    Log.d(TAG, "logIn: "+throwable.getMessage());
                    /*try {
                        HttpException error = (HttpException)throwable;
                        if (error.response().code()==400 || error.response().code()==401)
                            view.showAlertDialog(CosApplication.androidResourceManager.getErrorAuth());
                        else
                            view.showAlertDialog(processError(throwable));
                    }catch (Exception e){
                        e.printStackTrace();
                        view.showAlertDialog(CosApplication.androidResourceManager.getErrorServer());
                    }*/

                }));
    }
}
