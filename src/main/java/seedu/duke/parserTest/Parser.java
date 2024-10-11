package seedu.duke.parserTest;
import static java.lang.Integer.parseInt;

public class Parser {
    String line;
    int state;

    public Parser(String line, int state) {
        this.line = line;
        this.state = state;
    }

    public int parseCommand() {
        String[] parts = line.split(" ");

        switch (parts[0]) {
        case "add":
            try {
                return parseInt(parts[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Non-Numerical Error");
            }
            break;

        case "list":
            if (state == 0) {
                return 1;
            } else if (state == 1) {
                return 2;
            }
            break;

        case "delete", "select", "mark", "unmark":
            try {
                return parseInt(parts[1]) - 1;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Number out-of-range");
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
            }
            break;

        default:
            System.out.println("Unknown command");
        }
        return 0;
    }
}
