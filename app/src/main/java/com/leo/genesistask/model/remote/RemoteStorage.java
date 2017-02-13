package com.leo.genesistask.model.remote;

import android.util.Log;

import com.leo.genesistask.model.pojo.User;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Remote storage.
 * Get all data from the server here
 *
 * Created by leonid on 2/12/17.
 */

public class RemoteStorage {

    private static final String API_BASE_URL = "https://api.github.com/";
    private static final String LOG_TAG = RemoteStorage.class.getSimpleName();
    private ServerApi mApi;

    public RemoteStorage() {
        mApi = getServerApi();
    }

    private ServerApi getServerApi() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(API_BASE_URL)
                .build();
        return mRetrofit.create(ServerApi.class);
    }


    /**
     * Executes get users request
     *
     * @param sinceUserId id of the last user. Group of consecutive users will be loaded.
     *                    Pass in 0 to load users from the beginning.
     * @return list of {@link User}
     */
    public List<User> getUsers(int sinceUserId) {
        Call<List<User>> request = mApi.getUsersList(sinceUserId);
        try {
            Response<List<User>> response = request.execute();
            if (response.isSuccessful()) {
                //successfully retrieved users
                return response.body();
            } else {
                Log.e(LOG_TAG, "getUsers fail");
                return null;
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "getUsers error " + e);
            return null;
        }
    }

    /**
     * Executes get followers of a user request
     *
     * @param login user login
     * @return list of {@link User}
     */
    public List<User> getFollowers(String login) {
        Call<List<User>> request = mApi.getUserFollowers(login);
        try {
            Response<List<User>> response = request.execute();
            if (response.isSuccessful()) {
                //successfully retrieved user followers
                return response.body();
            } else {
                Log.e(LOG_TAG, "getFollowers fail");
                return null;
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "getFollowers error " + e);
            return null;
        }
    }
}
