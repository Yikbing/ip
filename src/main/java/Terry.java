
import java.util.Scanner;


public class Terry {
    public static void main(String[] args) {

        System.out.println("Hello from Terry\n");
        System.out.println("Hello! I am Terrybear\n ");
        System.out.println("What can I do for you?");
        printLine();

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
            }

         }

    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

