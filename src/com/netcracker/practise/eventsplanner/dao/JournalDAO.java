package com.netcracker.practise.eventsplanner.dao;

import com.netcracker.practise.eventsplanner.model.TaskJournal;

public interface JournalDAO {
    public void addJournal(TaskJournal taskJournal);
    public TaskJournal getJournal(int i);
    public void update(int i,TaskJournal taskJournal);
    public void delete(int i);

}
