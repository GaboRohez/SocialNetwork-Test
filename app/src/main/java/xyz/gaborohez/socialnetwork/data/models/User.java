package xyz.gaborohez.socialnetwork.data.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @Expose
    @SerializedName("_id")
    private String id;
    private String image;
    private String role;
    private String email;
    private String nick;
    private String surname;
    private String name;
    private String cover;

    protected User(Parcel in) {
        id = in.readString();
        image = in.readString();
        role = in.readString();
        email = in.readString();
        nick = in.readString();
        surname = in.readString();
        name = in.readString();
        cover = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(image);
        dest.writeString(role);
        dest.writeString(email);
        dest.writeString(nick);
        dest.writeString(surname);
        dest.writeString(name);
        dest.writeString(cover);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public Bitmap getImage() {
        if (image != null){
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }else {
            return null;
        }

    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getCover() {
        if (cover != null){
            byte[] decodedString = Base64.decode(cover, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }else {
            return null;
        }
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "User{" +"\n"+
                "id='" + id + '\'' +"\n"+
                ", role='" + role + '\'' +"\n"+
                ", email='" + email + '\'' +"\n"+
                ", nick='" + nick + '\'' +"\n"+
                ", surname='" + surname + '\'' +"\n"+
                ", name='" + name + '\'' +"\n"+
                '}';
    }
}
