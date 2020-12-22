package xyz.gaborohez.socialnetwork.ui.post.interactor;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.gaborohez.socialnetwork.data.network.RetrofitClient;
import xyz.gaborohez.socialnetwork.data.network.SocialNetworkAPI;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.post.PostRequest;
import xyz.gaborohez.socialnetwork.ui.post.presenter.PostContract;

public class PostInteractor implements PostContract.Interactor {

    @Override
    public Single<BaseResponse> createPost(PostRequest request) {
        return RetrofitClient.api()
                .create(SocialNetworkAPI.class)
                .createPost(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
