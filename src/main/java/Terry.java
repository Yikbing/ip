import java.util.Scanner;

public class Terry {
    public static void main(String[] args) {

        System.out.println("Hello from Terry\n");
        System.out.println("Hello! I am Terrybear\n ");
        System.out.println("What can I do for you?");
        printLine();

        Scanner in = new Scanner(System.in);
        String line;
        while(true) {
            line = in.nextLine();
            printLine();
            if(line.equals("bye")) {
                System.out.println("BYEBYE SEE YOU NEXT TIME");
                printLine();
                break;
                 }
            System.out.println(line);
            printLine();

         }

    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

