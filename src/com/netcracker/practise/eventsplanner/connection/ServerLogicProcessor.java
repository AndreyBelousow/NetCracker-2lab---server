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

    private DTOProcessor dtoProcessor = new DTOProcessor();
    private UserDAO userDAO = new UserDaoStub();

    public void onConnect(InputStream input, OutputStream output) throws IOException {

        ObjectInputStream objectIn = new ObjectInputStream(input);
        ObjectOutputStream objectOut = new ObjectOutputStream(output);

        //Сообщаем клиенту о присоединении
        objectOut.writeObject(Messages.connected);

        User user = null;

        //Авторизация
        while (true) {
            //Ждем UserDTO с данными для авторизации
            UserDTO authData = null;
            try {
                authData = (UserDTO) objectIn.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            //Проверяем логин - пароль
            user = tryLogIn(authData);
            if (user == null) {
                objectOut.writeObject(Messages.failed);
                continue;
            } else {
                objectOut.writeObject(Messages.success);
                break;
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

    private User tryLogIn(UserDTO data) {
        return userDAO.getUser(data.getUsername(), data.getPassword());
    }

    private void updateUser(User user){
        userDAO.update(user.getUsername(), user);
    }
}
