package com.leo.genesistask.exception;

import android.content.Context;

import com.leo.genesistask.R;


/**
 * Factory used to create an error message from an Exception.
 * <p>
 * Created by leonid on 11/06/16.
 */

public class ErrorMessageFactory {

    public ErrorMessageFactory() {
        // empty
    }

    /**
     * Creates a String representation of an error message.
     *
     * @param context Context needed to retrieve string resources.
     * @param error   An exception used as a condition to retrieve the correct error message.
     * @return {@link String} an error message.
     */
    public static String create(Context context, Throwable error) {
        String message;

        if (error instanceof LoadUsersException) {
            message = context.getString(R.string.exception_message_load_users);
        } else if (error instanceof LoadFollowersException) {
            message = context.getString(R.string.exception_message_load_followers);
        } else {
            message = null != error.getMessage() ? error.getMessage() :
                    context.getString(R.string.exception_message_generic);
        }
        return message;
    }

}
