package com.netcracker.practise.eventsplanner.dao.impl;

import com.netcracker.practise.eventsplanner.dao.JournalDAO;
import com.netcracker.practise.eventsplanner.model.TaskJournal;

import java.util.HashMap;

public class JournalDaoStub implements JournalDAO {
    private HashMap<Integer,TaskJournal> journals;


    public JournalDaoStub(){
        journals = new HashMap<>();
    }

    @Override
    public void addJournal(TaskJournal taskJournal) {
        journals.put(journals.size()+1,taskJournal);
    }

    @Override
    public TaskJournal getJournal(int i) {
        if (journals.containsKey(i))
            return journals.get(i);
        else return null;
    }

    @Override
    public void update(int i,TaskJournal taskJournal) {
        journals.put(i,taskJournal);
    }

    @Override
    public void delete(int i) {
        if (journals.containsKey(i))
            journals.remove(i);
    }
}
