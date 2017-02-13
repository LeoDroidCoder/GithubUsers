package com.leo.genesistask.di;

import android.content.Context;

import com.leo.genesistask.model.Repository;
import com.leo.genesistask.model.local.LocalStorage;
import com.leo.genesistask.model.remote.RemoteStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by leonid on 2/12/17.
 */

@Module
public class AppModule {

    private final Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Singleton @Provides
    Repository provideRepository() {
        return new Repository(new LocalStorage(mContext), new RemoteStorage());
    }

}
