package xyz.gaborohez.socialnetwork.ui.profile.presenter;

import android.util.Log;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import xyz.gaborohez.socialnetwork.app.SocialApp;
import xyz.gaborohez.socialnetwork.constants.AppConstants;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.profile.interactor.ProfileInteractor;

import static xyz.gaborohez.socialnetwork.constants.AppConstants.SUCCESS;

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {

    private final ProfileContract.Interactor interactor;
    private static final String TAG = "ProfilePresenter";

    public ProfilePresenter(ProfileContract.View view) {
        super(view);
        interactor = new ProfileInteractor();
    }

    @Override
    public void getCounters() {
        addSubscription(interactor.getCounters()
                .doOnSubscribe(disposable -> view.showLoader(true))
                .doAfterTerminate(() -> view.showLoader(false))
                .subscribe(response -> {
                    if (response.getCode().equals(SUCCESS)){
                        view.setCounters(response.getFollow());
                    }else  {
                        view.showAlertDialog(response.getMessage());
                    }
                }, throwable -> {
                    if (throwable instanceof HttpException) {
                        HttpException error = (HttpException)throwable;

                        if (error.response().code() == AppConstants.EXPIRED)
                            view.expiredToken();
                        else    // handle message
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