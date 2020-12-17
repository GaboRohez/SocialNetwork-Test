package xyz.gaborohez.socialnetwork.data.network.model.imageprofile;

import com.google.gson.annotations.SerializedName;

public class UpdateImageRequest {
    @SerializedName("image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "UpdateImageRequest{" +
                "image='" + image + '\'' +
                '}';
    }
}
