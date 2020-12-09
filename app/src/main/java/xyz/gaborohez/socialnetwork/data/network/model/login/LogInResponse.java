package xyz.gaborohez.socialnetwork.data.network.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;

public class LogInResponse extends BaseResponse {
    @Expose
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LogInResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
