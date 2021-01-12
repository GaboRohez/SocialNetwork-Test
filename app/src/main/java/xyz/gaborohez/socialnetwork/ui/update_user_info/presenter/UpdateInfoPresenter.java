package xyz.gaborohez.socialnetwork.ui.update_user_info.presenter;

import java.net.SocketTimeoutException;

import io.reactivex.Single;
import retrofit2.HttpException;
import xyz.gaborohez.socialnetwork.app.SocialApp;
import xyz.gaborohez.socialnetwork.constants.AppConstants;
import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.user.UpdateNameRequest;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.update_user_info.interactor.UpdateInfoInteractor;

import static xyz.gaborohez.socialnetwork.constants.AppConstants.SUCCESS;

/**
 * Created by Gabriel Rodríguez on 11/01/21.
 * Email gabow95k@gmail.com
 */
public class UpdateInfoPresenter extends BasePresenter<UpdateInfoContract.View> implements UpdateInfoContract.Presenter {

    private final UpdateInfoContract.Interactor interactor;

    public UpdateInfoPresenter(UpdateInfoContract.View view) {
        super(view);
        interactor = new UpdateInfoInteractor();
    }

    @Override
    public void updateName(UpdateNameRequest request) {
        User user = PreferencesManager.getInstance().getUser();

        if (user.getName().equals(request.getName()) && user.getSurname().equals(request.getSurname())){
            view.showAlertDialog(SocialApp.resourcesManager.getErrorNameEquals());
            return;
        }

        addSubscription(interactor.updateName(request)
                .doOnSubscribe(disposable -> view.showLoader(true))
                .doAfterTerminate(() -> view.showLoader(false))
                .subscribe(response -> {
                    if (response.getCode().equals(SUCCESS)){

                        user.setName(request.getName());
                        user.setSurname(request.getSurname());
                        PreferencesManager.getInstance().saveUser(user);

                        view.updated(response.getMessage());
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
