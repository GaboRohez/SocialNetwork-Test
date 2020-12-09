package xyz.gaborohez.socialnetwork.ui.session.signin.presenter;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.signin.SignInRequest;
import xyz.gaborohez.socialnetwork.ui.base.BaseView;

public interface SignInContract extends BaseView {

    interface View extends BaseView {

        void emailError(String message);

        void passError(String message);

        void cPassError(String message);

        void userCreated();
    }

    interface Presenter {

        void signIn(String name, String surname, String nickname, String email, String password, String confPassword);
    }

    interface Interactor {

        Single<BaseResponse> signIn(SignInRequest request);
    }

}
