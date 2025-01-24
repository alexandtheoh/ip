import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Alexis {
    public static void main(String[] args) {
        // create list
        List<String> words = new ArrayList<>();

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

            // display list condition
            if ("list".equalsIgnoreCase(input)) {
                if (words.size() > 0) {
                    int counter = 1;
                    System.out.println(line);
                    for (String word : words) {
                        System.out.println(counter + ". " + word);
                        counter++;
                    }
                    System.out.println(line);
                }
            } else {
                // Echo the input back to the user
                words.add(input);
                System.out.println(line + "added: " + input + "\n" + line);
            }

            // Exit condition
            if ("bye".equalsIgnoreCase(input)) {
                System.out.println(line+ "Bye. Hope to see you again soon!\n" + line);
                break;
            }
        }

        // Close the scanner
        scanner.close();
    }
}
