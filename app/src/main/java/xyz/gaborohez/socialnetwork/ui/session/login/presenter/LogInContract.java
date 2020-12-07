package xyz.gaborohez.socialnetwork.ui.session.login.presenter;

import io.reactivex.Single;
import xyz.gaborohez.socialnetwork.data.network.model.LogInRequest;
import xyz.gaborohez.socialnetwork.data.network.model.LogInResponse;
import xyz.gaborohez.socialnetwork.ui.base.BaseView;

public interface LogInContract {

    interface View extends BaseView {

        void emailError(String message);
        void passwordError(String message);
    }

    interface Presenter {

        void logIn(LogInRequest request);
    }

    interface Interactor {

        Single<LogInResponse> logIn(LogInRequest request);
    }

}
