package com.leo.genesistask.exception;

/**
 * Exception thrown by the application when repository fails to load followers
 * <p>
 * Created by leonid on 02/12/17.
 */

public class LoadFollowersException extends Exception {

    public LoadFollowersException() {
        super();
    }

    public LoadFollowersException(String message) {
        super(message);
    }

    public LoadFollowersException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadFollowersException(Throwable cause) {
        super(cause);
    }

}
