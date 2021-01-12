package xyz.gaborohez.socialnetwork.data.network.model.user;

import com.google.gson.annotations.SerializedName;

public class UpdateNameRequest {

    @SerializedName("surname")
    private String surname;
    @SerializedName("name")
    private String name;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
