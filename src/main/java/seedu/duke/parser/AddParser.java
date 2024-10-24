package seedu.duke.parser;

import seedu.duke.commands.AddPatientCommand;
import seedu.duke.commands.Command;
import seedu.duke.data.state.StateType;
import seedu.duke.data.state.State;

public class AddParser implements CommandParser{
    @Override
    public Command execute(String line, State state) {
        if (state.getState() == StateType.MAIN_STATE) {
            String[] parts = line.split(" t/");
            String patientName = parts[0].substring("add ".length()).trim();
            String tag = null;
            //if tag is provided
            if (parts.length > 1) {
                tag = parts[1].trim();  // only 1 tag is expected
            }
            return new AddPatientCommand(patientName, tag);
        }
        return null;
    }
}
