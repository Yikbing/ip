package task;

public class Deadline extends Task {
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }// when printing the things that are of Object type, the toString method gets called
    //and this overrides the toString method from Task
}
