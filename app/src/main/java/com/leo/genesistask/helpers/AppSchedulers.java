package com.leo.genesistask.helpers;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by leonid on 12/5/16.
 */

public class AppSchedulers {

    public interface SchedulerProvider {
        Scheduler mainThread();

        Scheduler io();
    }

    private static SchedulerProvider sInstance = new DefaultSchedulerProvider();

    public static void setInstance(SchedulerProvider instance) {
        AppSchedulers.sInstance = instance;
    }

    public static Scheduler mainThread() {
        return sInstance.mainThread();
    }

    public static Scheduler io() {
        return sInstance.io();
    }


    public static class DefaultSchedulerProvider implements SchedulerProvider {

        @Override
        public Scheduler mainThread() {
            return AndroidSchedulers.mainThread();
        }

        @Override
        public Scheduler io() {
            return Schedulers.io();
        }
    }
}
