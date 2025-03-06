package task;

import java.util.List;
import java.util.ArrayList;


/**
 * Represents a task with a description and a marked status.
 * The task can be marked as complete or incomplete
 */
public class Task {

    /**
     * The description or input for this task.
     */
    private String input;

    /**
     * Indicates whether the task is marked as completed or not.
     */
    private boolean isMarked;


    /**
     * Constructs a Task with the given description.
     * The task is initially unmarked (not completed).
     *
     * @param input The description of the task.
     */
    public Task(String input) {
        this.input = input;
        this.isMarked = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The input description of the task.
     */
    public String getInput() {
        return input;
    }

    /**
     * Checks if the task is marked as completed.
     *
     * @return {@code true} if the task is marked, otherwise {@code false}.
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Sets the status of the task to marked or unmarked.
     *
     * @param isMarked {@code true} to mark the task as completed, {@code false} to unmark it.
     */
    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    /**
     * Returns a string representation of the task's status icon.
     * Displays "[X]" if the task is marked as completed, and "[ ]" if not.
     *
     * @return The task's status icon ("[X]" or "[ ]").
     */
    public String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    /**
     * Returns a string representation of the Task object.
     * The format includes the task's status icon and description.
     *
     * @return A string representation of the Task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + input;
    }

    /**
     * Returns the string format used for saving this task.
     * The format follows: T | marked status | description.
     *
     * @return A string in the format for saving the Task.
     */
    public String getSaveFormat(){
        return "T | " + (isMarked() ? "1" : "0") + " | " + getInput();
    };

    /**
     * Searches for tasks containing a given keyword in their description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @param tasks The list of tasks to search through.
     * @return A list of tasks that contain the keyword in their description.
     */
    public List<Task> findTasksByKeyword(String keyword, List<Task> tasks) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getInput().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }

}

