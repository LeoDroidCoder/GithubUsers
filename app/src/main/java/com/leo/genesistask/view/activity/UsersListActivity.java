package com.leo.genesistask.view.activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.leo.genesistask.App;
import com.leo.genesistask.R;
import com.leo.genesistask.contract.UsersListContract;
import com.leo.genesistask.databinding.ActivityUsersListBinding;
import com.leo.genesistask.model.Repository;
import com.leo.genesistask.model.pojo.User;
import com.leo.genesistask.presenters.UsersListPresenter;
import com.leo.genesistask.view.adapter.UsersAdapter;
import com.leo.genesistask.view.custom.LoadingProgressDialog;

import java.util.List;

import javax.inject.Inject;

/**
 * Users list screen
 * <p>
 * Created by leonid on 2/12/17.
 */
public class UsersListActivity extends BaseActivity implements UsersListContract.View, UsersAdapter.OnUserClickListener {

    /**
     * Amount of users to load
     */
    private static final int USERS_AMOUNT_TO_LOAD = 200;
    @Inject
    protected Repository mRepository;
    private ActivityUsersListBinding mBinding;
    private UsersListPresenter mPresenter;
    private UsersAdapter mAdapter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_users_list);
        injectDependencies();
        setupViews();

        // initialize presenter
        mPresenter = new UsersListPresenter(mRepository, this);
        // load users
        mProgressDialog.show();
        mPresenter.loadUsersList(USERS_AMOUNT_TO_LOAD);
    }

    /**
     * Setup views
     */
    private void setupViews() {
        // initialize progress dialog
        mProgressDialog = new LoadingProgressDialog(this);
        // setup adapter and the RecyclerView
        mAdapter = new UsersAdapter(this);
        mAdapter.setUserItemClickListener(this);
        mBinding.rvUsers.setAdapter(mAdapter);
        mBinding.rvUsers.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvUsers.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // setup retry button to load users again in case of failure
        mBinding.btnRetry.setOnClickListener(view -> mPresenter.loadUsersList(USERS_AMOUNT_TO_LOAD));
    }

    /**
     * Inject dependencies
     */
    private void injectDependencies() {
        ((App) getApplication()).getAppComponent().inject(this);
    }

    /**
     * Presenter returned valid list of users.
     *
     * @param users list of {@link User}
     */
    @Override
    public void onUsersLoaded(List<User> users) {
        mProgressDialog.dismiss();
        // feed the adapter with the data
        mAdapter.setItems(users);
        setUpRecyclerAppearence();
    }

    /**
     * Presenter failed to provide users list
     *
     * @param error error
     */
    @Override
    public void onUsersLoadError(Throwable error) {
        mProgressDialog.dismiss();
        // notify user with human-readable message
        showError(error);
        setUpRecyclerAppearence();
    }

    /**
     * Followers button clicked on a User item.
     * Navigate to the followers screen
     *
     * @param userLogin user login
     */
    @Override
    public void onUserFollowersClicked(String userLogin) {
        mNavigator.navigateToUserFollowersActivity(this, userLogin);
    }

    /**
     * Shows users list RecyclerView or hides it and shows empty view
     * depending on data set size
     */
    private void setUpRecyclerAppearence() {
        boolean empty = mAdapter.getItemCount() == 0;
        mBinding.rvUsers.setVisibility(empty ? View.GONE : View.VISIBLE);
        mBinding.tvEmpty.setVisibility(empty ? View.VISIBLE : View.GONE);
        mBinding.btnRetry.setVisibility(empty ? View.VISIBLE : View.GONE);
    }
}
