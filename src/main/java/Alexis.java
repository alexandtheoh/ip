import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Alexis {
    protected static String line = "____________________________________________________________\n";

    public enum actionsEnum {
        bye, list
    }

    public static void main(String[] args) {
        // create alexis save
        AlexisSave save = new AlexisSave();
        save.loadSave();

        // Create a Scanner object to read input from the command line
        Scanner scanner = new Scanner(System.in);

        // intro line
        System.out.println(line + "Hello! I'm Alexis\n" +
                "What can I do for you?\n" + line);

        // Infinite loop to continuously echo input
        while (true) {
            // Prompt the user for input
            String input = scanner.nextLine();

            // Exit condition
            if (actionsEnum.bye.name().equalsIgnoreCase(input)) {
                save.save();
                System.out.println(line+ "Bye. Hope to see you again soon!\n" + line);
                break;
            }

            // mark/unmark condition
            String[] words = input.split(" ");

            if (words.length == 2) {
                System.out.println(save.performAction(input));
            }

            // display list condition
            else if (actionsEnum.list.name().equalsIgnoreCase(input)) {
                System.out.println(save.toString());
            } else { // add task
                System.out.println(save.addTask(input));
            }
        }

        // Close the scanner
        scanner.close();
    }
}
