package xyz.gaborohez.socialnetwork.data.network;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;
import xyz.gaborohez.socialnetwork.data.network.model.follows.FollowsResponse;
import xyz.gaborohez.socialnetwork.data.network.model.imageprofile.UpdateImageRequest;
import xyz.gaborohez.socialnetwork.data.network.model.login.LogInRequest;
import xyz.gaborohez.socialnetwork.data.network.model.login.LogInResponse;
import xyz.gaborohez.socialnetwork.data.network.model.login.UserInfoResponse;
import xyz.gaborohez.socialnetwork.data.network.model.post.PostsResponse;
import xyz.gaborohez.socialnetwork.data.network.model.post.PostRequest;
import xyz.gaborohez.socialnetwork.data.network.model.signin.SignInRequest;

public interface SocialNetworkAPI {

    @Headers({"Accept: application/json", "Connection: close"})
    @POST("user")
    Single<BaseResponse> signIn(@Body SignInRequest request);

    @Headers({"Accept: application/json", "Connection: close"})
    @POST("session")
    Single<LogInResponse> logIn(@Body LogInRequest request);

    @GET("user")
    Single<UserInfoResponse> getCurrentUser();

    @GET("counters")
    Single<FollowsResponse> getCounters();

    @PUT("user-image")
    Single<BaseResponse> updateImageProfile(@Body UpdateImageRequest request);

    @PUT("user-cover")
    Single<BaseResponse> updateImageCover(@Body UpdateImageRequest request);

    @POST("publication")
    Single<BaseResponse> createPost(@Body PostRequest request);

    @GET("publications/{page}")
    Single<PostsResponse> getPost(@Path("page") int page);

    @DELETE("publication/{id}")
    Single<BaseResponse> deletePost(@Path("id") String id);
}
