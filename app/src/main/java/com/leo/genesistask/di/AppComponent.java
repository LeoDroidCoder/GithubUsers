package com.leo.genesistask.di;

import com.leo.genesistask.view.activity.UserFollowersActivity;
import com.leo.genesistask.view.activity.UsersListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by leonid on 2/12/17.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(UsersListActivity activity);

    void inject(UserFollowersActivity activity);

}
