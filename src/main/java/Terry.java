import java.util.Scanner;

public class Terry {
    public static void main(String[] args) {

        welcomeMessage();

        Scanner in = new Scanner(System.in);
        String line;
        UserInputList list = new UserInputList();
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("BYEBYE SEE YOU NEXT TIME");
                printLine();
                break;
            } else if (line.equals("list")) {
                list.printTasks();
                printLine();
            } else if (line.startsWith("mark")) {
                String[] words = line.split(" ");
                if (words.length < 2) { // Check if the number is missing
                    System.out.println("YOU DIDN'T PUT THE NUMBER THERE");
                } else {
                    try {
                        int index = Integer.parseInt(words[1]);
                        list.mark(index);
                        if (list.getOneTask(index) != null) {
                            System.out.println("aww yea the task is marked LOOK");
                            list.printIndex(index);
                        } else {
                            System.out.println("WE HAVEN'T REACHED SO MANY TASKS YET");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("YOU DIDN'T PUT THE NUMBER THERE");
                    }
                }
                printLine();
            } else {
                addItemToList(list, line);
                // i think need to change this function into taking in 3 var, event
                // to do or deadline then based on that additemtolist haha
            }
        }
    }

    private static void addItemToList(UserInputList list, String line) {
        String[] words = line.split(" ", 2);
        if (words.length < 2) {
            System.out.println("Invalid format! Please specify the task details.");
            return;
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
                System.out.println("Invalid format for deadline! Use: deadline [description] /by [date]");
                return;
            }
            newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;
        case "event":
            String[] eventParts = details.split(" /from ", 2);
            if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
                System.out.println("Invalid format for event! Use: event [description] /from [start] /to [end]");
                return;
            }
            String[] times = eventParts[1].split(" /to ", 2);
            newTask = new Event(eventParts[0], times[0], times[1]);
            break;
        default:
            System.out.println("Unknown task type! Use 'todo', 'deadline', or 'event'.");
            return;
        }

        list.add(newTask); // Add the task to the list

        printLine();
        System.out.println("Got it. I've added this task:\n  " + newTask);
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
        printLine();
        // java automatically calls the toString function when printing an object type,
        //so printing newTask will call toString, invoking the toString functions in the
        //respective subclasses since they have @override inside
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
