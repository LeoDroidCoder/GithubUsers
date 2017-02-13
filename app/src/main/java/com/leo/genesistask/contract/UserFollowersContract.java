package com.leo.genesistask.contract;

import com.leo.genesistask.model.Repository;
import com.leo.genesistask.model.pojo.User;

import java.util.List;

/**
 * Created by leonid on 2/12/17.
 */

public interface UserFollowersContract {

    interface View extends BaseContract.View {

        /**
         * {@link Repository} provided followers data
         *
         * @param followers list of followers
         */
        void onFollowersLoaded(List<User> followers);

        /**
         * {@link Repository} failed to provide followers
         *
         * @param error error
         */
        void onFollowersError(Throwable error);

    }

    interface Presenter extends BaseContract.Presenter {

        /**
         * Get user followers
         *
         * @param userLogin login
         */
        void getUserFollowers(String userLogin);
    }
}
