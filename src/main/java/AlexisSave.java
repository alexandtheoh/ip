import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class AlexisSave {
    protected static String line = "____________________________________________________________\n";
    private static String filePath = "data/alexis.txt";
    private List<Task> tasks;

    public enum taskTypeEnum {
        T, D, E, todo, deadline, event
    }

    public enum actionsEnum {
        mark, unmark, delete
    }
    public AlexisSave() {
        tasks = new ArrayList<>();
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str); // Attempt to parse the string
            return true; // Parsing succeeded
        } catch (NumberFormatException e) {
            return false; // Parsing failed
        }
    }

    public void loadSave() {

        try (Scanner scanner = new Scanner(new File(AlexisSave.filePath))) {
            while (scanner.hasNextLine()) {
                try {
                    // parse string into task
                    String taskString = scanner.nextLine();

                    String[] taskStringArr = taskString.split("\\|");
                    if (taskTypeEnum.T.name().equals(taskStringArr[0])) {
                        Task todo = new Todo(taskStringArr[1], taskStringArr[2]);
                        this.tasks.add(todo);
                    } else if (taskTypeEnum.D.name().equals(taskStringArr[0])) {
                        Task deadline = new Deadline(taskStringArr[1], taskStringArr[2], taskStringArr[3]);
                        this.tasks.add(deadline);
                    } else if (taskTypeEnum.E.name().equals(taskStringArr[0])) {
                        Task event = new Event(taskStringArr[1], taskStringArr[2], taskStringArr[3], taskStringArr[4]);
                        this.tasks.add(event);
                    } else {
                        System.out.println("error loading save data");
                    }
                } catch (Exception e) {
                    System.out.println("error loading save data");
                }
            }
        } catch (FileNotFoundException e) {
            //System.out.println(e.getMessage());
        }
    }

    public String performAction(String actionString) {
        String[] words = actionString.split(" ");

        if (actionsEnum.mark.name().equalsIgnoreCase(words[0]) && isInteger(words[1])) {
            int pos = Integer.parseInt(words[1]);
            // pos is greater than array length
            if (pos <= this.tasks.size() && pos > 0) {
                Task task = this.tasks.get(pos - 1);
                task.mark();

                return line + "Nice! I've marked this task as done:\n" +
                        task.getStatusIcon() + " " + task.getDescription() + "\n" + line;
            }
        } else if (actionsEnum.unmark.name().equalsIgnoreCase(words[0]) && isInteger(words[1])) {
            int pos = Integer.parseInt(words[1]);
            // pos is greater than array length
            if (pos <= this.tasks.size() && pos > 0) {
                Task task = this.tasks.get(pos - 1);
                task.unmark();

                return line + "OK, I've marked this task as not done yet:\n" +
                        task.getStatusIcon() + " " + task.getDescription() + "\n" + line;
            }
        } else if (actionsEnum.delete.name().equalsIgnoreCase(words[0]) && isInteger(words[1])) {
            int pos = Integer.parseInt(words[1]);
            // pos is greater than array length
            if (pos <= this.tasks.size() && pos > 0) {
                Task task = this.tasks.get(pos - 1);
                tasks.remove(pos - 1);

                return line + "Noted. I've removed this task: \n"
                        + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n"
                        + line;
            }
        }

        return line + "Invalid command.\n" + line;
    }

    public String addTask(String input) {
        String[] taskString = input.split("/");
        String[] desArr = taskString[0].split(" ");
        String taskType = desArr[0];
        String description = String.join(" ", Arrays.copyOfRange(desArr, 1, desArr.length));

        if (taskTypeEnum.todo.name().equalsIgnoreCase(taskType)) {
            try {
                Task todo = new Todo("0", description);
                return this.add(todo);
            } catch (Exception e) {
                return line + e.getMessage() + "\n" + line;
            }
        }
        // Handle 'deadline' task type
        else if (taskTypeEnum.deadline.name().equalsIgnoreCase(taskType)) {
            String[] byArr = taskString[1].split(" ");
            String by = String.join(" ", Arrays.copyOfRange(byArr, 1, byArr.length));

            Task deadline = new Deadline("0", description, by);
            return this.add(deadline);
        }
        // Handle 'event' task type
        else if (taskTypeEnum.event.name().equalsIgnoreCase(taskType)) {
            String[] fromArr = taskString[1].split(" ");
            String from = String.join(" ", Arrays.copyOfRange(fromArr, 1, fromArr.length));

            String[] toArr = taskString[2].split(" ");
            String to = String.join(" ", Arrays.copyOfRange(toArr, 1, toArr.length));

            Task event = new Event("0", description, from, to);
            return this.add(event);
        }
        // Invalid task type
        else {
            return line + "Invalid command.\n" + line;
        }
    }

    private String add(Task task) {
        this.tasks.add(task);
        return line + "Got it. I've added this task: \n"
                + task.toString() + "\nNow you have " + this.tasks.size() + " tasks in the list.\n"
                + line;
    }

    public String toString() {
        StringBuilder taskList = new StringBuilder();
        int counter = 1;

        taskList.append(line).append("Here are the tasks in your list:\n");
        for (Task task : this.tasks) {
            taskList.append(counter).append(".").append(task.toString()).append("\n");
            counter++;
        }
        taskList.append(line);

        return taskList.toString();
    }

    public void save() {
        StringBuilder taskSave = new StringBuilder();
        for (Task task : this.tasks) {
            taskSave.append(task.toSaveString()).append("\n");
        }

        writeFile(taskSave.toString());
    }

    private void writeFile(String content) {
        try {
            // create directory
            String directoryPath = "data";
            // Create a File object for the directory
            File directory = new File(directoryPath);

            // Check if the directory exists
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create a File object
            File file = new File(AlexisSave.filePath);
            file.createNewFile();

            // Write to the file
            FileWriter writer = new FileWriter(AlexisSave.filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        AlexisSave alexis = new AlexisSave();
        alexis.loadSave();
        System.out.print(alexis);
        alexis.save();
    }
}
