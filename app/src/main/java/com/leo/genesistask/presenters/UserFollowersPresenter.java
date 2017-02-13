package com.leo.genesistask.presenters;

import com.leo.genesistask.contract.UserFollowersContract;
import com.leo.genesistask.exception.LoadFollowersException;
import com.leo.genesistask.helpers.AppSchedulers;
import com.leo.genesistask.model.Repository;

import rx.Single;

/**
 * Created by leonid on 2/12/17.
 */

public class UserFollowersPresenter implements UserFollowersContract.Presenter {

    private Repository mRepository;
    private UserFollowersContract.View mView;

    public UserFollowersPresenter(Repository repository, UserFollowersContract.View view) {
        mRepository = repository;
        mView = view;
    }


    /**
     * Get user followers
     *
     * @param userLogin user login
     */
    @Override
    public void getUserFollowers(String userLogin) {
        Single.fromCallable(() -> mRepository.getFollowers(userLogin))
                .subscribeOn(AppSchedulers.io())
                .observeOn(AppSchedulers.mainThread())
                .subscribe(followers -> {
                    if (null != followers) {
                        // received valid data
                        mView.onFollowersLoaded(followers);
                    } else {
                        // failed to receive valid data
                        mView.onFollowersError(new LoadFollowersException());
                    }
                }, error -> mView.onFollowersError(error));
    }
}
