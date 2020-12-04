package xyz.gaborohez.socialnetwork.ui.base;

import java.net.SocketTimeoutException;

import xyz.gaborohez.socialnetwork.R;


/**
 * Helper class for Presenters based on MVP pattern
 *
 * @param <T> - Presenter Class </T>
 */

public abstract class BasePresenter<T> {

    protected T view = null;
    private BaseView baseView;
    //private CompositeDisposable mCompositeDisposable;

    protected boolean isViewAttached() {
        return view != null;
    }

    public BasePresenter(T view) {
        this.view = view;
        //mCompositeDisposable = new CompositeDisposable();
    }

    /*protected void addSubscription(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }*/

    void detachView() {
        this.view = null;
        //mCompositeDisposable.clear();
    }

    protected int processError(Throwable throwable) {
        throwable.printStackTrace();
        if (throwable instanceof SocketTimeoutException) {
            return R.string.retrofit_timeout;
        } else {
            return R.string.retrofit_failure;
        }
    }
}