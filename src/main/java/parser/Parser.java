package parser;

import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

public class Parser {

    public static Task parseTaskFromSaveFormat(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null;

        String type = parts[0];
        boolean isMarked = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            if (parts.length < 4) return null;
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            if (parts.length < 4) return null;
            String[] eventTimes = parts[3].split(" to: ");
            if (eventTimes.length < 2) return null;
            task = new Event(description, eventTimes[0], eventTimes[1]);
            break;
        default:
            return null;
        }

        task.setMarked(isMarked);
        return task;
    }
}
