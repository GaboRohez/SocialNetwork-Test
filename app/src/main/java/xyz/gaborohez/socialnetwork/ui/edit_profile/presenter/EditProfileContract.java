package xyz.gaborohez.socialnetwork.ui.edit_profile.presenter;

import io.reactivex.Completable;
import io.reactivex.Single;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.imageprofile.UpdateImageRequest;
import xyz.gaborohez.socialnetwork.ui.base.BaseView;

public interface EditProfileContract {
    interface View extends BaseView {

        void imageUpdated(String message);
    }

    interface Presenter {

        void updateImageProfile(String image);

        void updateImageCover(String image);
    }

    interface Interactor {

        Single<BaseResponse> updateImageProfile(UpdateImageRequest request);

        Single<BaseResponse> updateImageCover(UpdateImageRequest request);
    }
}
