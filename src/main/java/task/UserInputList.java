package task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserInputList {
    public static final String TERRY_RELATIVE_PATH_NAME = "./data/terry.txt";
    private List<Task> tasks;

    public UserInputList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
        writeToFile();
    }


    public void remove(int index) {
        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1);
        } else {
            System.out.println("Index out of bounds");
        }
        writeToFile();
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
        writeToFile();
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

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No Tasks");
        } else {
            int index = 1;
            for (Task task : tasks) {
                System.out.println(index + ". " + task);  // Implicitly calls task.toString()

                index++;
            }
        }
    }

    public void printIndex(int index) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            System.out.println(index + ". " + task);
        } else {
            System.out.println("Index out of bounds");
        }
    }

    public void printIndexWithoutNumber( int index ) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            System.out.println(task);
        } else {
            System.out.println("Index out of bounds");
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public void saveTasksToFile() {
        File file = new File(TERRY_RELATIVE_PATH_NAME);
        file.getParentFile().mkdirs();  // will create data folder if it doesnt alr exist
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        }
        writeToFile();
    }

    public void loadTasksFromFile() {
        File file = new File(TERRY_RELATIVE_PATH_NAME);
        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromSaveFormat(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file");
        }
    }

    private void writeToFile(){
        //ill call this everytime i do bye? or should i do it everytime i call any one of the methods 0.o
        //this method basically overwrites the file everytime i call it
        try {
            FileWriter fw = new FileWriter(TERRY_RELATIVE_PATH_NAME);
            for (Task task : tasks) {
                fw.write(task.getSaveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file");
        }
    }

    private Task parseTaskFromSaveFormat(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null; // Invalid format

        String type = parts[0]; // T, D, or E
        boolean isMarked = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            if (parts.length < 4) return null; // Ensure correct format for Deadline
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            if (parts.length < 4) return null; // Ensure correct format for Event
            String[] eventTimes = parts[3].split(" to: ");
            if (eventTimes.length < 2) return null; // Invalid event time format
            task = new Event(description, eventTimes[0], eventTimes[1]);
            break;
        default:
            return null;
        }

        task.setMarked(isMarked);
        return task;
    }





}


