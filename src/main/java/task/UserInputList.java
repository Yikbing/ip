package task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import storage.Storage;


public class UserInputList {
    public static final String TERRY_RELATIVE_PATH_NAME = "./data/terry.txt";
    private List<Task> tasks;
    private Storage storage;

    public UserInputList() {
        this.tasks = new ArrayList<>();
        this.storage = new Storage();
        storage.loadTasksFromFile(tasks);
    }

    public void add(Task task) {
        tasks.add(task);
        storage.writeToFile(tasks);
    }


    public void remove(int index) {
        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1);
        } else {
            System.out.println("Index out of bounds");
        }
        storage.writeToFile(tasks);
    }

    public void mark(int index) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            if (task.isMarked()) {
                System.out.println("Already Marked");
                return;
            }
            task.setMarked(true);
        } else {
            System.out.println("Index out of bounds");
        }
        storage.writeToFile(tasks);
    }

    public void unmark(int index) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            if (task.isMarked()) {
                task.setMarked(false);
            }
        } else {
            System.out.println("Invalid Index");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> getOneTask(int index) {
        if (index > 0 && index <= tasks.size()) {
            return tasks.subList(0, index);
        }
        System.out.println("Index out of bounds");
        return null;
    }

    public void clear() {
        tasks.clear();
    }

    public int getSize() {
        return tasks.size();
    }

}


