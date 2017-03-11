package com.netcracker.practise.eventsplanner.dto;

import java.io.Serializable;

/**
 * @author A.Belousow
 */
public class UserDTO implements Serializable{

    private String username;
    private  String password;
    private  Integer userId;
    private JournalDTO usersJournal;

    public UserDTO(String username, String password, Integer userId, JournalDTO usersJournal) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.usersJournal = usersJournal;
    }

    public Integer getUserId() {
        return userId;
    }

    public JournalDTO getUsersJournal() {
        return usersJournal;
    }

    public String getPassword() {

        return password;
    }

    public String getUsername() {

        return username;
    }
}
