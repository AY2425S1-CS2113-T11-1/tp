package seedu.duke.ui;
import java.util.Scanner;

import seedu.duke.common.Messages;

/**
 * The Ui class handles the user interface for the task management application.
 * It provides methods for displaying messages and interacting with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showMainScreen() {
        showLine();
        System.out.print(Messages.MESSAGE_MAIN_PROMPT);
    }

    public void showTaskScreen(String patientName, String patientTag) {
        String tagFormatted = "";
        if (patientTag != null) {
            tagFormatted = " [" + patientTag + "]";
        }
        showLine();
        System.out.println("Patient: " + patientName + tagFormatted);
        System.out.print(Messages.MESSAGE_MAIN_PROMPT);
    }

    public void showToUser(String message) {
        System.out.println(message);
    }


    /**
     * Reads a command inputted by the user.
     *
     * @return the user input as a String
     */

    public String readCommand() {
        assert scanner != null;
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return null;
        }
    }

    /**
     * Displays a horizontal line to the console.
     */
    public void showLine() {
        System.out.println(Messages.MESSAGE_BREAKLINE);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println(Messages.LOGO);
        System.out.println(Messages.MESSAGE_WELCOME);
    }

    /**
     * Closes the scanner
     */
    public void closeScanner() {
        scanner.close();
    }

}
