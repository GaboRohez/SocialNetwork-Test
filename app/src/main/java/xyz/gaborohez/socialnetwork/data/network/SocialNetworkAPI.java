package xyz.gaborohez.socialnetwork.data.network;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import xyz.gaborohez.socialnetwork.data.network.model.LogInRequest;
import xyz.gaborohez.socialnetwork.data.network.model.LogInResponse;

public interface SocialNetworkAPI {

    @Headers({"Accept: application/json", "Connection: close"})
    @POST("session")
    Single<LogInResponse> logIn(@Body LogInRequest request);

}
