package com.netcracker.practise.eventsplanner.dao.impl;

import com.netcracker.practise.eventsplanner.dao.TaskDAO;
import com.netcracker.practise.eventsplanner.model.Task;

import java.util.HashMap;

public class TaskDaoStub implements TaskDAO {
    private HashMap<Integer,Task> taskHashMap;

    public TaskDaoStub(){
        taskHashMap = new HashMap<>();
    }

    @Override
    public void addTask(Task task) {
        taskHashMap.put(taskHashMap.size()+1,task);
    }

    @Override
    public Task getTask(int i) {
        if (taskHashMap.containsKey(i))
            return taskHashMap.get(i);
        else return null;
    }

    @Override
    public void update(int i,Task task) {
        taskHashMap.put(i,task);
    }

    @Override
    public void delete(int i) {
        if (taskHashMap.containsKey(i))
            taskHashMap.remove(i);
    }
}
