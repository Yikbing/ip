
import java.util.Scanner;


public class Terry {
    public static void main(String[] args) {

        welcomeMessage();

        Scanner in = new Scanner(System.in);
        String line;
        UserInputList list = new UserInputList();
        while(true) {
            line = in.nextLine();
            if(line.equals("bye")) {
                System.out.println("BYEBYE SEE YOU NEXT TIME");
                printLine();
                break;
            }
            if(line.equals("list")) {
                list.printInputs();
                printLine();
            } else {
                addItemToList(list, line);
            }
         }
    }

    private static void addItemToList(UserInputList list, String line) {
        list.add(line);
        printLine();
        System.out.print("added: ");
        System.out.println(line);
        printLine();
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

