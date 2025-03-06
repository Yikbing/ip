package task;


/**
 * Represents an Event task, which has a description and a time range (from and to).
 * Inherits from the {@link Task} class.
 */
public class Event extends Task {
    /**
     * The starting time of the event.
     */
    private String from;

    /**
     * The ending time of the event.
     */
    private String to;

    /**
     * Constructs an Event task with the given description, starting time, and ending time.
     * The task is initially unmarked (not completed).
     * @param description The description of the event.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     * The format includes the task's status, description, the starting time, and the ending time.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the string format used for saving this task.
     * The format follows: E | marked status | description | starting time and ending time.
     *
     * @return A string in the format for saving the Event task.
     */
    @Override
    public String getSaveFormat() {
        // Format: E | 1 | project meeting | Aug 6th 2-4pm
        return "E | " + (isMarked() ? "1" : "0") + " | " + getInput() + " | " + from + " to: " + to;
    }

}

