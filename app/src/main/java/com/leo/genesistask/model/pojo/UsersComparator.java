package com.leo.genesistask.model.pojo;

import java.util.Comparator;

/**
 * Used to compare users by login alphabetically
 * <p>
 * Created by leonid on 2/11/17.
 */

public class UsersComparator implements Comparator<User> {

    @Override
    public int compare(User user1, User user2) {
        return user1.getLogin().compareToIgnoreCase(user2.getLogin());
    }

}
