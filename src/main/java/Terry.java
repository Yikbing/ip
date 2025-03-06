import exceptions.TerryException;
import task.UserInputList;

import java.util.Scanner;
import ui.Ui;
import storage.Storage;
import commandprocessor.CommandProcessor;

/**
 * The main class of the Terry task manager application. This class initializes the user interface,
 * loads tasks from the storage, processes user input commands, and interacts with other components
 * such as task list, storage, and UI.
 */
public class Terry {

    /**
     * The maximum number of words to split a command line into, used for processing user input.
     */
    private static final int COMMAND_AND_DETAILS_LIMIT = 2;


    /**
     * The main method that runs the Terry task manager application. It:
     * - Displays the welcome message
     * - Initializes UI, storage, and task list
     * - Continuously takes user input, processes commands, and executes the corresponding actions
     *
     * @param args Command-line arguments passed to the application (not used in this implementation).
     * @throws TerryException If an error occurs during command processing or other operations.
     */
    public static void main(String[] args) throws TerryException {

        Ui ui = new Ui();
        Storage storage = new Storage();
        Ui.welcomeMessage();

        Scanner in = new Scanner(System.in);
        String line;
        UserInputList list = new UserInputList();
        storage.loadTasksFromFile(list.getTasks());

        // Main loop for processing user input
        while (true) {
            line = in.nextLine().trim();
            String[] words = line.split(" ", COMMAND_AND_DETAILS_LIMIT);
            String command = words[0].toLowerCase();

            // Process the user command, if the command returns true(user types "bye"), exit the loop
            if (CommandProcessor.process_command(
                    command, list, words, line, ui, storage)) {
                return;
            }


        }

    }



}
