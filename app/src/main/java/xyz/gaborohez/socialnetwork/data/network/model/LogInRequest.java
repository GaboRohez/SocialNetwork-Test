package xyz.gaborohez.socialnetwork.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInRequest {

    @Expose
    @SerializedName("gettoken")
    private boolean gettoken;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("email")
    private String email;

    public LogInRequest() {
        this.gettoken = false;
    }

    public boolean getGettoken() {
        return gettoken;
    }

    public void setGettoken(boolean gettoken) {
        this.gettoken = gettoken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
