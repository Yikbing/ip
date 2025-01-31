import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UserInputList {
    private List<Task> tasks;

    public UserInputList() {
        this.tasks = new ArrayList<>();
    }

    public void add(String input) {
        tasks.add(new Task(input, false));
    }

    public void remove(int index) {
        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1);
        } else {
            System.out.println("Index out of bounds");
        }
    }

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
    }

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

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> getOneTask(int index) {
        if (index > 0 && index <= tasks.size()) {
            return tasks.subList(0, index);
        }
        System.out.println("Index out of bounds");
        return null;
    }

    public void clear() {
        tasks.clear();
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No Tasks");
        } else {
            int index = 1;
            for (Task task : tasks) {
                System.out.println(index + ". [" + (task.isMarked() ? "X" : " ") + "] " + task.getInput());
                index++;
            }
        }
    }

    public void printIndex( int index ) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            System.out.println(index + ". [" + (task.isMarked() ? "X" : " ") + "] " + task.getInput());
        } else {
            System.out.println("Index out of bounds");
        }
    }

}

class Task {
    private String input;
    private boolean isMarked;

    public Task(String input, boolean isMarked) {
        this.input = input;
        this.isMarked = isMarked;
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


}
