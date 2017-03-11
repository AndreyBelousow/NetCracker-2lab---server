package com.netcracker.practise.eventsplanner.dao;
import com.netcracker.practise.eventsplanner.model.Task;

public interface TaskDAO {
    public void addTask(Task task);
    public Task getTask(int i);
    public void update(int i,Task task);
    public void delete(int i);
}
