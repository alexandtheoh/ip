import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Alexis {
    public enum taskTypeEnum {
        todo, deadline, event
    }

    public enum actionsEnum {
        bye, add , list, mark, unmark, delete
    }
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str); // Attempt to parse the string
            return true; // Parsing succeeded
        } catch (NumberFormatException e) {
            return false; // Parsing failed
        }
    }

    public static String addTask(List<Task> tasks, Task task) {
        String line = "____________________________________________________________\n";
        tasks.add(task);

        return line + "Got it. I've added this task: \n"
                + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n"
                    + line;
    }
    public static void main(String[] args) {

        // create list
        List<Task> tasks = new ArrayList<>();

        // Create a Scanner object to read input from the command line
        Scanner scanner = new Scanner(System.in);

        String line = "____________________________________________________________\n";
        // intro line
        System.out.println(line + "Hello! I'm Alexis\n" +
                "What can I do for you?\n" + line);

        // Infinite loop to continuously echo input
        while (true) {
            // Prompt the user for input
            String input = scanner.nextLine();

            // Exit condition
            if (actionsEnum.bye.name().equalsIgnoreCase(input)) {
                System.out.println(line+ "Bye. Hope to see you again soon!\n" + line);
                break;
            }

            // mark/unmark condition
            String[] words = input.split(" ");

            if (words.length == 2) {
                // mark
                if (actionsEnum.mark.name().equalsIgnoreCase(words[0]) && isInteger(words[1])) {
                    int pos = Integer.parseInt(words[1]);
                    // pos is greater than array length
                    if (pos <= tasks.size() && pos > 0)  {
                        Task task = tasks.get(pos - 1);
                        task.mark();

                        System.out.println(line + "Nice! I've marked this task as done:\n" +
                                task.getStatusIcon() + " " + task.getDescription() + "\n" + line);
                        continue;
                    }
                } else if (actionsEnum.unmark.name().equalsIgnoreCase(words[0]) && isInteger(words[1])) {
                    int pos = Integer.parseInt(words[1]);
                    // pos is greater than array length
                    if (pos <= tasks.size() && pos > 0)  {
                        Task task = tasks.get(pos - 1);
                        task.unmark();

                        System.out.println(line + "OK, I've marked this task as not done yet:\n" +
                                task.getStatusIcon() + " " + task.getDescription() + "\n" + line);
                        continue;
                    }
                } else if (actionsEnum.delete.name().equalsIgnoreCase(words[0]) && isInteger(words[1])) {
                    int pos = Integer.parseInt(words[1]);
                    // pos is greater than array length
                    if (pos <= tasks.size() && pos > 0)  {
                        Task task = tasks.get(pos - 1);
                        tasks.remove(pos - 1);

                        System.out.println(line + "Noted. I've removed this task: \n"
                                + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n"
                                + line);
                        continue;
                    }
                }
            }

            // display list condition
            if (actionsEnum.list.name().equalsIgnoreCase(input)) {
                if (tasks.size() > 0) {
                    int counter = 1;
                    System.out.println(line + "Here are the tasks in your list:");
                    for (Task task : tasks) {
                        System.out.println(counter + "." + task.toString());
                        counter++;
                    }
                    System.out.println(line);
                }
            } else { // add task
                String[] taskString = input.split("/");
                String[] desArr = taskString[0].split(" ");
                String taskType = desArr[0];
                String description = String.join(" ", Arrays.copyOfRange(desArr, 1, desArr.length));

                if (taskTypeEnum.todo.name().equalsIgnoreCase(taskType)) {
                    try {
                        Task todo = new Todo(description);
                        System.out.println(addTask(tasks, todo));
                    } catch (Exception e) {
                        System.out.println(line + e.getMessage() + "\n" + line);
                    }
                }
                // Handle 'deadline' task type
                else if (taskTypeEnum.deadline.name().equalsIgnoreCase(taskType)) {
                    String[] byArr = taskString[1].split(" ");
                    String by = String.join(" ", Arrays.copyOfRange(byArr, 1, byArr.length));

                    Task deadline = new Deadline(description, by);
                    System.out.println(addTask(tasks, deadline));
                }
                // Handle 'event' task type
                else if (taskTypeEnum.event.name().equalsIgnoreCase(taskType)) {
                    String[] fromArr = taskString[1].split(" ");
                    String from = String.join(" ", Arrays.copyOfRange(fromArr, 1, fromArr.length));

                    String[] toArr = taskString[2].split(" ");
                    String to = String.join(" ", Arrays.copyOfRange(toArr, 1, toArr.length));

                    Task event = new Event(description, from, to);
                    System.out.println(addTask(tasks, event));
                }
                // Invalid task type
                else {
                    System.out.println(line + "Invalid command.\n" + line);
                }
            }
        }

        // Close the scanner
        scanner.close();
    }
}
