

public class Task {
    private String input;
    private boolean isMarked;

    public Task(String input) {
        this.input = input;
        this.isMarked = false;
    }

    public String getInput() {
        return input;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    public String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + input;
    } // need to put override cause java has its own toString function which will not return
    //something we want when it is called, so we need to put override
    //cause all classes are subclasses of the Object type!! hehe
}