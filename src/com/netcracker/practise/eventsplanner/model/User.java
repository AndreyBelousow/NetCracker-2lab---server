package com.netcracker.practise.eventsplanner.model;

/**
 * Created by 1 on 28.02.2017.
 */
public class User {
    private String username;
    private  String password;
    private  Integer userId;
    private TaskJournal usersJournal;

    public User(String username, String password){this.username = username; this.password = password; usersJournal=new TaskJournal();}
    public boolean isEqual(User user){
        return user.getUsername().equals(this.getUsername()) && user.getPassword().equals(this.getPassword());
    }
    public boolean isEqual(String username, String password){
        return username.equals(this.getUsername()) && password.equals(this.getPassword());
    }

    public Integer getUserId() {
        return userId;
    }
    public  void setUserId(Integer userId){
        this.userId = userId;
    }

    public String getUsername(){
        return username;
    }
    public  void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public  void setPassword(String password){
        this.password = password;
    }

    public   TaskJournal getUsersJournal(){
        return usersJournal;
    }
    public void setUsersJournal(TaskJournal usersJournal){
        this.usersJournal = usersJournal;
    }

}
