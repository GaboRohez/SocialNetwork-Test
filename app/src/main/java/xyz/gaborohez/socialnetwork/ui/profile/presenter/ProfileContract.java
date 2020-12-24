package xyz.gaborohez.socialnetwork.ui.profile.presenter;

import java.util.List;

import io.reactivex.Single;
import xyz.gaborohez.socialnetwork.data.models.Follow;
import xyz.gaborohez.socialnetwork.data.models.Publications;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.follows.FollowsResponse;
import xyz.gaborohez.socialnetwork.data.network.model.imageprofile.UpdateImageRequest;
import xyz.gaborohez.socialnetwork.data.network.model.post.PostsResponse;
import xyz.gaborohez.socialnetwork.ui.base.BaseView;

public interface ProfileContract {
    interface View extends BaseView {

        void setCounters(Follow result);

        void imageUpdated(String message);

        void emptyPost();

        void showPosts(List<Publications> publications);

        void postRemoved(int position, String message);
    }

    interface Presenter {

        void getCounters();

        void updateImageProfile(String image);

        void updateImageCover(String image);

        void getPosts(int page);

        void deletePost(String id, int position);
    }

    interface Interactor {

        Single<FollowsResponse> getCounters();

        Single<BaseResponse> updateImageProfile(UpdateImageRequest request);

        Single<BaseResponse> updateImageCover(UpdateImageRequest request);

        Single<PostsResponse> getPost(int page);

        Single<BaseResponse> deletePost(String id);
    }
}
