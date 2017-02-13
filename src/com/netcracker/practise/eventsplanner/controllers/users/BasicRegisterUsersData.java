package com.netcracker.practise.eventsplanner.controllers.users;

/**
 * Created by 1 on 13.02.2017.
 */
public class BasicRegisterUsersData implements RegisteredUsersData {
    @Override
    public void add(String ID, String password) {

    }

    @Override
    public void delete(String ID, String password) {

    }

    @Override
    public boolean checkFor(String ID) {
        return false;
    }

    @Override
    public boolean checkPass(String ID, String password) {
        return false;
    }
}
