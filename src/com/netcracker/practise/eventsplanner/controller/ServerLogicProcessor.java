package com.netcracker.practise.eventsplanner.controller;
import com.netcracker.practise.eventsplanner.controller.services.UserService;
import com.netcracker.practise.eventsplanner.controller.services.impl.UserServiceStub;
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

    private DTOProcessor dtoProcessor = new DTOProcessor();
    private UserDAO userDAO = new UserDaoStub();
    private UserService service = new UserServiceStub(userDAO);
    //TODO перенести на сервис

    public void onConnect(InputStream input, OutputStream output) throws IOException {

        ObjectInputStream objectIn = new ObjectInputStream(input);
        ObjectOutputStream objectOut = new ObjectOutputStream(output);

        //Сообщаем клиенту о присоединении
        objectOut.writeObject(Messages.connected);

        User user = null;

        String msg = null;
        try{
            msg=(String) objectIn.readObject();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        switch(msg){
            case Messages.signIn :
                //Авторизация
                while (true) {
                    //Ждем UserDTO с данными для авторизации
                    UserDTO authData = null;
                    try {
                        authData = (UserDTO) objectIn.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    user = new User(authData.getUsername(), authData.getPassword());
                    //Проверяем логин - пароль
                    service.signIn(user);
                    if (user == null) {
                        objectOut.writeObject(Messages.failed);
                        continue;
                    } else {
                        objectOut.writeObject(Messages.success);
                        break;
                    }
                }
                break;

            case Messages.signUp :
                //Регистрация
                while (true) {
                    //Ждем UserDTO с данными для регистрации
                    UserDTO regData = null;
                    try {
                        regData = (UserDTO) objectIn.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    user = new User(regData.getUsername(), regData.getPassword());
                    //Проверяем логин на наличие такого же в базе
                    try {
                        service.signUp(user);
                        objectOut.writeObject(Messages.success);
                    }
                    catch (IllegalArgumentException e){
                        objectOut.writeObject(Messages.failed);
                    }
                }
        }

        //Отправляем клиенту его данные
        objectOut.writeObject(dtoProcessor.getDTO(user));

        //Ждем сообщения от клиента
        while(true){
            String message = null;
            try {
                message = (String)objectIn.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //Обновляем его журнал или ждем сообщения о выходе
            switch (message){
                case (Messages.update):
                    UserDTO dto = null;
                    try {
                        dto = (UserDTO)objectIn.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    user = dtoProcessor.getObject(dto);
                    updateUser(user);
                    break;
                case (Messages.quit) :
                    updateUser(user);
                    return;
            }
        }
    }

    //_________________________________________________________________________

    private void updateUser(User user){
        userDAO.update(user.getUsername(), user);
    }
}
