package xyz.gaborohez.socialnetwork.ui.profile.interactor;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.gaborohez.socialnetwork.data.network.RetrofitClient;
import xyz.gaborohez.socialnetwork.data.network.SocialNetworkAPI;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.follows.FollowsResponse;
import xyz.gaborohez.socialnetwork.data.network.model.imageprofile.UpdateImageRequest;
import xyz.gaborohez.socialnetwork.data.network.model.post.PostsResponse;
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

    @Override
    public Single<BaseResponse> updateImageProfile(UpdateImageRequest request) {
        return RetrofitClient.api()
                .create(SocialNetworkAPI.class)
                .updateImageProfile(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<BaseResponse> updateImageCover(UpdateImageRequest request) {
        return RetrofitClient.api()
                .create(SocialNetworkAPI.class)
                .updateImageCover(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<PostsResponse> getPost(int page) {
        return RetrofitClient.api()
                .create(SocialNetworkAPI.class)
                .getPost(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
