package task;

/**
 * Represents a ToDo task, which is a simple task with a description and a marked status.
 * Inherits from the {@link Task} class.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     * The task is initially unmarked (not completed).
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The format includes the task's status and description.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string format used for saving this ToDo task.
     * The format follows: T | marked status | description.
     *
     * @return A string in the format for saving the ToDo task.
     */
    @Override
    public String getSaveFormat() {
        return "T | " + (isMarked() ? "1" : "0") + " | " + getInput();
    }
}