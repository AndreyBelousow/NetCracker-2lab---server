package com.netcracker.practise.eventsplanner.connection;

import com.netcracker.practise.eventsplanner.controllers.users.UserController;
import com.netcracker.practise.eventsplanner.controllers.saveLoad.BasicSaveLoadController;
import com.netcracker.practise.eventsplanner.controllers.saveLoad.SaveLoadController;

import java.util.ArrayList;

/**
 * Created by 1 on 12.02.2017.
 */
public class Server {

    private ArrayList<UserController> ConnectedUsers;

    public SaveLoadController SaveLoadController = new BasicSaveLoadController();

    public void connectUser(String userID){

    }

    public void disconnectUser(){}

    public void synchronizeUser(){}

    public void registerNewUser(){}
}
