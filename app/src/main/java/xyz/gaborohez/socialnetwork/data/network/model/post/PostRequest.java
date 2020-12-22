package xyz.gaborohez.socialnetwork.data.network.model.post;

import com.google.gson.annotations.SerializedName;

public class PostRequest {

    @SerializedName("image")
    private String image;
    @SerializedName("text")
    private String text;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PostRequest{" +
                "image='" + image + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
