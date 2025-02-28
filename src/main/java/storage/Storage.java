package storage;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import task.Task;
import task.Event;
import task.ToDo;
import task.Deadline;

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
                Task task = parseTaskFromSaveFormat(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file");
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
