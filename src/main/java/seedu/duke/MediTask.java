package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.HospitalCommand;
import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.hospital.Patient;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;
import seedu.duke.parser.Parser;
import seedu.duke.storage.StorageFile;
import seedu.duke.ui.Ui;

public class MediTask {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        Ui ui = new Ui();
        Hospital hospital = new Hospital();
        StorageFile storage = new StorageFile();

        ui.showWelcome();
        // Start in MAIN_STATE
        State currentState = new State(StateType.MAIN_STATE);
        assert currentState != null : "Current state should not be null.";

        // Load data from file
        hospital = storage.load();

        HospitalCommand.setHospital(hospital);

        // variable to hold the selected patient
        Patient selectedPatient = null;

        while (true) {
            if (currentState.getState() == StateType.MAIN_STATE) {

                ui.showMainScreen();
                String commandInput = ui.readCommand();

                // parse and execute the command, and also pass in the currentState
                Parser parser = new Parser();
                Command command = parser.parseCommand(commandInput, currentState);

                if (command != null) {
                    command.execute();

                    // handle patient selection
                    if (command instanceof seedu.duke.commands.SelectPatientCommand) {
                        try {
                            // convert user input to index
                            int patientIndex = Integer.parseInt(commandInput.split(" ")[1]) - 1;
                            // check if patient index fall within the number of patients in the hospital
                            assert patientIndex >= 0 && patientIndex < hospital.getSize() : "Invalid patient index.";
                            // get selected patient
                            selectedPatient = hospital.getPatient(patientIndex);
                        } catch (Exception e) {
                            System.out.println("invalid patient selection.");
                        }
                    }
                }
            } else if (currentState.getState() == StateType.TASK_STATE) {
                // show task screen for the selected patient
                if (selectedPatient != null) {
                    assert selectedPatient != null : "A patient must be selected in TASK_STATE.";
                    // display selectedPatient name
                    ui.showTaskScreen(selectedPatient.getName(), selectedPatient.getTag());
                }
                String commandInput = ui.readCommand();

                // parse and execute commands in TASK_STATE
                // pass the State object
                Parser parser = new Parser();
                Command command = parser.parseCommand(commandInput, currentState);

                if (command != null) {
                    command.setData(selectedPatient.getTaskList());
                    CommandResult result = command.execute();
                    ui.showToUser(result.getFeedbackToUser());
                }
            }
            // Save hospital data to file
            storage.save(hospital);
        }
    }
}
