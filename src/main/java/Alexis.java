import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the command line
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type something and press Enter. Type 'exit' to quit.");

        // Infinite loop to continuously echo input
        while (true) {
            // Prompt the user for input
            System.out.print("Input: ");
            String input = scanner.nextLine();

            // Exit condition
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Goodbye!");
                break;
            }

            // Echo the input back to the user
            System.out.println("Echo: " + input);
        }

        // Close the scanner
        scanner.close();
    }
}
