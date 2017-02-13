package com.netcracker.practise.eventsplanner.controllers.saveLoad;

import com.netcracker.practise.eventsplanner.controllers.saveLoad.SaveLoadController;
import com.netcracker.practise.eventsplanner.model.Task;
import com.netcracker.practise.eventsplanner.model.TaskJournal;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author
 */

public class BasicSaveLoadController implements SaveLoadController {

    public void saveJournal(TaskJournal journal, String filename){
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(filename);
            writeJournal(fileWriter, journal);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
       System.out.println("SAVED");
    }

    public TaskJournal loadJournal(String filename){
        FileReader fileReader;
        TaskJournal tmp = null;
        try {
            fileReader = new FileReader(filename);
            tmp = readJournal(fileReader);
            fileReader.close();
        } catch (Exception e) {
           System.err.println(e);
        }
        return tmp;
    }

    //________________________________________________
    
    private void writeJournal(Writer out, TaskJournal taskJournal){
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.write(taskJournal.getTasks().size()+"\n");
        for (int i = 0; i < taskJournal.getTasks().size(); i++) {
            writeTask(taskJournal.getTask(i), out);
        }
        printWriter.flush();
        printWriter.close();
    }

    private TaskJournal readJournal(Reader in) throws IOException, ParseException{
        List<Task> taskList;
        try (BufferedReader bufferedReader = new  BufferedReader(in)) {
            int listSize = Integer.parseInt(bufferedReader.readLine());
            taskList = new ArrayList<>(listSize);
            for (int i = 0; i < listSize; i++) {
                taskList.add(readTask(bufferedReader));
            }
            bufferedReader.close();
        }
        return new TaskJournal(taskList);
    }

    private void writeTask(Task task, Writer out) {
        PrintWriter pw = new PrintWriter(out);

        pw.write(task.getName()+"\n");
        pw.write(task.getDate().getTime()+"\n");
        pw.write(task.getDescription()+"\n");
        pw.write(task.getContacts().size()+"\n");
        for (int i = 0; i < task.getContacts().size(); i++) {
            pw.write(task.getContact(i)+"\n");
        }
        pw.flush();
    }

    private Task readTask(BufferedReader in) throws IOException, ParseException{
        String name  = in.readLine();
        String dateString = in.readLine();
        Date date = new Date(Long.parseLong(dateString));
        String description = in.readLine();
        int sizeOfList = Integer.parseInt(in.readLine());
        List<String> contacts = new ArrayList<>();
        for (int i = 0; i < sizeOfList; i++) {
            contacts.add(in.readLine());
        }
        return new Task(name, description, contacts, date);
    }
}
