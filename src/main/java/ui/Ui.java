package ui;

import task.Task;
import task.UserInputList;

import java.util.List;

public class Ui {

    public static void welcomeMessage() {
        System.out.println("Hello from Terry\n");
        System.out.println("Hello! I am Terrybear\n ");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printTasks(UserInputList list, Task newTask) {
        Ui.printLine();
        System.out.println("Got it. I've added this task:\n  " + newTask);
        printListSize(list);
        Ui.printLine();
    }

    public void printListSize(UserInputList list) {
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

    public static void showExitMessage() {
        System.out.println("BYEBYE SEE YOU NEXT TIME");
        printLine();
    }

    public void printTasks(UserInputList userInputList) {
        List<Task> tasks = userInputList.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("No Tasks");
        } else {
            int index = 1;
            for (Task task : tasks) {
                System.out.println(index + ". " + task);
                index++;
            }
        }
    }

    // Method to print a task by index
    public void printIndex(UserInputList userInputList, int index) {
        List<Task> tasks = userInputList.getTasks();
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            System.out.println(index + ". " + task);
        } else {
            System.out.println("Index out of bounds");
        }
    }

    // Method to print a task by index without the number
    public void printIndexWithoutNumber(UserInputList userInputList, int index) {
        List<Task> tasks = userInputList.getTasks();
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            System.out.println(task);
        } else {
            System.out.println("Index out of bounds");
        }
    }
}
