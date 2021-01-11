package xyz.gaborohez.socialnetwork.ui.update_user_info.presenter;

import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.update_user_info.interactor.UpdateInfoInteractor;

/**
 * Created by Gabriel Rodríguez on 11/01/21.
 * Email gabow95k@gmail.com
 */
public class UpdateInfoPresenter extends BasePresenter<UpdateInfoContract.View> implements UpdateInfoContract.Presenter {

    private final UpdateInfoContract.Interactor interactor;

    public UpdateInfoPresenter(UpdateInfoContract.View view) {
        super(view);
        interactor = new UpdateInfoInteractor();
    }
}
