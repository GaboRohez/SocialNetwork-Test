package xyz.gaborohez.socialnetwork.ui.profile.presenter;

import io.reactivex.Single;
import xyz.gaborohez.socialnetwork.data.models.Follow;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.follows.FollowsResponse;
import xyz.gaborohez.socialnetwork.data.network.model.imageprofile.UpdateImageRequest;
import xyz.gaborohez.socialnetwork.ui.base.BaseView;

public interface ProfileContract {
    interface View extends BaseView {

        void setCounters(Follow result);

        void imageUpdated(String message);
    }

    interface Presenter {

        void getCounters();

        void updateImageProfile(String image);

        void updateImageCover(String image);
    }

    interface Interactor {

        Single<FollowsResponse> getCounters();

        Single<BaseResponse> updateImageProfile(UpdateImageRequest request);

        Single<BaseResponse> updateImageCover(UpdateImageRequest request);
    }
}
