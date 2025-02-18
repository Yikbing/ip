import task.*;

import java.util.Scanner;

public class Terry {

    private static final int COMMAND_AND_DETAILS_LIMIT = 2;

    public static void main(String[] args) throws TerryException {

        welcomeMessage();

        Scanner in = new Scanner(System.in);
        String line;
        UserInputList list = new UserInputList();
        list.loadTasksFromFile();

        while (true) {
            line = in.nextLine().trim();
            String[] words = line.split(" ", COMMAND_AND_DETAILS_LIMIT);
            String command = words[0].toLowerCase();

            switch (command) {
            case "bye":
                System.out.println("BYEBYE SEE YOU NEXT TIME");
                printLine();
                return;

            case "list":
                list.printTasks();
                printLine();
                break;

            case "mark":
                handleMarkCase(words, list);
                printLine();
                break;

            default:
                try {
                    addItemToList(list, line);
                } catch (TerryException e) {
                    printLine();
                }
                break;
            }
        }

    }

    private static void handleMarkCase(String[] words, UserInputList list) throws TerryException {
        if (words.length < COMMAND_AND_DETAILS_LIMIT) { // can also use this limit since need mark followed by index as well!
            throw new TerryException("YOU DIDN'T PUT THE NUMBER THERE");
        } else {
            try {
                int index = Integer.parseInt(words[1]);
                if (list.getOneTask(index) != null) {
                    list.mark(index);
                    System.out.println("aww yea the task is marked LOOK");
                    list.printIndex(index);
                } else {
                    throw new TerryException("WE HAVEN'T REACHED SO MANY TASKS YET");
                }
            } catch (NumberFormatException e) {
                throw new TerryException("YOU DIDN'T PUT THE NUMBER THERE");
            }
        }
    }

    private static void addItemToList(UserInputList list, String line) throws TerryException {
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

        printLine();
        System.out.println("Got it. I've added this task:\n  " + newTask);
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
        printLine();
        // java automatically calls the toString function when printing an object type,
        //so printing newTask will call toString, invoking the toString functions in the
        //respective subclasses since they have @override inside
        list.saveTasksToFile();
    }


    private static void welcomeMessage() {
        System.out.println("Hello from Terry\n");
        System.out.println("Hello! I am Terrybear\n ");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
