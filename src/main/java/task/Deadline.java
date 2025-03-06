package task;

/**
 * Represents a Deadline task, which has a description and a deadline time.
 * Inherits from the {@link Task} class.
 */
public class Deadline extends Task {
    /**
     * The deadline time for this task.
     */
    private String by;


    /**
     * Constructs a Deadline task with the given description and deadline time.
     * The task is initially unmarked (not completed).
     * @param description The description of the task.
     * @param by The deadline time for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format includes the task's status, description and the deadline time.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string format used for saving this task.
     * The format follows: D | marked status | description | deadline time.
     *
     * @return A string in the format for saving the Deadline task.
     */
    @Override
    public String getSaveFormat() {
        // Format: D | 1 | read book | June 6th
        return "D | " + (isMarked() ? "1" : "0") + " | " + getInput() + " | " + by;
    }

}
