package xyz.gaborohez.socialnetwork.ui.update_user_info.presenter;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.user.UpdateNameRequest;
import xyz.gaborohez.socialnetwork.ui.base.BaseView;

/**
 * Created by Gabriel Rodríguez on 11/01/21.
 * Email gabow95k@gmail.com
 */
public interface UpdateInfoContract {
    interface View extends BaseView {

        void updated(String message);
    }

    interface Presenter {
        void updateName(UpdateNameRequest request);
    }

    interface Interactor {

        Single<BaseResponse> updateName(UpdateNameRequest request);
    }
}
