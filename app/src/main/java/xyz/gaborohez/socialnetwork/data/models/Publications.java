package xyz.gaborohez.socialnetwork.data.models;

import com.google.gson.annotations.SerializedName;

public class Publications {
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
}
