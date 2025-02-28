package commandprocessor;

import exceptions.TerryException;
import parser.Parser;
import storage.Storage;
import task.UserInputList;
import ui.Ui;
import task.Task;

import java.util.Arrays;
import java.util.List;

public class CommandProcessor {

    private static final int COMMAND_AND_DETAILS_LIMIT = 2;

    public static boolean process_command(String command, UserInputList list, String[] words, String line, Ui ui, Storage storage) throws TerryException {

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
        case "find":
            handleFindCase(list, words, ui);
            Ui.printLine();
            break;


        default:
            try {
                Parser.addItemToList(list, line, ui, storage);
            } catch (TerryException e) {
                Ui.printLine();
            }
            break;
        }
        return false;
    }

    private static void handleFindCase(UserInputList list, String[] words, Ui ui) throws TerryException {
        if (words.length < 2) {
            throw new TerryException("Please provide a keyword to search for.");
        }
        Task taskInstance = new Task("");
        List<Task> foundTasks = taskInstance.findTasksByKeyword(words[1], list.getTasks());
        ui.showMatchingTasks(foundTasks);
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

}
