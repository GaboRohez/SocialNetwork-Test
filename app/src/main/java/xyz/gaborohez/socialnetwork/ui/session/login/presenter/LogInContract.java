package xyz.gaborohez.socialnetwork.ui.session.login.presenter;

import io.reactivex.Single;
import xyz.gaborohez.socialnetwork.data.network.model.login.LogInRequest;
import xyz.gaborohez.socialnetwork.data.network.model.login.LogInResponse;
import xyz.gaborohez.socialnetwork.data.network.model.login.UserInfoResponse;
import xyz.gaborohez.socialnetwork.ui.base.BaseView;

public interface LogInContract {

    interface View extends BaseView {
        void emailError(String message);
        void passwordError(String message);
        void openMain();
    }

    interface Presenter {
        void logIn(LogInRequest request);
    }

    interface Interactor {
        Single<LogInResponse> logIn(LogInRequest request);
        Single<UserInfoResponse> getCurrentUser();
    }

}
