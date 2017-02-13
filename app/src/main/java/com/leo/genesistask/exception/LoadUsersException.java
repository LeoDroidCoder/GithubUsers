package com.leo.genesistask.exception;

/**
 * Exception thrown by the application when repository fails to load users
 * <p>
 * Created by leonid on 02/12/17.
 */

public class LoadUsersException extends Exception {

    public LoadUsersException() {
        super();
    }

    public LoadUsersException(String message) {
        super(message);
    }

    public LoadUsersException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadUsersException(Throwable cause) {
        super(cause);
    }

}
