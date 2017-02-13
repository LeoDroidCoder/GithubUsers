package com.leo.genesistask.model;

import com.leo.genesistask.model.local.LocalStorage;
import com.leo.genesistask.model.pojo.User;
import com.leo.genesistask.model.remote.RemoteStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Main place to take any data
 * <p>
 * Created by leonid on 2/12/17.
 */

public class Repository {

    private RemoteStorage mRemoteStorage;
    // not used now but will be needed later when we store data locally
    private LocalStorage mLocalStorage;

    public Repository(LocalStorage localStorage, RemoteStorage remoteStorage) {
        mLocalStorage = localStorage;
        mRemoteStorage = remoteStorage;
    }

    /**
     * Loads users from the remote.
     *
     * @param usersAmount amount of users to load
     * @return list of unsorted {@link List<User>}
     */
    public List<User> getUsers(int usersAmount) {
        List<User> users = new ArrayList<>();
        int lastUserId = 0;
        // keep loading users from the remote storage until reach needed amount
        while (users.size() < usersAmount) {
            // load 30 users from the cloud
            users.addAll(mRemoteStorage.getUsers(lastUserId));
            // remember id of the last user for the next request
            lastUserId = users.size() > 0 ? users.get(users.size() - 1).getId() : 0;
        }
        // remove unneeded users if uploaded more then required
        if (users.size() > usersAmount) {
            users.subList(usersAmount, users.size()).clear();
        }
        return users;
    }

    /**
     * Loads user followers from the remote.
     *
     * @param userLogin user login
     * @return list of followers
     */
    public List<User> getFollowers(String userLogin) {
        return mRemoteStorage.getFollowers(userLogin);
    }

}
