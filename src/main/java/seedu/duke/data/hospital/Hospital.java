package seedu.duke.data.hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonPropertyOrder({"patients"})
@JsonRootName("hospital")
@JsonInclude(JsonInclude.Include.NON_NULL) // Added to exclude null values
public class Hospital {
    private static final Logger logger = Logger.getLogger(Hospital.class.getName());
    private List<Patient> patients;

    static {
        logger.setLevel(Level.SEVERE); // Only show warnings and errors
    }

    @JsonCreator
    public Hospital() {
        this.patients = new ArrayList<>();
        logger.log(Level.INFO, "Hospital initialized with an empty patient list.");
        assert patients != null : "Patients list should not be null after initialization";
    }


    public void addPatient(String name) {
        assert name != null && !name.isEmpty() : "Patient name should not be null or empty";
        logger.log(Level.INFO, "Adding new patient: {0}", name);

        Patient newPatient = new Patient(name);
        patients.add(newPatient);

        logger.log(Level.INFO, "Patient added successfully: {0}", name);
    }

    public void addPatient(String name, String tag) {
        assert name != null && !name.isEmpty() : "Patient name should not be null or empty";
        logger.log(Level.INFO, "Adding new patient: {0}", name);

        Patient newPatient = new Patient(name, tag);
        patients.add(newPatient);

        logger.log(Level.INFO, "Patient added successfully: {0}", name);
    }

    public void deletePatient(int index) throws PatientNotFoundException {
        assert index >= 0 : "Index should not be negative";
        logger.log(Level.INFO, "Deleting patient at index: {0}", index);

        if (!isValidIndex(index)) {
            logger.log(Level.WARNING, "Invalid index provided for deletion: {0}", index);
            throw new PatientNotFoundException();
        }

        patients.remove(index);
        logger.log(Level.INFO, "Patient deleted successfully at index: {0}", index);
    }

    public Patient getPatient(int index) throws PatientNotFoundException {
        assert index >= 0 : "Index should not be negative";
        logger.log(Level.INFO, "Getting patient at index: {0}", index);

        if (!isValidIndex(index)) {
            logger.log(Level.WARNING, "Invalid index provided for retrieval: {0}", index);
            throw new PatientNotFoundException();
        }

        return patients.get(index);
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < getSize();
    }

    @JsonIgnore
    public int getSize() {
        return patients.size();
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void printList() {
        StringBuilder resultMessage = new StringBuilder("Here are the patients in your list:\n");
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            String tag = (patient.getTag() != null && !patient.getTag().isEmpty()) ? " [" + patient.getTag() + "]" : "";
            resultMessage.append((i + 1)).append(". ").append(patient.getName()).append(tag).append("\n");
        }
        System.out.println(resultMessage);
        logger.log(Level.INFO, "Printed patient list.");
    }

    public boolean isDuplicatePatient(String name) {
        assert name != null && !name.isEmpty() : "Patient name should not be null or empty";

        for (Patient patient : patients) {
            if (patient.getName().equalsIgnoreCase(name)) {
                logger.log(Level.INFO, "Duplicate patient found: {0}", name);
                return true;
            }
        }

        return false;
    }

    public static class PatientNotFoundException extends Exception {
        public PatientNotFoundException() {
            super("Patient not found in the list!");
        }
    }
}
