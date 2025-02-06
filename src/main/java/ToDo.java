public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    } // when printing the things that are of Object type, the toString method gets called
    //and this overrides the toString method from Task

}