package seedu.duke.commands;

import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.state.State;
import seedu.duke.data.state.StateType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectPatientCommand extends HospitalCommand {
    public static final String COMMAND_WORD = "select";
    public static final String MESSAGE_SUCCESS = "Selected patient: %1$s";
    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient not found in the list!";

    private static final Logger logger = Logger.getLogger(SelectPatientCommand.class.getName());

    private State state; // To hold reference to the global state object
    private int index;

    static {
        logger.setLevel(Level.SEVERE);
    }

    public SelectPatientCommand(int index, State state) {
        this.index = index - 1;
        this.state = state;
    }

    @Override
    public CommandResult execute() {
        assert index >= 0 : "Index should be non-negative";
        assert state != null : "State object should not be null";
        assert hospital != null : "Hospital object should not be null";

        try {
            String patientName = hospital.getPatient(index).getName();
            String patientTag = hospital.getPatient(index).getTag();
            String formattedTag = "";
            if (patientTag != null){
                formattedTag = " [" + patientTag + "]";
            }
            String resultMessage = String.format(MESSAGE_SUCCESS, patientName + formattedTag);
            state.setState(StateType.TASK_STATE);
            logger.log(Level.INFO, "System is now in TASK_STATE for patient: {0}", patientName);
            System.out.println(resultMessage);
            return new CommandResult(resultMessage);
        } catch (Hospital.PatientNotFoundException e) {
            logger.log(Level.SEVERE, "Attempted to select a patient at an invalid index: {0}", index);
            System.out.println(MESSAGE_PATIENT_NOT_FOUND);
            return new CommandResult(MESSAGE_PATIENT_NOT_FOUND);
        }
    }
}
