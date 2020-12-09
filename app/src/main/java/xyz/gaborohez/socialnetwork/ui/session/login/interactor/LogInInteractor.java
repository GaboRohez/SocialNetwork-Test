package xyz.gaborohez.socialnetwork.ui.session.login.interactor;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.gaborohez.socialnetwork.data.network.RetrofitClient;
import xyz.gaborohez.socialnetwork.data.network.SocialNetworkAPI;
import xyz.gaborohez.socialnetwork.data.network.model.login.LogInRequest;
import xyz.gaborohez.socialnetwork.data.network.model.login.LogInResponse;
import xyz.gaborohez.socialnetwork.ui.session.login.presenter.LogInContract;

public class LogInInteractor implements LogInContract.Interactor{

    @Override
    public Single<LogInResponse> logIn(LogInRequest request) {
        return RetrofitClient.singleAPI()
                .create(SocialNetworkAPI.class)
                .logIn(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
