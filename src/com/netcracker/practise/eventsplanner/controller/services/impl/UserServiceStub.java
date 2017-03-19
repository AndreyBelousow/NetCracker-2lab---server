package com.netcracker.practise.eventsplanner.controller.services.impl;

import com.netcracker.practise.eventsplanner.controller.services.UserService;
import com.netcracker.practise.eventsplanner.dao.UserDAO;
import com.netcracker.practise.eventsplanner.model.User;

public class UserServiceStub implements UserService {

    private UserDAO userDao;

    public UserServiceStub(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public User signIn(User user) {
        return  userDao.getUser(user.getUsername(),user.getPassword());
    }

    @Override
    public void signUp(User user) {
        if(signIn(user)==null){
            userDao.addUser(user);
        }
        else throw new IllegalArgumentException();
    }
}
