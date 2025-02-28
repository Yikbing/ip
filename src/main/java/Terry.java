import task.*;

import java.util.Scanner;
import ui.Ui;
import storage.Storage;

public class Terry {

    private Storage storage;
    private Ui ui;

    private static final int COMMAND_AND_DETAILS_LIMIT = 2;

    public static void main(String[] args) throws TerryException {

        Ui ui = new Ui();
        Storage storage = new Storage();
        Ui.welcomeMessage();

        Scanner in = new Scanner(System.in);
        String line;
        UserInputList list = new UserInputList();
        storage.loadTasksFromFile(list.getTasks());

        while (true) {
            line = in.nextLine().trim();
            String[] words = line.split(" ", COMMAND_AND_DETAILS_LIMIT);
            String command = words[0].toLowerCase();

            if (process_command(command, list, words, line, ui, storage)) return;
        }

    }

    private static boolean process_command(String command, UserInputList list, String[] words, String line, Ui ui, Storage storage) throws TerryException {
        switch (command) {
        case "bye":
            Ui.showExitMessage();
            return true;

        case "list":
            ui.printTasks(list);
            Ui.printLine();
            break;

        case "mark":
            handleMarkCase(words, list, ui);
            Ui.printLine();
            break;
        case "delete":
            handleDeleteCase(words, list, ui);
            Ui.printLine();
            break;

        default:
            try {
                addItemToList(list, line, ui, storage);
            } catch (TerryException e) {
                Ui.printLine();
            }
            break;
        }
        return false;
    }

    private static void handleDeleteCase(String[] words, UserInputList list, Ui ui) throws TerryException {
        int index_to_delete = Integer.parseInt(words[1]);
        System.out.println("I deleted thisss: ");
        ui.printIndexWithoutNumber(list, index_to_delete);
        list.remove(index_to_delete);
        ui.printListSize(list);
    }

    private static void handleMarkCase(String[] words, UserInputList list, Ui ui) throws TerryException {
        if (words.length < COMMAND_AND_DETAILS_LIMIT) { // can also use this limit since need mark followed by index as well!
            throw new TerryException("YOU DIDN'T PUT THE NUMBER THERE");
        } else {
            try {
                int index = Integer.parseInt(words[1]);
                if (list.getOneTask(index) != null) {
                    list.mark(index);
                    System.out.println("aww yea the task is marked LOOK");
                    ui.printIndex(list, index);
                } else {
                    throw new TerryException("WE HAVEN'T REACHED SO MANY TASKS YET");
                }
            } catch (NumberFormatException e) {
                throw new TerryException("YOU DIDN'T PUT THE NUMBER THERE");
            }
        }
    }

    private static void addItemToList(UserInputList list, String line, Ui ui, Storage storage) throws TerryException {
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
//        list.saveTasksToFile();
        storage.saveTasksToFile(list.getTasks());
    }

}
