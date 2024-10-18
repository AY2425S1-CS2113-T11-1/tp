package seedu.duke.parser;
import seedu.duke.commands.Command;
import seedu.duke.commands.UnmarkTaskCommand;
import seedu.duke.commands.MarkTaskCommand;
import seedu.duke.commands.SelectPatientCommand;
import seedu.duke.commands.AddPatientCommand;
import seedu.duke.commands.AddTaskCommand;
import seedu.duke.commands.BackCommand;
import seedu.duke.commands.DeletePatientCommand;
import seedu.duke.commands.ListTaskCommand;
import seedu.duke.commands.DeleteTaskCommand;
import seedu.duke.commands.ListPatientCommand;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class Parser {
    private static final Logger logger = Logger.getLogger("Parser");

    static {
        logger.setLevel(Level.SEVERE); // Only show warnings and errors
    }

    String line;
    State state;

    public Parser(String line, State state) {
        this.line = line;
        this.state = state;
        logger.log(Level.INFO, "Starting Parser Class...");
        assert state.getState() == StateType.MAIN_STATE
                || state.getState() == StateType.TASK_STATE : "state should be 0 or 1";
    }
    public Command parseCommand() {
        if (line == null || line.isEmpty()){
            System.out.println("Command is empty");
            return null;
        }
        String[] parts = line.split(" ");

        switch (parts[0]) {
        case "add":
            try {
                if(state.getState() == StateType.MAIN_STATE){
                    String patientName = line.substring("add ".length()).trim();
                    return new AddPatientCommand(patientName);
                } else if (state.getState() == StateType.TASK_STATE){
                    String taskName = line.substring("add ".length()).trim();
                    return new AddTaskCommand(taskName);
                }
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
                logger.log(Level.WARNING, "Add Command Error: Non-Numerical Error");
            }
            break;

        case "list":
            if (state.getState() == StateType.MAIN_STATE) {
                return new ListPatientCommand();
            } else if (state.getState() == StateType.TASK_STATE) {
                return new ListTaskCommand();
            }
            break;

        case "delete":
            try{
                if(state.getState() == StateType.MAIN_STATE){
                    return new DeletePatientCommand(parseInt(parts[1]));
                } else if (state.getState() == StateType.TASK_STATE){
                    return new DeleteTaskCommand(parseInt(parts[1]));
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out-of-bounds Error");
                logger.log(Level.WARNING, "Delete Command Error: Out-of-bounds Error");
            } catch (NumberFormatException e){
                System.out.println("Non-Numeric Error");
                logger.log(Level.WARNING, "Delete Command Error: Non-Numerical Error");
            }
            break;

        case "select":
            try{
                if(state.getState() == StateType.MAIN_STATE){
                    return new SelectPatientCommand(parseInt(parts[1]), state);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out-of-bounds Error");
                logger.log(Level.WARNING, "Select Command Error: Out-of-bounds Error");
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
                logger.log(Level.WARNING, "Select Command Error: Non-Numerical Error");
            }
            break;

        case "mark":
            try{
                if(state.getState() == StateType.TASK_STATE){
                    return new MarkTaskCommand(parseInt(parts[1]));
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out-of-bounds Error");
                logger.log(Level.WARNING, "Mark Command Error: Out-of-bounds Error");
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
                logger.log(Level.WARNING, "Mark Command Error: Non-Numerical Error");
            }
            break;

        case "unmark":
            try{
                if(state.getState() == StateType.TASK_STATE) {
                    return new UnmarkTaskCommand(parseInt(parts[1]));
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out-of-bounds Error");
                logger.log(Level.WARNING, "Select Command Error: Out-of-bounds Error");
            } catch (NumberFormatException e) {
                System.out.println("Non-Numerical Error");
                logger.log(Level.WARNING, "Select Command Error: Non-Numerical Error");
            }
            break;

        case "back":
            return new BackCommand(state); // Pass the global State object created in main to backcommand

        case "exit":
            System.out.println("Exiting program...");
            System.exit(0);  // Terminates the program gracefully
            break;

        default:
            System.out.println("Unknown command");
            logger.log(Level.WARNING, "Parser Command Error: No such command!");
        }
        return null;
    }
}
