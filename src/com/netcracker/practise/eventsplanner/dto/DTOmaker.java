package com.netcracker.practise.eventsplanner.dto;

import com.netcracker.practise.eventsplanner.model.Task;
import com.netcracker.practise.eventsplanner.model.TaskJournal;
import com.netcracker.practise.eventsplanner.model.User;

import java.util.List;

/**
 * @author A.Belousow
 */
public class DTOmaker {

    public UserDTO getDTO(User user){

        JournalDTO jdto = getDTO(user.getUsersJournal());

        return new UserDTO(user.getUsername(), user.getPassword(), user.getUserId(), jdto);
    }

    public JournalDTO getDTO(TaskJournal journal){

        List<TaskDTO> taskDTOList = null;

        List<Task> taskList = journal.getTasks();

        for(int i=0; i<taskList.size(); i++){
            TaskDTO tdto = getDTO(taskList.get(i));
            taskDTOList.add(i,tdto);
        }

        return new JournalDTO(taskDTOList);
    }

    public TaskDTO getDTO(Task task){
        return new TaskDTO(task.getName(), task.getDate(), task.getDescription(), task.getContacts());
    }
}
