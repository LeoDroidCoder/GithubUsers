package com.leo.genesistask;

import android.app.Application;

import com.leo.genesistask.di.AppComponent;
import com.leo.genesistask.di.AppModule;
import com.leo.genesistask.di.DaggerAppComponent;
import com.splunk.mint.Mint;

/**
 * Created by leonid on 2/12/17.
 */

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // build app component
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        // initialize crash reporting
        Mint.initAndStartSession(this, "566beb12");
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
