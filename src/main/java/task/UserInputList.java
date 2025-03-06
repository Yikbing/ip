package task;

import java.util.ArrayList;
import java.util.List;
import storage.Storage;


/**
 * Represents a list of tasks that a user interacts with, providing methods
 * to add, remove, mark, unmark, and retrieve tasks. It also handles
 * storage of tasks through the {@link Storage} class.
 */
public class UserInputList {

    /**
     * The list of tasks that are managed by the user.
     */
    private List<Task> tasks;


    /**
     * The {@link Storage} instance used to save and load tasks.
     */
    private Storage storage;

    /**
     * Constructs a UserInputList with an empty task list and a new Storage instance.
     * Loads tasks from the file upon initialization.
     */
    public UserInputList() {
        this.tasks = new ArrayList<>();
        this.storage = new Storage();
        storage.loadTasksFromFile(tasks);
    }

    /**
     * Adds a new task to the list and saves the updated list to the file.
     *
     * @param task The task to add to the list.
     */
    public void add(Task task) {
        tasks.add(task);
        storage.writeToFile(tasks);
    }


    /**
     * Removes the task at the specified index from the list and saves the updated list to the file.
     * The index is 1-based (i.e., 1 corresponds to the first task in the list).
     *
     * @param index The index of the task to remove.
     */
    public void remove(int index) {
        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1);
        } else {
            System.out.println("Index out of bounds");
        }
        storage.writeToFile(tasks);
    }

    /**
     * Marks the task at the specified index as completed and saves the updated list to the file.
     * The index is 1-based (i.e., 1 corresponds to the first task in the list).
     *
     * @param index The index of the task to mark.
     */
    public void mark(int index) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            if (task.isMarked()) {
                System.out.println("Already Marked");
                return;
            }
            task.setMarked(true);
        } else {
            System.out.println("Index out of bounds");
        }
        storage.writeToFile(tasks);
    }

    /**
     * Unmarks the task at the specified index (marks as incomplete) and saves the updated list to the file.
     * The index is 1-based (i.e., 1 corresponds to the first task in the list).
     *
     * @param index The index of the task to unmark.
     */
    public void unmark(int index) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            if (task.isMarked()) {
                task.setMarked(false);
            }
        } else {
            System.out.println("Invalid Index");
        }
    }

    /**
     * Returns the full list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a sublist containing only the task at the specified index (1-based index).
     *
     * @param index The index of the task to retrieve (1-based).
     * @return A sublist containing the task at the specified index, or null if the index is invalid.
     */
    public List<Task> getOneTask(int index) {
        if (index > 0 && index <= tasks.size()) {
            return tasks.subList(0, index);
        }
        System.out.println("Index out of bounds");
        return null;
    }

    /**
     * Clears the list of tasks.
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

}


