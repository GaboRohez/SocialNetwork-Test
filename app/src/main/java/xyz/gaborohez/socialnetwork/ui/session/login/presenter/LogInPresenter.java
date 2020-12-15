package xyz.gaborohez.socialnetwork.ui.session.login.presenter;

import android.util.Log;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import xyz.gaborohez.socialnetwork.app.SocialApp;
import xyz.gaborohez.socialnetwork.constants.AppConstants;
import xyz.gaborohez.socialnetwork.data.network.model.login.LogInRequest;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.session.login.interactor.LogInInteractor;
import xyz.gaborohez.socialnetwork.ui.utils.AppUtils;

import static xyz.gaborohez.socialnetwork.constants.AppConstants.SUCCESS;

public class LogInPresenter extends BasePresenter<LogInContract.View> implements LogInContract.Presenter {

    private static final String TAG = "LogInPresenter";

    private final LogInContract.Interactor interactor;

    public LogInPresenter(LogInContract.View view) {
        super(view);
        interactor = new LogInInteractor();
    }

    @Override
    public void logIn(LogInRequest request) {

        if (request.getEmail().isEmpty() || !AppUtils.isValidEmail(request.getEmail())){
            view.emailError(SocialApp.resourcesManager.getEmailErrorMessage());
            return;
        }

        if (request.getPassword().isEmpty()){
            view.passwordError(SocialApp.resourcesManager.getPasswordError());
            return;
        }

        addSubscription(interactor.logIn(request)
                .doOnSubscribe(disposable -> view.showLoader(true))
                .doAfterTerminate(() -> view.showLoader(false))
                .subscribe(response -> {
                    if (response.getCode().equals(SUCCESS)){
                        PreferencesManager.getInstance().saveString(AppConstants.KEY_TOKEN, response.getToken());   //  save the user token
                        getUserInfo();
                    }else  {
                        view.showAlertDialog(response.getMessage());
                    }
                }, throwable -> {
                    if (throwable instanceof HttpException) {
                        // handle message
                        view.showAlertDialog(handlerError(throwable));
                    } else if (throwable instanceof SocketTimeoutException) {
                        // handle timeout from Retrofit
                        view.showAlertDialog(processError(throwable));
                    }else {
                        view.showAlertDialog(SocialApp.resourcesManager.getErrorServer());
                    }
                }));
    }

    private void getUserInfo() {
        addSubscription(interactor.getCurrentUser()
                .doOnSubscribe(disposable -> view.showLoader(true))
                .doAfterTerminate(() -> view.showLoader(false))
                .subscribe(response -> {
                    if (response.getCode().equals(SUCCESS)){
                        PreferencesManager.getInstance().saveUser(response.getUser());
                        PreferencesManager.getInstance().saveBoolean(AppConstants.isLogged, true);

                        view.openMain();
                    }else  {
                        view.showAlertDialog(response.getMessage());
                    }
                }, throwable -> {
                    if (throwable instanceof HttpException) {
                        // handle message
                        view.showAlertDialog(handlerError(throwable));
                    } else if (throwable instanceof SocketTimeoutException) {
                        // handle timeout from Retrofit
                        view.showAlertDialog(processError(throwable));
                    }else {
                        view.showAlertDialog(SocialApp.resourcesManager.getErrorServer());
                    }
                }));
    }
}
