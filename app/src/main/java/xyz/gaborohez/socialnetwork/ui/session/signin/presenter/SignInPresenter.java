package xyz.gaborohez.socialnetwork.ui.session.signin.presenter;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import xyz.gaborohez.socialnetwork.app.SocialApp;
import xyz.gaborohez.socialnetwork.constants.AppConstants;
import xyz.gaborohez.socialnetwork.data.network.model.signin.SignInRequest;
import xyz.gaborohez.socialnetwork.data.prefs.PreferencesManager;
import xyz.gaborohez.socialnetwork.ui.base.BasePresenter;
import xyz.gaborohez.socialnetwork.ui.session.signin.interactor.SignInInteractor;
import xyz.gaborohez.socialnetwork.ui.utils.AppUtils;

import static xyz.gaborohez.socialnetwork.constants.AppConstants.SUCCESS;

public class SignInPresenter extends BasePresenter<SignInContract.View> implements SignInContract.Presenter{

    private SignInContract.Interactor interactor;

    public SignInPresenter(SignInContract.View view) {
        super(view);
        interactor = new SignInInteractor();
    }

    @Override
    public void signIn(String name, String surname, String nickname, String email, String password, String confPassword) {

        if (!AppUtils.isValidEmail(email)){
            view.emailError(SocialApp.resourcesManager.getEmailErrorMessage());
            return;
        }

        if (password.length() < 6){
            view.passError(SocialApp.resourcesManager.getPasswordIncorrect());
            return;
        }

        if (!password.equals(confPassword)){
            view.cPassError(SocialApp.resourcesManager.getPasswordErrorMessage());
            return;
        }

        SignInRequest request = new SignInRequest();
        request.setName(name);
        request.setSurname(surname);
        request.setNick(nickname);
        request.setEmail(email);
        request.setPassword(password);

        addSubscription(interactor.signIn(request)
                .doOnSubscribe(disposable -> view.showLoader(true))
                .doAfterTerminate(() -> view.showLoader(false))
                .subscribe(response -> {
                    if (response.getCode().equals(SUCCESS)){
                       view.userCreated();
                    }else  {
                        view.showAlertDialog(response.getMessage());
                    }
                }, throwable -> {
                    if (throwable instanceof HttpException) {
                        // handle message
                        view.showAlertDialog(handlerError(throwable));
                    } else if (throwable instanceof SocketTimeoutException) {
                        // handle timeout from Retrofit
                        view.showAlertDialog(processError(throwable));
                    }else {
                        view.showAlertDialog(SocialApp.resourcesManager.getErrorServer());
                    }
                }));
    }
}
