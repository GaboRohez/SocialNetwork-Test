package xyz.gaborohez.socialnetwork.ui.profile.presenter;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import xyz.gaborohez.socialnetwork.data.models.Follow;
import xyz.gaborohez.socialnetwork.data.network.model.follows.FollowsResponse;
import xyz.gaborohez.socialnetwork.data.network.model.login.UserInfoResponse;
import xyz.gaborohez.socialnetwork.ui.base.BaseView;

public interface ProfileContract {
    interface View extends BaseView {

        void setCounters(Follow result);
    }

    interface Presenter {

        void getCounters();
    }

    interface Interactor {

        Single<FollowsResponse> getCounters();
    }
}
