# Terry User Guide

Terry is a **desktop app for managing tasks**, optimized for use via a Command Line Interface(CLI).

- [Quick Start](#anchor-point-quick-start)
- [Features](#anchor-point-features)
    - [list](#anchor-point-list)
    - [tasks](#anchor-point-tasks)
        - [todo](#anchor-point-todo)
        - [deadline](#anchor-point-deadline)
        - [event](#anchor-point-event)
    - [mark](#anchor-point-mark)
    - [delete](#anchor-point-delete)
    - [find](#anchor-point-find)
    - [bye](#anchor-point-bye)
- [Saving the data](#anchor-point-save)
- [Known Issues](#anchor-point-issues)
- [Command Summary](#anchor-point-summary)

  <a name="anchor-point-quick-start"></a>
  ## Quick Start
  1. Ensure you have Java 17 or above installed in your Computer.
     **Mac Users:** Ensure you have the precise JDK version prescribed <a href="https://se-education.org/guides/tutorials/javaInstallationMac.html" target="_blank">here</a>.

  2. Download the latest .jar file <a href="https://github.com/Yikbing/ip/releases/download/v0.2/ip.jar" target="_blank">here</a>.

  3. Copy the file to the folder you want to use as the _home folder_ for Terrys.

  4. Open a command terminal, `cd` into that folder you put the jar file in and use `java -jar Terry.jar` command to run the application.

  5. Type the commands in the terminal and press enter to execute it! you can refer to the [Features tab](#anchor-point-features) to try some commands.

  <a name="anchor-point-features"></a>
  ## Features

  <a name="anchor-point-list"></a>
  ### list
  Shows the entire list of tasks.

  Format: `list`

  ---

  <a name="anchor-point-tasks"></a> 
  ### tasks
  There are three different types of tasks: `todo`, `deadline` and `event`

  ---

  <a name="anchor-point-todo"></a>
  ### todo
  A `todo` task is a simple task with a description and a marked status.

  Format: `todo [description]`

  ---

  <a name="anchor-point-deadline"></a>
  ### deadline
  A `deadline` task is a task with a description, marked status, and a time to complete it by.

  Format: `deadline [description] [/by TIME_TO_COMPLETE]`

  ---

  <a name="anchor-point-event"></a>
  ### event
  An `event` task is a task with a description, marked status, a time when it starts, and a time when it ends.

  Format: `event [description] [/from TIME_IT_STARTS] [/to TIME_IT_ENDS]`

  ---

  <a name="anchor-point-mark"></a>
  ### mark
  `mark` allows you to mark a task to indicate that it is completed.

  Format: `mark INDEX_OF_TASK`
  - Marks the task at `INDEX_OF_TASK`. The index refers to the number shown in the displayed list. The index **must be a positive integer** 1,2,3...

  Example: `mark 1`. this will then return task 1 with an `X` marked in the box on the right of the box containing the task type.

  ---

  <a name="anchor-point-delete"></a>
  ### delete
  `delete` allows you to delete a task from the list.

  Format: `delete INDEX_OF_TASK`
  - Deletes the task at `INDEX_OF_TASK`. The index refers to the number shown in the displayed list. The index **must be a positive integer** 1,2,3...

  Example: `delete 1`. this will then remove task 1 from the list and when `list` is called again, all the items would have moved up by one position.

  ---

  <a name="anchor-point-find"></a>
  ### find
  `find` allows you to find all tasks containing the keyword within its description.

  Format: `find DESCRIPTION`
  - Finds all tasks with DESCRIPTION within the task's description.

  Example: `find me`. If the description of the tasks have "me" inside it, like "homework" or "meet friends", these tasks will be listed out.

  ---

  <a name="anchor-point-bye"></a>
  ### bye
  `bye` lets the user exit the program.

  Format: `bye`
    
  ---

  <a name="anchor-point-save"></a>
  ### Saving the data
  The list data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

  ---

  <a name="anchor-point-issues"></a>
  ### Known Issues
  - The first time a task is added to the list, the error `An error occurred while writing to the file` will appear before 
  the task is added to the list. This will only happen once and will not affect any writings to the text file.

  ---

    <a name="anchor-point-summary"></a>
  ## Command Summary

| Command       | Format                                      | Description                                                                 |
|--------------|--------------------------------------------|-----------------------------------------------------------------------------|
| `list`       | `list`                                     | Shows the entire list of tasks.                                            |
| `todo`       | `todo [description]`                       | Adds a simple task with a description.                                     |
| `deadline`   | `deadline [description] [/by TIME_TO_COMPLETE]` | Adds a task with a deadline to complete it by.                             |
| `event`      | `event [description] [/from TIME_IT_STARTS] [/to TIME_IT_ENDS]` | Adds an event task with start and end times.                               |
| `mark`       | `mark INDEX_OF_TASK`                       | Marks a task as completed.                                                 |
| `delete`     | `delete INDEX_OF_TASK`                     | Deletes a task from the list.                                              |
| `find`       | `find DESCRIPTION`                         | Finds all tasks containing the keyword in their description.               |
| `bye`        | `bye`                                      | Exits the program.                                                         |
| **Auto-Save** | N/A                                        | The app automatically saves data after any command that changes the list.  |



