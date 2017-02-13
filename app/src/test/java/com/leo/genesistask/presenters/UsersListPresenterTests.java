package com.leo.genesistask.presenters;

import com.leo.genesistask.contract.UsersListContract;
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
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by leonid on 2/13/17.
 */

public class UsersListPresenterTests extends BaseUnitTest {

    @Mock
    private Repository mRepository;
    @Mock
    private UsersListContract.View mView;
    private UsersListPresenter mPresenter;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        //initialize presenter
        mPresenter = new UsersListPresenter(mView, mRepository);
    }


    /**
     * Check if presenter fires {@link UsersListContract.View#onUsersLoaded(List)}
     * when receives list of users
     */
    @Test
    public void getUsers_success() {
        // construct valid response
        List<User> users = new ArrayList<>();
        users.add(new User());

        when(mRepository.getUsers(anyInt())).thenReturn(users);

        mPresenter.loadUsersList(anyInt());

        verify(mView, times(1)).onUsersLoaded(any());
    }


    /**
     * Check if presenter fires {@link UsersListContract.View#onUsersLoadError(Throwable)}
     * in case of error
     */
    @Test
    public void getUsers_error() {
        when(mRepository.getUsers(anyInt())).thenThrow(new MockitoException("Ups..."));

        mPresenter.loadUsersList(anyInt());

        verify(mView, times(1)).onUsersLoadError(any());
    }

    /**
     * Check if presenter fires {@link UsersListContract.View#onUsersLoadError(Throwable)}
     * with {@link com.leo.genesistask.exception.LoadUsersException} as a parameter
     * when receives corrupted data
     */
    @Test
    public void getUsers_wrongData() {
        // server returns null response
        when(mRepository.getUsers(anyInt())).thenReturn(null);
        mPresenter.loadUsersList(anyInt());
        // server returns empty list
        when(mRepository.getUsers(anyInt())).thenReturn(new ArrayList<>());
        mPresenter.loadUsersList(anyInt());

        verify(mView, times(2)).onUsersLoadError(any(LoadUsersException.class));
    }
}
