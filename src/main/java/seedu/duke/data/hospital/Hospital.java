package seedu.duke.data.hospital;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Represents a hospital.
 */
@JsonPropertyOrder({"name", "patients"})
@JsonRootName("hospital")
// TODO: Add JsonInclude for null values
public class Hospital {
    private String name;
    private static final String DEFAULT_NAME = "Default Hospital";

    List<Patient> patients = new ArrayList<>();

    @JsonCreator
    public Hospital() {
        this(DEFAULT_NAME);
    }

    public Hospital(String name) {
        this.name = name;
    }

    public String getHospitalName() {
        return name;
    }

    public void setHospitalName(String name) {
        this.name = name;
    }

    public void addPatient(String patientName) {
        Patient patient = new Patient(patientName, 0);
        patients.add(patient);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void deletePatient(int index) {
        patients.remove(index);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public Patient getPatient(int index) {
        return patients.get(index);
    }

    @Override
    public String toString() {
        return "Hospital: " + name + "\nPatients: " + patients.toString();
    }






}
