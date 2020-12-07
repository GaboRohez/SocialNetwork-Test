package xyz.gaborohez.socialnetwork.ui.base;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.SocketTimeoutException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import xyz.gaborohez.socialnetwork.R;
import xyz.gaborohez.socialnetwork.data.network.RetrofitClient;
import xyz.gaborohez.socialnetwork.data.network.model.ResponseError;


/**
 * Helper class for Presenters based on MVP pattern
 *
 * @param <T> - Presenter Class </T>
 */

public abstract class BasePresenter<T> {

    protected T view = null;
    private BaseView baseView;
    private CompositeDisposable mCompositeDisposable;

    protected boolean isViewAttached() {
        return view != null;
    }

    public BasePresenter(T view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    protected void addSubscription(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    void detachView() {
        this.view = null;
        mCompositeDisposable.clear();
    }

    protected int processError(Throwable throwable) {
        throwable.printStackTrace();
        if (throwable instanceof SocketTimeoutException) {
            return R.string.retrofit_timeout;
        } else {
            return R.string.retrofit_failure;
        }
    }

    protected String handlerError(Throwable throwable) throws IOException {

        ResponseBody body = ((HttpException) throwable).response().errorBody();

        Converter<ResponseBody, ResponseError> errorConverter = RetrofitClient.singleAPI().responseBodyConverter(ResponseError.class, new Annotation[0]);

        ResponseError error = errorConverter.convert(body);
        Log.i("","ERROR: " + error.getMessage());
        return error.getMessage();

    }
}