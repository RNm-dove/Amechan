package org.cord4handai.amechan.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ryosuke on 2018/03/08.
 */

public interface ClaimService{

    @GET("search")
    Single<ListClaim> getClaims();

    @GET("comment")
    Single<ListComment> getComments(@Query("id") int id);


    public static class ListClaim {
        @Expose
        @SerializedName("claim")
        public final List<Claim> items;

        public ListClaim(List<Claim> items) {
            this.items = items;
        }
    }

    public static class Claim implements Serializable{
        @Expose
        @SerializedName("id")
        public final Integer id;

        @Expose
        @SerializedName("content")
        public final String content;

        @Expose
        @SerializedName("sex")
        public final Integer sex;

        @Expose
        @SerializedName("age")
        public final Integer age;

        @Expose
        @SerializedName("submit")
        public final Integer submit;

        public String toString() {
            return "id:" + id + "content:" + content + "sex:" + String.valueOf(sex) + "age:" + String.valueOf(age) + "submit:" + String.valueOf(submit);
        }


        public Claim(Integer id , String content, Integer sex, Integer age, Integer submit) {
            this.id = id;
            this.content = content;
            this.sex = sex;
            this.age = age;
            this.submit = submit;
        }

    }

    public static class ListComment{
        @Expose
        @SerializedName("claim")
        public final List<Comment> items;

        public ListComment(List<Comment> items) {
            this.items = items;
        }
    }

    public static class Comment{

        @Expose
        @SerializedName("user_name")
        public final String userName;

        @Expose
        @SerializedName("content")
        public final String content;

        public Comment(String userName, String content) {
            this.userName = userName;
            this.content = content;
        }

        @Override
        public String toString() {
            return "user_name:" + userName + "content:" + content;
        }
    }

}

