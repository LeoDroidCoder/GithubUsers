package com.leo.genesistask.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.leo.genesistask.App;
import com.leo.genesistask.R;
import com.leo.genesistask.contract.UserFollowersContract;
import com.leo.genesistask.databinding.ActivityUserFollowersBinding;
import com.leo.genesistask.helpers.IntentActionUtils;
import com.leo.genesistask.model.Repository;
import com.leo.genesistask.model.pojo.User;
import com.leo.genesistask.presenters.UserFollowersPresenter;
import com.leo.genesistask.view.adapter.FollowersAdapter;
import com.leo.genesistask.view.custom.LoadingProgressDialog;

import java.util.List;

import javax.inject.Inject;

/**
 * User followers screen
 * <p>
 * Created by leonid on 2/12/17.
 */
public class UserFollowersActivity extends BaseActivity implements UserFollowersContract.View, FollowersAdapter.OnFollowerClickListener {

    private static final String INTENT_EXTRA_USER_LOGIN = "INTENT_EXTRA_USER_LOGIN";
    private static final String INSTANCE_STATE_PARAM_USER_LOGIN = "INSTANCE_STATE_PARAM_USER_LOGIN";

    @Inject
    protected Repository mRepository;
    private ActivityUserFollowersBinding mBinding;
    private UserFollowersPresenter mPresenter;
    private FollowersAdapter mAdapter;
    private String mUserLogin;
    private ProgressDialog mProgressDialog;

    public static Intent getCallingIntent(Context context, String userLogin) {
        Intent intent = new Intent(context, UserFollowersActivity.class);
        intent.putExtra(INTENT_EXTRA_USER_LOGIN, userLogin);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_followers);
        injectDependencies();
        retrieveExtras(savedInstanceState);
        setupViews();

        // initialize presenter
        mPresenter = new UserFollowersPresenter(this, mRepository);
        // load data
        mPresenter.getUserFollowers(mUserLogin);
    }

    /**
     * Inject dependencies
     */
    private void injectDependencies() {
        ((App) getApplication()).getAppComponent().inject(this);
    }

    /**
     * Retrieving extra from saved instance state or from intent,
     * which started current activity
     *
     * @param savedInstanceState - saved instance state
     */
    private void retrieveExtras(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mUserLogin = getIntent().getStringExtra(INTENT_EXTRA_USER_LOGIN);
        } else {
            mUserLogin = savedInstanceState.getString(INSTANCE_STATE_PARAM_USER_LOGIN);
        }
    }

    /**
     * Setup views
     */
    private void setupViews() {
        // setup action bar
        if (null != getSupportActionBar()) {
            // show home button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // set title
            if (null != mUserLogin) {
                getSupportActionBar().setTitle(String.format(getString(R.string.followers_title_format), mUserLogin));
            }
        }
        // initialize progress dialog
        mProgressDialog = new LoadingProgressDialog(this);
        // setup adapter and the RecyclerView
        mAdapter = new FollowersAdapter(this);
        mAdapter.setUserItemClickListener(this);
        mBinding.rvFollowers.setAdapter(mAdapter);
        mBinding.rvFollowers.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvFollowers.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // setup retry button to load users again in case of failure
        mBinding.btnRetry.setOnClickListener(view -> mPresenter.getUserFollowers(mUserLogin));
    }


    /**
     * {@link Repository} provided followers data
     *
     * @param followers list of followers
     */
    @Override
    public void onFollowersLoaded(List<User> followers) {
        mProgressDialog.dismiss();
        // supply adapter with the data
        mAdapter.setItems(followers);
        checkEmptyUsersList();
    }

    /**
     * {@link Repository} failed to provide followers
     *
     * @param error error
     */
    @Override
    public void onFollowersError(Throwable error) {
        mProgressDialog.dismiss();
        // notify user with human-readable message
        showError(error);
        checkEmptyUsersList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // action bar home button pressed
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_right);
    }

    /**
     * Open url button clicked on a User item
     *
     * @param user clicked user
     */
    @Override
    public void onShowInBrowserClicked(User user) {
        // show user's web page in a browser
        IntentActionUtils.openUrl(this, user.getHtmlUrl(), String.format(getString(R.string.chooser_title_format), user.getLogin()));
    }

    /**
     * Shows followers list RecyclerView or hides it and shows empty view
     * depending on data set size
     */
    private void checkEmptyUsersList() {
        boolean empty = mAdapter.getItemCount() == 0;
        mBinding.rvFollowers.setVisibility(empty ? View.GONE : View.VISIBLE);
        mBinding.tvEmpty.setVisibility(empty ? View.VISIBLE : View.GONE);
        mBinding.btnRetry.setVisibility(empty ? View.VISIBLE : View.GONE);
    }
}
