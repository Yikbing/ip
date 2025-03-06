package ui;

import task.Task;
import task.UserInputList;

import java.util.List;

/**
 * This class handles user interface (UI) interactions, including displaying
 * welcome messages, task lists, task details, and exit messages. It works with
 * the {@link UserInputList} and {@link Task} classes to print relevant information.
 */
public class Ui {

    /**
     * Prints a welcome message to the user when the program starts.
     */
    public static void welcomeMessage() {
        System.out.println("Hello from Terry\n");
        System.out.println("Hello! I am Terrybear\n ");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Prints a separator line used for formatting UI output.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a confirmation message after a new task is added, including the task
     * details and the updated size of the task list.
     *
     * @param list The {@link UserInputList} containing the tasks.
     * @param newTask The task that was added.
     */
    public void printTasks(UserInputList list, Task newTask) {
        Ui.printLine();
        System.out.println("Got it. I've added this task:\n  " + newTask);
        printListSize(list);
        Ui.printLine();
    }

    /**
     * Prints the number of tasks currently in the task list.
     *
     * @param list The {@link UserInputList} containing the tasks.
     */
    public void printListSize(UserInputList list) {
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }


    /**
     * Prints an exit message when the user exits the application.
     */
    public static void showExitMessage() {
        System.out.println("BYEBYE SEE YOU NEXT TIME");
        printLine();
    }

    /**
     * Prints the entire list of tasks, with each task displayed along with its index.
     * If the task list is empty, a message indicating so is displayed.
     *
     * @param userInputList The {@link UserInputList} containing the tasks.
     */
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

    /**
     * Prints the task at the specified index. The index is 1-based, meaning the first task
     * in the list has index 1. If the index is out of bounds, an error message is displayed.
     *
     * @param userInputList The {@link UserInputList} containing the tasks.
     * @param index The index of the task to display.
     */
    public void printIndex(UserInputList userInputList, int index) {
        List<Task> tasks = userInputList.getTasks();
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            System.out.println(index + ". " + task);
        } else {
            System.out.println("Index out of bounds");
        }
    }

    /**
     * Prints the task at the specified index without displaying the index number.
     * If the index is out of bounds, an error message is displayed.
     *
     * @param userInputList The {@link UserInputList} containing the tasks.
     * @param index The index of the task to display.
     */

    public void printIndexWithoutNumber(UserInputList userInputList, int index) {
        List<Task> tasks = userInputList.getTasks();
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            System.out.println(task);
        } else {
            System.out.println("Index out of bounds");
        }
    }

    /**
     * Prints a list of tasks that match the user's search criteria.
     * If no matching tasks are found, a message indicating so is displayed.
     *
     * @param tasks The list of tasks that match the search criteria.
     */
    public void showMatchingTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Matching tasks in the list:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

}
