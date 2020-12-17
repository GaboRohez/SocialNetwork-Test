package xyz.gaborohez.socialnetwork.ui.profile.interactor;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.gaborohez.socialnetwork.data.network.RetrofitClient;
import xyz.gaborohez.socialnetwork.data.network.SocialNetworkAPI;
import xyz.gaborohez.socialnetwork.data.network.model.follows.FollowsResponse;
import xyz.gaborohez.socialnetwork.ui.profile.presenter.ProfileContract;

public class ProfileInteractor implements ProfileContract.Interactor{
    @Override
    public Single<FollowsResponse> getCounters() {
        return RetrofitClient.api()
                .create(SocialNetworkAPI.class)
                .getCounters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
