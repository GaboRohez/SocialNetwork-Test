package xyz.gaborohez.socialnetwork.data.models;

import com.google.gson.annotations.SerializedName;

public class Follow {
    @SerializedName("post")
    private int post;
    @SerializedName("followed")
    private int followed;
    @SerializedName("following")
    private int following;

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getFollowed() {
        return followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    @Override
    public String
    toString() {
        return "Follow{" +
                "post=" + post +
                ", followed=" + followed +
                ", following=" + following +
                '}';
    }
}
