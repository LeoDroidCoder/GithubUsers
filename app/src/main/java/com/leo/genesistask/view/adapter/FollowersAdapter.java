package com.leo.genesistask.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo.genesistask.R;
import com.leo.genesistask.databinding.ItemFollowerBinding;
import com.leo.genesistask.model.pojo.User;

import java.util.Collections;
import java.util.List;

/**
 * Followers adapter
 * <p>
 * Created by leonid on 2/12/17.
 */

public class FollowersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnFollowerClickListener {
        /**
         * Open url button clicked on a User item
         *
         * @param user clicked user
         */
        void onShowInBrowserClicked(User user);
    }

    private OnFollowerClickListener mListener;
    private List<User> mFollowers;
    private final LayoutInflater mLayoutInflater;

    public FollowersAdapter(Context context) {
        mLayoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFollowers = Collections.emptyList();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.item_follower, parent, false);
        return new FollowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FollowerViewHolder) holder).onBind(mFollowers.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mFollowers != null ? mFollowers.size() : 0;
    }


    /**
     * Set user data here
     *
     * @param users {@link User}
     */
    public void setItems(List<User> users) {
        mFollowers = users;
        notifyDataSetChanged();
    }

    public void setUserItemClickListener(OnFollowerClickListener listener) {
        mListener = listener;
    }

    private static class FollowerViewHolder extends RecyclerView.ViewHolder {

        private ItemFollowerBinding mBinding;

        FollowerViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        void onBind(User user, OnFollowerClickListener listener) {
            mBinding.setUser(user);
            mBinding.setImageLink(user.getAvatarUrl());
            mBinding.btnShowInBrowser.setOnClickListener(view -> {
                if (null != listener) {
                    listener.onShowInBrowserClicked(user);
                }
            });
            mBinding.executePendingBindings();
        }
    }

}
