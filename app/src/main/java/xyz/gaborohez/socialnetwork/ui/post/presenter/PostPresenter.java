package xyz.gaborohez.socialnetwork.ui.post.presenter;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import xyz.gaborohez.socialnetwork.app.SocialApp;
import xyz.gaborohez.socialnetwork.constants.AppConstants;
import xyz.gaborohez.socialnetwork.data.network.model.post.PostRequest;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.post.interactor.PostInteractor;

public class PostPresenter extends BasePresenter<PostContract.View> implements PostContract.Presenter {

    private final PostContract.Interactor interactor;

    public PostPresenter(PostContract.View view) {
        super(view);
        interactor = new PostInteractor();
    }

    @Override
    public void createPost(PostRequest request) {

        addSubscription(interactor.createPost(request)
                .doOnSubscribe(disposable -> view.showLoader(true))
                .doAfterTerminate(() -> view.showLoader(false))
                .subscribe(response -> {
                    view.postPublished(response.getMessage());
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
