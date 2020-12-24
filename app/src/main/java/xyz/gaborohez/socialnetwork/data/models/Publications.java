package xyz.gaborohez.socialnetwork.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Publications implements Parcelable {
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("user")
    private User user;
    @SerializedName("file")
    private String file;
    @SerializedName("text")
    private String text;
    @SerializedName("_id")
    private String id;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n --------------------------------------\n" +
                "Publications{" +
                "id='" + id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }

    protected Publications(Parcel in) {
        created_at = in.readString();
        file = in.readString();
        text = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(created_at);
        dest.writeString(file);
        dest.writeString(text);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Publications> CREATOR = new Creator<Publications>() {
        @Override
        public Publications createFromParcel(Parcel in) {
            return new Publications(in);
        }

        @Override
        public Publications[] newArray(int size) {
            return new Publications[size];
        }
    };

}
