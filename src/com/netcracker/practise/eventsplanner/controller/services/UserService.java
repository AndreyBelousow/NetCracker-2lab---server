package com.netcracker.practise.eventsplanner.controller.services;

import com.netcracker.practise.eventsplanner.model.User;

public interface UserService {
    public User signIn(User user);
    public void signUp(User user);

}
