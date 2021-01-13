package xyz.gaborohez.socialnetwork.data.network.model.people;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;

public class PeopleResponse extends BaseResponse implements Parcelable {


    @SerializedName("users")
    private List<User> users;
    @SerializedName("totalPages")
    private int totalPages;
    @SerializedName("actualPage")
    private int actualPage;
    @SerializedName("total")
    private int total;

    protected PeopleResponse(Parcel in) {
        users = in.createTypedArrayList(User.CREATOR);
        totalPages = in.readInt();
        actualPage = in.readInt();
        total = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(users);
        dest.writeInt(totalPages);
        dest.writeInt(actualPage);
        dest.writeInt(total);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PeopleResponse> CREATOR = new Creator<PeopleResponse>() {
        @Override
        public PeopleResponse createFromParcel(Parcel in) {
            return new PeopleResponse(in);
        }

        @Override
        public PeopleResponse[] newArray(int size) {
            return new PeopleResponse[size];
        }
    };

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> user) {
        this.users = user;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getActualPage() {
        return actualPage;
    }

    public void setActualPage(int actualPage) {
        this.actualPage = actualPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PeopleResponse{" +
                "users=" + users +
                ", totalPages=" + totalPages +
                ", actualPage=" + actualPage +
                ", total=" + total +
                '}';
    }
}
