package seedu.duke.storage;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.task.TaskList.TaskNotFoundException;

public class StorageFileTest {

    String filePath = "src/test/java/seedu/duke/data/hospital_data_load.json";
    private Hospital hospital;
    String hospitalJson;

    @BeforeEach
    public void setUp() {
        hospital = new Hospital("NUH");
        hospital.addPatient("Alice");
        hospital.addPatient("Bob");

        hospital.getPatient(0).getTasks().addTask("Consultation");
        hospital.getPatient(0).getTasks().addTask("Medication");
        hospital.getPatient(1).getTasks().addTask("Surgery");
        hospital.getPatient(1).getTasks().addTask("Medication");


        hospitalJson = """
{
  "patients" : [ {
    "name" : "Alice",
    "tasksList" : {
      "tasks" : [ {
        "description" : "Consultation",
        "isDone" : false
      }, {
        "description" : "Medication",
        "isDone" : false
      } ]
    }
  }, {
    "name" : "Bob",
    "tasksList" : {
      "tasks" : [ {
        "description" : "Surgery",
        "isDone" : false
      }, {
        "description" : "Medication",
        "isDone" : false
      } ]
    }
  } ],
  "hospitalName" : "NUH"
}
    """;
    }

    @Test
    public void loadFromFile_success() throws TaskNotFoundException {
        Hospital loadHospital = JsonUtil.loadFromFile(filePath);

        assertEquals("NUH", hospital.getHospitalName());
        assertEquals("Alice", loadHospital.getPatient(0).getName());
        assertEquals("Bob", loadHospital.getPatient(1).getName());
        assertEquals("Consultation", loadHospital.getPatient(0).getTasks().getTask(0).getDescription());
        assertEquals("Medication", loadHospital.getPatient(0).getTasks().getTask(1).getDescription());
        assertFalse(loadHospital.getPatient(0).getTasks().getTask(0).isDone());
    }

    @Test
    public void saveToFile_success() {
        String pathToSave = "src/test/java/seedu/duke/data/hospital_data_save.json";
        File file = new File(pathToSave);
        // check if file exists
        if (file.exists()) {
            file.delete();
        }

        JsonUtil.saveToFile(hospital, pathToSave);
        assertTrue(file.exists());
    }
}
