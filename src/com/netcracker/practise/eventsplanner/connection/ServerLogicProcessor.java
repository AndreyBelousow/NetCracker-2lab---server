package com.netcracker.practise.eventsplanner.connection;
import com.netcracker.practise.eventsplanner.dao.*;
import com.netcracker.practise.eventsplanner.dao.impl.*;
import com.netcracker.practise.eventsplanner.dto.*;
import com.netcracker.practise.eventsplanner.model.User;

import java.io.*;

/**
 * Provides a main controller's function
 * Redirects calls to other controllers
 * @author A.Belousow
 */
public class ServerLogicProcessor {

    private DTOmaker dtoMaker = new DTOmaker();

    private UserDAO userDAO = new UserDaoStub();
    private JournalDAO journalDAO = new JournalDaoStub();
    private TaskDAO taskDAO = new TaskDaoStub();

    //TODO разделение на отдельные методы
    public void onConnect(InputStream input, OutputStream output){

        BufferedReader stringIn = new BufferedReader(new InputStreamReader(input));
        PrintWriter stringOut = new PrintWriter(output);

        ObjectInputStream objectIn = null;
        ObjectOutputStream objectOut = null;
        try {
            objectIn = new ObjectInputStream(input);
            objectOut = new ObjectOutputStream(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Сообщаем клиенту о присоединении
        stringOut.println("connect");

        //Ждем UserDTO с данными для авторизации
        UserDTO authData = null;
        try {
            authData = (UserDTO) objectIn.readObject();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        User user = userDAO.getUser(authData.getUsername(), authData.getPassword());

    }
}
