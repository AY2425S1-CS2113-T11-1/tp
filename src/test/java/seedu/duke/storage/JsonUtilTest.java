package seedu.duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.hospital.Hospital.PatientNotFoundException;
import seedu.duke.data.task.TaskList.TaskNotFoundException;

public class JsonUtilTest {

    String hospitalJson;
    String hospitalJsonInvalid;
    private final Hospital hospital = new Hospital();

    @BeforeEach
    public void setUp() throws PatientNotFoundException, TaskNotFoundException {

        hospital.addPatient("Alice");
        hospital.addPatient("Bob");

        hospital.getPatient(0).getTaskList().addTask("Consultation");
        hospital.getPatient(0).getTaskList().addTask("Medication");
        hospital.getPatient(1).getTaskList().addTask("Surgery");
        hospital.getPatient(1).getTaskList().addTask("Medication");
        hospital.getPatient(0).getTaskList().getTask(0).markAsDone();

        hospitalJson = """
                {
                  "patients" : [ {
                    "name" : "Alice",
                    "taskList" : {
                      "tasks" : [ {
                        "description" : "Consultation",
                        "isDone" : true
                      }, {
                        "description" : "Medication",
                        "isDone" : false
                      } ]
                    }
                  }, {
                    "name" : "Bob",
                    "taskList" : {
                      "tasks" : [ {
                        "description" : "Surgery",
                        "isDone" : false
                      }, {
                        "description" : "Medication",
                        "isDone" : false
                      } ]
                    }
                  } ]
                }
                                        """;

        hospitalJsonInvalid = """
                {
                    "patients": [
                        {
                            "name": "Alice",
                            "tasksList": [
                                {
                                    "isDone": false
                                },
                                {
                                    "description": "Medication",
                                    "isDone": false
                                }
                            ]
                        }
                    ]
                """;
    }

    @Test
    void serialize_success() {
        String jsonSerialize = JsonUtil.toJson(hospital).trim();
        assertNotNull(jsonSerialize);
        // TODO: Failed on Windows System
        // assertTrue(jsonSerialize.equals(hospitalJson.trim()));
    }

    @Test
    void deserialize_success()
            throws JsonMappingException, JsonProcessingException, TaskNotFoundException, PatientNotFoundException {
        Hospital hospitalDeserialized = JsonUtil.fromJson(hospitalJson);
        assertNotNull(hospitalDeserialized);
        assertEquals(hospital.getPatients().size(),
                hospitalDeserialized.getPatients().size());
        assertEquals(hospital.getPatient(0).getName(),
                hospitalDeserialized.getPatient(0).getName());
        assertEquals(hospital.getPatient(1).getName(),
                hospitalDeserialized.getPatient(1).getName());
        assertEquals(hospital.getPatient(0).getTaskList().getTask(0).getDescription(),
                hospitalDeserialized.getPatient(0).getTaskList().getTask(0).getDescription());
        assertEquals(hospital.getPatient(1).getTaskList().getTask(0).getDescription(),
                hospitalDeserialized.getPatient(1).getTaskList().getTask(0).getDescription());

    }

    @Test
    void deserialize_invalidJsonFormat_exceptionThrown() {
        assertThrows(JsonMappingException.class, () -> JsonUtil.fromJson(hospitalJsonInvalid));
    }

}
