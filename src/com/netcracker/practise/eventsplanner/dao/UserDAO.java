package com.netcracker.practise.eventsplanner.dao;

import com.netcracker.practise.eventsplanner.model.User;

public interface UserDAO {
    public void addUser(User user);
    public User getUser(String username,String password);
    public User getUser(String username);
    public void update(String username,User user);
    public void delete(User user);
}
