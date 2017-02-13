package com.leo.genesistask.presenters;

import com.leo.genesistask.contract.UserFollowersContract;
import com.leo.genesistask.exception.LoadUsersException;
import com.leo.genesistask.model.Repository;
import com.leo.genesistask.model.pojo.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by leonid on 2/13/17.
 */

public class UserFollowersPresenterTests extends BaseUnitTest {

    @Mock
    private Repository mRepository;
    @Mock
    private UserFollowersContract.View mView;
    private UserFollowersPresenter mPresenter;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        //initialize presenter
        mPresenter = new UserFollowersPresenter(mView, mRepository);
    }


    /**
     * Check if presenter fires {@link UserFollowersContract.View#onFollowersLoaded(List)}
     * when receives list of followers
     */
    @Test
    public void getFollowers_success() {
        // construct valid response
        List<User> followers = new ArrayList<>();
        followers.add(new User());

        when(mRepository.getFollowers(anyString())).thenReturn(followers);

        mPresenter.getUserFollowers(anyString());

        verify(mView, times(1)).onFollowersLoaded(any());
    }


    /**
     * Check if presenter fires {@link UserFollowersContract.View#onFollowersError(Throwable)}
     * in case of error
     */
    @Test
    public void getUsers_error() {
        when(mRepository.getFollowers(anyString())).thenThrow(new MockitoException("Ups..."));

        mPresenter.getUserFollowers(anyString());

        verify(mView, times(1)).onFollowersError(any());
    }

    /**
     * Check if presenter fires {@link UserFollowersContract.View#onFollowersError(Throwable)}
     * with {@link com.leo.genesistask.exception.LoadFollowersException} as a parameter
     * when receives corrupted data
     */
    @Test
    public void getUsers_wrongData() {
        // server returns null response
        when(mRepository.getFollowers(anyString())).thenReturn(null);
        mPresenter.getUserFollowers(anyString());

        verify(mView, times(1)).onFollowersError(any(LoadUsersException.class));
    }

}
