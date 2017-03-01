package com.netcracker.practise.eventsplanner.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author A.Belousow
 */
public class TaskDTO implements Serializable {

    private String name;
    private Date date;
    private String description;
    private List<String> contacts;


    public TaskDTO(String name, Date date, String description, List<String> contacts) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.contacts = contacts;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public String getDescription() {

        return description;
    }

    public Date getDate() {

        return date;
    }

    public String getName() {

        return name;
    }
}
