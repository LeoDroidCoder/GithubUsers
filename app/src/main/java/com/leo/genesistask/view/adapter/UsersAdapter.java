package com.leo.genesistask.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo.genesistask.R;
import com.leo.genesistask.databinding.ItemUserLeftAvatarBinding;
import com.leo.genesistask.databinding.ItemUserRightAvatarBinding;
import com.leo.genesistask.model.pojo.User;

import java.util.Collections;
import java.util.List;

/**
 * Users adapter
 * <p>
 * Created by leonid on 2/12/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_LEFT_AVATAR = 1;
    private static final int VIEW_TYPE_RIGHT_AVATAR = 2;

    public interface OnUserClickListener {
        /**
         * Followers button clicked on a User item
         *
         * @param userLogin user login
         */
        void onUserFollowersClicked(String userLogin);
    }

    private OnUserClickListener mListener;
    private List<User> mUsers;
    private final LayoutInflater mLayoutInflater;

    public UsersAdapter(Context context) {
        mLayoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mUsers = Collections.emptyList();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;

        if (viewType == VIEW_TYPE_LEFT_AVATAR) {
            View view = mLayoutInflater.inflate(R.layout.item_user_left_avatar, parent, false);
            viewHolder = new LeftAvatarViewHolder(view);
        } else {
            View view = mLayoutInflater.inflate(R.layout.item_user_right_avatar, parent, false);
            viewHolder = new RightAvatarViewHolder(view);
        }

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((UserViewHolder) holder).onBind(mUsers.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return ++position % 3 == 0 ? VIEW_TYPE_RIGHT_AVATAR : VIEW_TYPE_LEFT_AVATAR;
    }

    /**
     * Set user data here
     *
     * @param users {@link User}
     */
    public void setItems(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    public void setUserItemClickListener(OnUserClickListener listener) {
        mListener = listener;
    }

    private static class LeftAvatarViewHolder extends UserViewHolder {

        private ItemUserLeftAvatarBinding mBinding;

        LeftAvatarViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        void onBind(User user, OnUserClickListener listener) {
            mBinding.setUser(user);
            mBinding.setImageLink(user.getAvatarUrl());
            mBinding.btnFollowers.setOnClickListener(view -> {
                if (null != listener) {
                    listener.onUserFollowersClicked(user.getLogin());
                }
            });
            mBinding.executePendingBindings();
        }
    }

    private static class RightAvatarViewHolder extends UserViewHolder {

        private ItemUserRightAvatarBinding mBinding;

        RightAvatarViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);

        }

        @Override
        void onBind(User user, OnUserClickListener listener) {
            mBinding.setUser(user);
            mBinding.setImageLink(user.getAvatarUrl());
            mBinding.btnFollowers.setOnClickListener(view -> {
                if (null != listener) {
                    listener.onUserFollowersClicked(user.getLogin());
                }
            });
            mBinding.executePendingBindings();
        }
    }

    private abstract static class UserViewHolder extends RecyclerView.ViewHolder {

        UserViewHolder(View itemView) {
            super(itemView);
        }

        abstract void onBind(User user, OnUserClickListener listener);
    }

}
