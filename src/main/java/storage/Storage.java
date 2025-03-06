package storage;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import task.Task;
import parser.Parser;

/**
 * Handles saving and loading tasks from a file
 */
public class Storage {
    public static final String TERRY_RELATIVE_PATH_NAME = "./data/terry.txt";

    /**
     * Saves the list of tasks to a file.
     * If the file does not exist, it is created.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasksToFile(List<Task> tasks) {
        File file = new File(TERRY_RELATIVE_PATH_NAME);
        file.getParentFile().mkdirs();
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        }
        writeToFile(tasks);
    }

    /**
     * Writes the given list of tasks to the file.
     *
     * @param tasks The list of tasks to be written.
     */
    public void writeToFile(List<Task> tasks) {
        try (FileWriter fw = new FileWriter(TERRY_RELATIVE_PATH_NAME)) {
            for (Task task : tasks) {
                fw.write(task.getSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file");
        }
    }

    /**
     * Loads tasks from the file and adds them to the task list.
     * If the file does not exist, no tasks are loaded.
     *
     * @param tasks The list where the loaded tasks will be stored.
     */
    public void loadTasksFromFile(List<Task> tasks) {
        File file = new File(TERRY_RELATIVE_PATH_NAME);
        if (!file.exists()) {
            return;
        }

        tasks.clear();

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
