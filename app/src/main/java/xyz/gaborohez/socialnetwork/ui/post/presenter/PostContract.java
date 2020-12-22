package xyz.gaborohez.socialnetwork.ui.post.presenter;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.post.PostRequest;
import xyz.gaborohez.socialnetwork.ui.base.BaseView;

public interface PostContract {
    interface View extends BaseView {

        void postPublished(String message);
    }

    interface Presenter {

        void createPost(PostRequest request);
    }

    interface Interactor {

        Single<BaseResponse> createPost(PostRequest request);
    }
}
