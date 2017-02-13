package com.leo.genesistask.navigation;

import android.app.Activity;
import android.content.Intent;

import com.leo.genesistask.R;
import com.leo.genesistask.view.activity.UserFollowersActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Used to navigate through the Application
 * <p>
 * Created by leonid on  2/12/17.
 */

@Singleton
public class Navigator {

    @Inject
    Navigator() {
        //empty
    }

    /**
     * Navigates to user followers screen
     *
     * @param activity  needed to start new activity
     * @param userLogin user login
     */
    public void navigateToUserFollowersActivity(Activity activity, String userLogin) {
        if (activity != null) {
            Intent intentToLaunch = UserFollowersActivity.getCallingIntent(activity, userLogin);
            activity.startActivity(intentToLaunch);
            startActivitySlideAnimation(activity);
        }
    }


    /**
     * Overrides pending transition (activity animation)
     *
     * @param activity {@link Activity}
     */
    private static void startActivitySlideAnimation(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }


}
