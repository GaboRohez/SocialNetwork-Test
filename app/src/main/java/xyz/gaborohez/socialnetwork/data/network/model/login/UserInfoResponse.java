package xyz.gaborohez.socialnetwork.data.network.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;

public class UserInfoResponse extends BaseResponse {


    @Expose
    @SerializedName("user")
    private User user;
    @Expose
    @SerializedName("followed")
    private boolean followed;
    @Expose
    @SerializedName("following")
    private boolean following;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    @Override
    public String toString() {
        return "UserInfoResponse{" +
                "user=" + user +
                ", followed=" + followed +
                ", following=" + following +
                '}';
    }
}
