import exceptions.TerryException;
import task.*;

import java.util.Scanner;
import ui.Ui;
import storage.Storage;
import commandprocessor.CommandProcessor;

public class Terry {

//    private Storage storage;
//    private Ui ui;

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

            if (CommandProcessor.process_command(command, list, words, line, ui, storage)) return;
        }

    }



}
