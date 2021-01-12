package xyz.gaborohez.socialnetwork.ui.update_user_info.interactor;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.gaborohez.socialnetwork.data.network.RetrofitClient;
import xyz.gaborohez.socialnetwork.data.network.SocialNetworkAPI;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.user.UpdateNameRequest;
import xyz.gaborohez.socialnetwork.ui.update_user_info.presenter.UpdateInfoContract;

/**
 * Created by Gabriel Rodríguez on 11/01/21.
 * Email gabow95k@gmail.com
 */
public class UpdateInfoInteractor implements UpdateInfoContract.Interactor{
    @Override
    public Single<BaseResponse> updateName(UpdateNameRequest request) {
        return RetrofitClient.api()
                .create(SocialNetworkAPI.class)
                .updateName(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<BaseResponse> updateEmail(String email) {
        return RetrofitClient.api()
                .create(SocialNetworkAPI.class)
                .updateEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
