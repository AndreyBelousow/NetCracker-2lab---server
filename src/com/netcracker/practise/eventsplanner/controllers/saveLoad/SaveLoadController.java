package com.netcracker.practise.eventsplanner.controllers.saveLoad;

import com.netcracker.practise.eventsplanner.model.TaskJournal;

/**
 * Created by 1 on 13.02.2017.
 */
public interface SaveLoadController {
    void saveJournal(TaskJournal journal, String filename);
    TaskJournal loadJournal(String filename);
}
