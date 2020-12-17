package xyz.gaborohez.socialnetwork.data.network.model.follows;

import com.google.gson.annotations.SerializedName;

import xyz.gaborohez.socialnetwork.data.models.Follow;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;

public class FollowsResponse extends BaseResponse {


    @SerializedName("follow")
    private Follow follow;

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }

    @Override
    public String toString() {
        return "FollowsResponse{" +
                "follow=" + follow +
                '}';
    }
}
