import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Alexis {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str); // Attempt to parse the string
            return true; // Parsing succeeded
        } catch (NumberFormatException e) {
            return false; // Parsing failed
        }
    }
    public static void main(String[] args) {
        // create list
        List<Task> tasks = new ArrayList<>();

        // Create a Scanner object to read input from the command line
        Scanner scanner = new Scanner(System.in);

        String line = "____________________________________________________________\n";
        // intro line
        System.out.println(line + "Hello! I'm [YOUR CHATBOT NAME]\n" +
                "What can I do for you?\n" + line);

        // Infinite loop to continuously echo input
        while (true) {
            // Prompt the user for input
            String input = scanner.nextLine();

            // Exit condition
            if ("bye".equalsIgnoreCase(input)) {
                System.out.println(line+ "Bye. Hope to see you again soon!\n" + line);
                break;
            }

            // mark/unmark condition
            String[] words = input.split(" ");

            if (words.length == 2) {
                // mark
                if ("mark".equalsIgnoreCase(words[0]) && isInteger(words[1])) {
                    int pos = Integer.parseInt(words[1]);
                    // pos is greater than array length
                    if (pos <= tasks.size() && pos > 0)  {
                        Task task = tasks.get(pos - 1);
                        task.mark();

                        System.out.println(line + "Nice! I've marked this task as done:\n" +
                                task.getStatusIcon() + " " + task.getDescription() + "\n" + line);
                        continue;
                    }
                } else if ("unmark".equalsIgnoreCase(words[0]) && isInteger(words[1])) {
                    int pos = Integer.parseInt(words[1]);
                    // pos is greater than array length
                    if (pos <= tasks.size() && pos > 0)  {
                        Task task = tasks.get(pos - 1);
                        task.unmark();

                        System.out.println(line + "OK, I've marked this task as not done yet:\n" +
                                task.getStatusIcon() + " " + task.getDescription() + "\n" + line);
                        continue;
                    }
                }
            }

            // display list condition
            if ("list".equalsIgnoreCase(input)) {
                if (tasks.size() > 0) {
                    int counter = 1;
                    System.out.println(line);
                    for (Task task : tasks) {
                        System.out.println(counter + "." + task.getStatusIcon() + " " + task.getDescription());
                        counter++;
                    }
                    System.out.println(line);
                }
            } else {
                // Echo the input back to the user
                tasks.add(new Task(input));
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }

        // Close the scanner
        scanner.close();
    }
}
