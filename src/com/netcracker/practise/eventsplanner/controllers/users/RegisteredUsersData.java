package com.netcracker.practise.eventsplanner.controllers.users;

/**
 * Created by 1 on 13.02.2017.
 */
public interface RegisteredUsersData {
    void add(String ID, String password);
    void delete(String ID, String password);
    boolean checkFor(String ID);
    boolean checkPass(String ID, String password);
}
