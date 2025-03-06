package parser;

import storage.Storage;
import ui.Ui;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import task.UserInputList;
import exceptions.TerryException;


/**
 * The {@code Parser} class is responsible for processing user commands,
 * parsing task information, and handling task creation from saved files.
 */
public class Parser {

    // Position constants used for parsing input strings
    public static final int COMMAND_POSITION = 0;
    public static final int EVENT_FROM_POSITION = 0;
    public static final int DESCRIPTION_POSITION_AFTER_PARSE = 0;

    public static final int MARKED_POSITION = 1;
    public static final int EVENT_TO_POSITION = 1;
    public static final int DETAILS_POSITION = 1;
    public static final int EVENT_DETAILS_POSITION = 1;
    public static final int TIME_POSITION_AFTER_PARSE = 1;

    private static final int COMMAND_AND_DETAILS_LIMIT = 2;
    public static final int REQUIRED_PARTS_FOR_EVENT_TIMES = 2;
    public static final int DESCRIPTION_POSITION = 2;
    public static final int DETAILS_AND_PERIOD_LIMIT = 2;

    public static final int DEADLINE_POSITION = 3;
    public static final int EVENT_TIMES_POSITION = 3;
    public static final int REQUIRED_PARTS_FOR_ALL_TASKS = 3;

    public static final int REQUIRED_PARTS_FOR_DEADLINE = 4;
    public static final int REQUIRED_PARTS_FOR_EVENT = 4;

    /**
     * Parses a user input line and adds the corresponding task to the list.
     *
     * @param list     The list of user tasks.
     * @param line     The user input line containing the task details.
     * @param ui       The UI instance for displaying messages.
     * @param storage  The storage instance for saving tasks.
     * @throws TerryException If the input format is invalid or unknown.
     */
    public static void addItemToList(
            UserInputList list, String line,
            Ui ui, Storage storage) throws TerryException {
        String[] words = line.split(" ", COMMAND_AND_DETAILS_LIMIT);
        if (words.length < COMMAND_AND_DETAILS_LIMIT) {
            System.out.println("Invalid format! Please specify the task details.");
            throw new TerryException("Invalid format! Please specify the task details.");

        }

        String taskType = words[COMMAND_POSITION];
        String details = words[DETAILS_POSITION];

        Task newTask = null;

        switch (taskType) {
        case "todo":
            newTask = new ToDo(details);
            break;
        case "deadline":
            String[] deadlineParts = details.split(" /by ", DETAILS_AND_PERIOD_LIMIT);
            if (deadlineParts.length < DETAILS_AND_PERIOD_LIMIT) {
                throw new TerryException(
                        "Invalid format for deadline! Use: deadline [description] /by [date]");
            }
            newTask = new Deadline(
                    deadlineParts[DESCRIPTION_POSITION_AFTER_PARSE],
                    deadlineParts[TIME_POSITION_AFTER_PARSE]);
            break;
        case "event":
            String[] eventParts = details.split(" /from ", DETAILS_AND_PERIOD_LIMIT);
            if (eventParts.length < DETAILS_AND_PERIOD_LIMIT ||
                    !eventParts[TIME_POSITION_AFTER_PARSE].contains(" /to ")) {
                throw new TerryException(
                        "Invalid format for event!" +
                        " Use: event [description] /from [start] /to [end]");
            }
            String[] times = eventParts[EVENT_DETAILS_POSITION].split(" /to ",
                    REQUIRED_PARTS_FOR_EVENT_TIMES);
            newTask = new Event(eventParts[DESCRIPTION_POSITION_AFTER_PARSE],
                    times[EVENT_FROM_POSITION], times[EVENT_TO_POSITION]);
            break;
        default:
            throw new TerryException("Unknown task type! Use 'todo', 'deadline', or 'event'.");
        }

        list.add(newTask);

        ui.printTasks(list, newTask);
        storage.saveTasksToFile(list.getTasks());
    }

    /**
     * Parses a task from a saved file format and reconstructs it.
     *
     * @param line The line of text from the saved file containing task details.
     * @return The reconstructed task, or {@code null} if the format is invalid.
     */
    public static Task parseTaskFromSaveFormat(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < REQUIRED_PARTS_FOR_ALL_TASKS) return null;

        String type = parts[COMMAND_POSITION];
        boolean isMarked = parts[MARKED_POSITION].equals("1");
        String description = parts[DESCRIPTION_POSITION];

        Task task;
        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            if (parts.length < REQUIRED_PARTS_FOR_DEADLINE) return null;
            task = new Deadline(description, parts[DEADLINE_POSITION]);
            break;
        case "E":
            if (parts.length < REQUIRED_PARTS_FOR_EVENT) return null;
            String[] eventTimes = parts[EVENT_TIMES_POSITION].split(" to: ");
            if (eventTimes.length < REQUIRED_PARTS_FOR_EVENT_TIMES) return null;
            task = new Event(description, eventTimes[EVENT_FROM_POSITION],
                    eventTimes[EVENT_TO_POSITION]);
            break;
        default:
            return null;
        }

        task.setMarked(isMarked);
        return task;
    }
}
