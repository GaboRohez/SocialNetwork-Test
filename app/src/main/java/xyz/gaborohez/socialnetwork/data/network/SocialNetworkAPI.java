package xyz.gaborohez.socialnetwork.data.network;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.login.LogInRequest;
import xyz.gaborohez.socialnetwork.data.network.model.login.LogInResponse;
import xyz.gaborohez.socialnetwork.data.network.model.signin.SignInRequest;

public interface SocialNetworkAPI {

    @Headers({"Accept: application/json", "Connection: close"})
    @POST("user")
    Single<BaseResponse> signIn(@Body SignInRequest request);

    @Headers({"Accept: application/json", "Connection: close"})
    @POST("session")
    Single<LogInResponse> logIn(@Body LogInRequest request);

}
