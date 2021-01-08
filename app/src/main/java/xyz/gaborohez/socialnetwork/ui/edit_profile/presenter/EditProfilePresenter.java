package xyz.gaborohez.socialnetwork.ui.edit_profile.presenter;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import xyz.gaborohez.socialnetwork.app.SocialApp;
import xyz.gaborohez.socialnetwork.constants.AppConstants;
import xyz.gaborohez.socialnetwork.data.network.model.imageprofile.UpdateImageRequest;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.edit_profile.interactor.EditProfileInteractor;
import xyz.gaborohez.socialnetwork.ui.profile.presenter.ProfileContract;

import static xyz.gaborohez.socialnetwork.constants.AppConstants.SUCCESS;

public class EditProfilePresenter extends BasePresenter<EditProfileContract.View> implements EditProfileContract.Presenter{

    private EditProfileContract.Interactor interactor;

    public EditProfilePresenter(EditProfileContract.View view) {
        super(view);
        interactor = new EditProfileInteractor();
    }

    @Override
    public void updateImageProfile(String image) {
        UpdateImageRequest request = new UpdateImageRequest();
        request.setImage(image);

        addSubscription(interactor.updateImageProfile(request)
                .doOnSubscribe(disposable -> view.showLoader(true))
                .doAfterTerminate(() -> view.showLoader(false))
                .subscribe(response -> {
                    if (response.getCode().equals(SUCCESS)){
                        view.imageUpdated(response.getMessage());
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

    @Override
    public void updateImageCover(String image) {
        UpdateImageRequest request = new UpdateImageRequest();
        request.setImage(image);

        addSubscription(interactor.updateImageCover(request)
                .doOnSubscribe(disposable -> view.showLoader(true))
                .doAfterTerminate(() -> view.showLoader(false))
                .subscribe(response -> {
                    if (response.getCode().equals(SUCCESS)){
                        view.imageUpdated(response.getMessage());
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
