import java.util.Scanner;

public class Alexis {
    public static void main(String[] args) {
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

            // Echo the input back to the user
            System.out.println(line + input + "\n" + line);
        }

        // Close the scanner
        scanner.close();
    }
}
