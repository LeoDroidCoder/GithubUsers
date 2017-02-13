package com.leo.genesistask.helpers;

import org.junit.rules.ExternalResource;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by leonid on 2/12/17.
 */

public class SynchronousSchedulers extends ExternalResource {
    @Override
    protected void before() throws Throwable {

        AppSchedulers.setInstance(new AppSchedulers.SchedulerProvider() {
            @Override
            public Scheduler mainThread() {
                return Schedulers.immediate();
            }

            @Override
            public Scheduler io() {
                return Schedulers.immediate();
            }
        });
    }

    @Override
    protected void after() {
        AppSchedulers.setInstance(new AppSchedulers.DefaultSchedulerProvider());
    }
}
