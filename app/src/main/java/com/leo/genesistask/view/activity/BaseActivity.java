package com.leo.genesistask.view.activity;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.leo.genesistask.R;
import com.leo.genesistask.exception.ErrorMessageFactory;
import com.leo.genesistask.helpers.Utils;
import com.leo.genesistask.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by leonid on 2/10/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected Navigator mNavigator;


    /**
     * Shows a message in the Toast
     *
     * @param message message
     */
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a message in the Toast
     *
     * @param stringResource message string resource
     */
    public void showToast(@StringRes int stringResource) {
        Toast.makeText(this, getString(stringResource), Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows error message in the toast.
     * Checks internet connection and notifies user if it is not available.
     * Otherwise shows error message from the {@link Throwable} error
     *
     * @param error error
     */
    public void showError(Throwable error) {
        if (Utils.isNetworkAvailable(this)) {
            showToast(ErrorMessageFactory.create(this, error));
        } else {
            // error happened due to network troubles
            showToast(R.string.error_no_internet_connection);
        }
    }


}
