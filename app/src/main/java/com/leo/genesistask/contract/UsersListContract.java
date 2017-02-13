package com.leo.genesistask.contract;

import com.leo.genesistask.model.pojo.User;

import java.util.List;

/**
 * Created by leonid on 2/12/17.
 */

public interface UsersListContract {

    interface View extends BaseContract.View {

        /**
         * Presenter returned list of users
         *
         * @param users list of {@link com.leo.genesistask.model.pojo.User}
         */
        void onUsersLoaded(List<User> users);

        /**
         * Presenter failed to provide users list
         *
         * @param error error
         */
        void onUsersLoadError(Throwable error);

    }

    interface Presenter extends BaseContract.Presenter {

        /**
         * Load list of {@link com.leo.genesistask.model.pojo.User}
         *
         * @param numberOfUsers number of users to load
         */
        void loadUsersList(int numberOfUsers);
    }
}
