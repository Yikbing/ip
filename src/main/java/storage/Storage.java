package storage;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import task.Task;
import task.Event;
import task.ToDo;
import task.Deadline;
import parser.Parser;

public class Storage {
    public static final String TERRY_RELATIVE_PATH_NAME = "./data/terry.txt";

    public void saveTasksToFile(List<Task> tasks) {
        File file = new File(TERRY_RELATIVE_PATH_NAME);
        file.getParentFile().mkdirs();  // Create data folder if it doesn't exist
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        }
        writeToFile(tasks);
    }

    public void writeToFile(List<Task> tasks) {
        try (FileWriter fw = new FileWriter(TERRY_RELATIVE_PATH_NAME)) {
            for (Task task : tasks) {
                fw.write(task.getSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file");
        }
    }

    public void loadTasksFromFile(List<Task> tasks) {
        File file = new File(TERRY_RELATIVE_PATH_NAME);
        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTaskFromSaveFormat(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file");
        }
    }
}
