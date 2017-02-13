package com.leo.genesistask.presenters;

import com.leo.genesistask.contract.UsersListContract;
import com.leo.genesistask.exception.LoadUsersException;
import com.leo.genesistask.helpers.AppSchedulers;
import com.leo.genesistask.model.Repository;
import com.leo.genesistask.model.pojo.UsersComparator;

import java.util.Collections;

import rx.Single;

/**
 * Created by leonid on 2/12/17.
 */

public class UsersListPresenter implements UsersListContract.Presenter {

    private Repository mRepository;
    private UsersListContract.View mView;

    public UsersListPresenter(Repository repository, UsersListContract.View view) {
        mRepository = repository;
        mView = view;
    }

    /**
     * Load list of users from {@link Repository}
     * and sort them by login alphabetically with help of {@link UsersComparator}
     *
     * @param numberOfUsers number of users to load
     */
    @Override
    public void loadUsersList(int numberOfUsers) {
        Single.fromCallable(() -> mRepository.getUsers(numberOfUsers))
                .subscribeOn(AppSchedulers.io())
                .observeOn(AppSchedulers.mainThread())
                .map(users -> {
                    // sort users
                    Collections.sort(users, new UsersComparator());
                    return users;
                })
                .subscribe(sortedUsers -> {
                    if (null != sortedUsers && !sortedUsers.isEmpty()) {
                        // received valid users data
                        mView.onUsersLoaded(sortedUsers);
                    } else {
                        // received corrupted data
                        mView.onUsersLoadError(new LoadUsersException());
                    }
                }, error -> mView.onUsersLoadError(error));
    }
}