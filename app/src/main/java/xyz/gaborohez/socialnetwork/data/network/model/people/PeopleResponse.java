package xyz.gaborohez.socialnetwork.data.network.model.people;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import xyz.gaborohez.socialnetwork.data.models.User;
import xyz.gaborohez.socialnetwork.data.network.global.BaseResponse;

public class PeopleResponse extends BaseResponse {


    @SerializedName("users")
    private List<User> users;
    @SerializedName("totalPages")
    private int totalPages;
    @SerializedName("actualPage")
    private int actualPage;
    @SerializedName("total")
    private int total;

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
