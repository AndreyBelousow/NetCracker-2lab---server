package com.netcracker.practise.eventsplanner.dao.impl;

import com.netcracker.practise.eventsplanner.dao.UserDAO;
import com.netcracker.practise.eventsplanner.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDaoStub implements UserDAO {
    private HashMap<String,User> userHashMap;

    public UserDaoStub(){
        this.userHashMap = new HashMap<>();
    }

    public HashMap<String,User> getUserHashMap(){
        return this.userHashMap;
    }

    @Override
    public void addUser(User user) {
        user.setUserId(this.userHashMap.size()+1);
        userHashMap.put(user.getUsername(),user);
    }

    public User getUser(String username, String password){
        for(Map.Entry<String,User> entry: userHashMap.entrySet()){
           if (entry.getValue().isEqual(username, password))
               return entry.getValue();
        }
         return null;
    }

    @Override
    public User getUser(String username) {
        return userHashMap.get(username);
    }

    @Override
    public void update(String username,User user) {
      userHashMap.put(username,user);
    }

    @Override
    public void delete(User user) {
        userHashMap.remove(user.getUsername());
    }


    public HashMap<String, User> findAll(){
        return this.userHashMap;
    }

    public void addUser(User...users){
        for(User tmp:users){
            tmp.setUserId(this.userHashMap.size()+1);
            this.userHashMap.put(tmp.getUsername(),tmp);
        }
    }
    public void addUser(String username, String password){
        User tmpUser = new User(username,password);
        tmpUser.setUserId(this.userHashMap.size()+1);
        userHashMap.put(tmpUser.getUsername(),tmpUser);
    }




}
