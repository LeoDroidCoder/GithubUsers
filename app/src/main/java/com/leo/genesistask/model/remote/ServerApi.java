package com.leo.genesistask.model.remote;

import com.leo.genesistask.model.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by leonid on 2/12/17.
 */

public interface ServerApi {

    /**
     * Get list of 30 users.
     *
     * @param sinceUserId id of the last user. Group of consecutive users will be loaded.
     *                    Pass in 0 to load users from the beginning.
     * @return list of {@link User}
     */
    @GET("users")
    Call<List<User>> getUsersList(@Query("since") int sinceUserId);

    /**
     * Get followers of a user
     *
     * @param login user login
     * @return list of {@link User}
     */
    @GET("users/{userLogin}/followers")
    Call<List<User>> getUserFollowers(@Path("userLogin") String login);

}
