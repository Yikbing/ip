package task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }// when printing the things that are of Object type, the toString method gets called
    //and this overrides the toString method from Task
    @Override
    public String getSaveFormat() {
        // Format: E | 1 | project meeting | Aug 6th 2-4pm
        return "E | " + (isMarked() ? "1" : "0") + " | " + getInput() + " | " + from + " to: " + to;
    }

}

