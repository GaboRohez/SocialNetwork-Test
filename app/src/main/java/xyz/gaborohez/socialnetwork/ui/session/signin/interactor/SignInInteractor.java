package xyz.gaborohez.socialnetwork.ui.session.signin.interactor;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xyz.gaborohez.socialnetwork.data.network.RetrofitClient;
import xyz.gaborohez.socialnetwork.data.network.SocialNetworkAPI;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.signin.SignInRequest;
import xyz.gaborohez.socialnetwork.ui.session.signin.presenter.SignInContract;

public class SignInInteractor implements SignInContract.Interactor {

    @Override
    public Single<BaseResponse> signIn(SignInRequest request) {
        return RetrofitClient.singleAPI()
                .create(SocialNetworkAPI.class)
                .signIn(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
