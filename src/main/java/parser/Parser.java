package parser;

import storage.Storage;
import ui.Ui;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import task.UserInputList;
import exceptions.TerryException;

public class Parser {

    private static final int COMMAND_AND_DETAILS_LIMIT = 2;


    public static void addItemToList(UserInputList list, String line, Ui ui, Storage storage) throws TerryException {
        String[] words = line.split(" ", COMMAND_AND_DETAILS_LIMIT);
        if (words.length < COMMAND_AND_DETAILS_LIMIT) {
            System.out.println("Invalid format! Please specify the task details.");
            throw new TerryException("Invalid format! Please specify the task details.");

        }

        String taskType = words[0]; // First part is the command
        String details = words[1];

        Task newTask = null; // Task object to be added

        switch (taskType) {
        case "todo":
            newTask = new ToDo(details);
            break;
        case "deadline":
            String[] deadlineParts = details.split(" /by ", 2);
            if (deadlineParts.length < 2) {
                throw new TerryException("Invalid format for deadline! Use: deadline [description] /by [date]");
            }
            newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;
        case "event":
            String[] eventParts = details.split(" /from ", 2);
            if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
                throw new TerryException("Invalid format for event! Use: event [description] /from [start] /to [end]");
            }
            String[] times = eventParts[1].split(" /to ", 2);
            newTask = new Event(eventParts[0], times[0], times[1]);
            break;
        default:
            throw new TerryException("Unknown task type! Use 'todo', 'deadline', or 'event'.");
        }

        list.add(newTask); // Add the task to the list

        ui.printTasks(list, newTask);
        // java automatically calls the toString function when printing an object type,
        //so printing newTask will call toString, invoking the toString functions in the
        //respective subclasses since they have @override inside
        storage.saveTasksToFile(list.getTasks());
    }


    public static Task parseTaskFromSaveFormat(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null;

        String type = parts[0];
        boolean isMarked = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            if (parts.length < 4) return null;
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            if (parts.length < 4) return null;
            String[] eventTimes = parts[3].split(" to: ");
            if (eventTimes.length < 2) return null;
            task = new Event(description, eventTimes[0], eventTimes[1]);
            break;
        default:
            return null;
        }

        task.setMarked(isMarked);
        return task;
    }
}
