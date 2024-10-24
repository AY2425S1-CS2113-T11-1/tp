package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.data.state.State;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    private static final Logger LOGGER = Logger.getLogger("Parser");

    public Command parseCommand(String line, State state){
        if (line == null || line.isEmpty()){
            System.out.println("Command is empty");
            return null;
        }
        String[] parts = line.split(" ");

        switch(parts[0]){
        case "todo":
            try{
                return new AddTodoParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "Todo Command Error: Non-Numerical Error");

            }
            break;

        case "Deadline":
            try{
                return new AddDeadlineParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "Deadline Command Error: Non-Numerical Error");

            }
            break;

        case "add":
            try{
                return new AddParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "Add Command Error: Non-Numerical Error");
            }
            break;

        case "list":
            try{
                return new ListParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "List Command Error: Non-Numerical Error");
            }
            break;

        case "delete":
            try{
                return new DeleteParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "Delete Command Error: Non-Numerical Error");
            }
            break;

        case "select":
            try{
                return new SelectParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "Select Command Error: Non-Numerical Error");
            }
            break;

        case "mark":
            try{
                return new MarkParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "Mark Command Error: Non-Numerical Error");
            }
            break;

        case "unmark":
            try{
                return new UnmarkParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "Unmark Command Error: Non-Numerical Error");
            }
            break;

        case "back":
            try{
                return new BackParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "Back Command Error: Non-Numerical Error");
            }
            break;

        case "find":
            try{
                return new FindParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "Find Command Error: Non-Numerical Error");
            }
            break;

        case "exit":
            try{
                return new ExitParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "Exit Command Error: Non-Numerical Error");
            }
            break;

        case "edit":
            try {
                return new EditParser().execute(line, state);
            } catch (NumberFormatException e) {
                System.out.println("Number format exception");
                LOGGER.log(Level.WARNING, "Edit Command Error: Non-Numerical Error");
            }
            break;

        default:
            System.out.println("Unknown command");
            LOGGER.log(Level.WARNING, "Unknown Command Error");
        }
        return null;
    }
}
