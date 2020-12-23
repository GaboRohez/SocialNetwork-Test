package xyz.gaborohez.socialnetwork.data.network.model.post;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import xyz.gaborohez.socialnetwork.data.models.Publications;

public class PostsResponse {

    @SerializedName("publications")
    private List<Publications> publications;
    @SerializedName("totalPages")
    private int totalPages;
    @SerializedName("actualPage")
    private int actualPage;
    @SerializedName("total")
    private int total;

    public List<Publications> getPublications() {
        return publications;
    }

    public void setPublications(List<Publications> publications) {
        this.publications = publications;
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
        return "\n --------------------------------------\n" +
                "GetPostResponse{" +
                "totalPages=" + totalPages +
                ", actualPage=" + actualPage +
                ", total=" + total +
                ", publications=" + publications +
                '}';
    }
}
