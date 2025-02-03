
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
                list.printTasks();
                printLine();
<<<<<<< HEAD
            } else {
                addItemToList(list, line);
=======
            } else if(line.startsWith("mark")) {
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
            }

            else {

                list.add(line);
                printLine();
                System.out.print("added: ");
                System.out.println(line);
                printLine();
>>>>>>> 9527102e9655234d7eb37c62ab079bc1746ca2f9
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

