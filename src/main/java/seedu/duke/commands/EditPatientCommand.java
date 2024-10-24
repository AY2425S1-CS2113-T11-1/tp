package seedu.duke.commands;

import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.hospital.Patient;

public class EditPatientCommand extends HospitalCommand {
    public static final String MESSAGE_SUCCESS = "Patient updated: %1$s";
    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient not found at index %1$d";

    private int index;
    private String newTag;

    public EditPatientCommand(int index, String newTag) {
        this.index = index - 1; // Convert to 0-based index
        this.newTag = newTag;
    }

    @Override
    public CommandResult execute() {
        try {
            //put patient object into 'variable' patient
            Patient patient = hospital.getPatient(index);

            //update tag. clear it if empty string provided
            patient.setTag(newTag.isEmpty() ? null : newTag);

            //print success message
            String resultMessage = String.format(MESSAGE_SUCCESS, patient.getName() + patient.getFormattedTag());
            System.out.println(resultMessage);
            return new CommandResult(resultMessage);
        } catch (Hospital.PatientNotFoundException e) {
            return new CommandResult(String.format(MESSAGE_PATIENT_NOT_FOUND, index + 1));
        }
    }
}
