package task;

import java.util.List;
import java.util.ArrayList;


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
    //cause all classes are subclasses of the Object type!!

    public String getSaveFormat(){
        return "T | " + (isMarked() ? "1" : "0") + " | " + getInput();
    };

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

