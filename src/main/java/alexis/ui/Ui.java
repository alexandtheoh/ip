package alexis.ui;

import java.util.Arrays;
import java.util.Scanner;

import alexis.exceptions.InvalidPosException;
import tasks.Deadline;
import tasks.Event;
import tasks.Storage;
import tasks.Task;
import tasks.TasksList;
import tasks.Todo;


/**
 * Represents a UI for alexis
 * A {@code Ui} responsible for handling user input and alexis output
 */
public class Ui {
    private static String line = "____________________________________________________________\n";
    private static String invalid = line + "Invalid command.\n" + line;

    private enum TaskTypeEnum {
        todo, deadline, event
    }

    private enum ActionsEnum {
        mark, unmark, delete, bye, list, search
    }

    private Storage storage;
    private TasksList tasksList;

    /**
     * Creates a Ui with a storage and a tasksList object
     */
    public Ui(Storage storage, TasksList tasksList) {
        this.storage = storage;
        this.tasksList = tasksList;
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str); // Attempt to parse the string
            return true; // Parsing succeeded
        } catch (NumberFormatException e) {
            return false; // Parsing failed
        }
    }

    /**
     * Starts the terminal UI
     */
    public void start() {
        // Create a Scanner object to read input from the command line
        Scanner scanner = new Scanner(System.in);

        // intro line
        System.out.println(line + "Hello! I'm Alexis\n"
                + "What can I do for you?\n" + line);

        // Infinite loop to continuously echo input
        while (scanner.hasNextLine()) {
            // Prompt the user for input
            String input = scanner.nextLine();

            // Exit condition
            if (ActionsEnum.bye.name().equalsIgnoreCase(input)) {
                storage.save();
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                break;
                // display list condition
            } else if (ActionsEnum.list.name().equalsIgnoreCase(input)) {
                System.out.println(line + "Here are the tasks in your list:\n"
                        + this.tasksList.toString() + line);
            } else {
                // mark/unmark condition
                String[] words = input.split(" ");

                if (ActionsEnum.search.name().equalsIgnoreCase(words[0])) { // search
                    String searchString = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    System.out.println(line + "Here are the matching tasks in your list:\n"
                            + this.tasksList.search(searchString) + line);
                } else if (words.length == 2 && isInteger(words[1])) { // case of mark/unmark/delete
                    if (ActionsEnum.mark.name().equalsIgnoreCase(words[0])) {
                        int pos = Integer.parseInt(words[1]);
                        try {
                            Task task = tasksList.markTask(pos);
                            System.out.println(line + "Nice! I've marked this task as done:\n"
                                    + task.getStatusIcon() + " " + task.getDescription() + "\n" + line);
                        } catch (InvalidPosException e) {
                            System.out.println(Ui.invalid);
                        }
                    } else if (ActionsEnum.unmark.name().equalsIgnoreCase(words[0])) {
                        int pos = Integer.parseInt(words[1]);
                        try {
                            Task task = tasksList.unmarkTask(pos);
                            System.out.println(line + "OK, I've marked this task as not done yet:\n"
                                    + task.getStatusIcon() + " " + task.getDescription() + "\n" + line);
                        } catch (InvalidPosException e) {
                            System.out.println(Ui.invalid);
                        }
                    } else if (ActionsEnum.delete.name().equalsIgnoreCase(words[0])) {
                        int pos = Integer.parseInt(words[1]);
                        try {
                            Task task = tasksList.deleteTask(pos);
                            System.out.println(line + "Noted. I've removed this task: \n"
                                    + task.toString() + "\nNow you have " + this.tasksList.getSize()
                                    + " tasks in the list.\n" + line);
                        } catch (InvalidPosException e) {
                            System.out.println(Ui.invalid);
                        }
                    } else {
                        System.out.println(Ui.invalid);
                        continue;
                    }
                } else { // add task
                    String[] taskString = input.split("/");
                    String[] desArr = taskString[0].split(" ");
                    String taskType = desArr[0];
                    String description = String.join(" ", Arrays.copyOfRange(desArr, 1, desArr.length));

                    Task task = new Task("0", "dummy");

                    if (TaskTypeEnum.todo.name().equalsIgnoreCase(taskType)) {
                        try {
                            task = new Todo("0", description);
                            this.tasksList.addTask(task);
                        } catch (Exception e) {
                            System.out.println(line + e.getMessage() + "\n" + line);
                            continue;
                        }
                    } else if (TaskTypeEnum.deadline.name().equalsIgnoreCase(taskType)) {
                        String[] byArr = taskString[1].split(" ");
                        String by = String.join(" ", Arrays.copyOfRange(byArr, 1, byArr.length));

                        task = new Deadline("0", description, by);
                        this.tasksList.addTask(task);
                    } else if (TaskTypeEnum.event.name().equalsIgnoreCase(taskType)) {
                        String[] fromArr = taskString[1].split(" ");
                        String from = String.join(" ", Arrays.copyOfRange(fromArr, 1, fromArr.length));

                        String[] toArr = taskString[2].split(" ");
                        String to = String.join(" ", Arrays.copyOfRange(toArr, 1, toArr.length));

                        task = new Event("0", description, from, to);
                        this.tasksList.addTask(task);
                    } else {
                        System.out.println(Ui.invalid);
                        continue;
                    }

                    // print task added message
                    System.out.println(line + "Got it. I've added this task: \n"
                            + task + "\nNow you have " + this.tasksList.getSize()
                            + " tasks in the list.\n" + line);
                }
            }
        }

        // Close the scanner
        scanner.close();
    }
}
