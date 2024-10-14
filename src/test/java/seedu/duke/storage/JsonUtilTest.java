package seedu.duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import seedu.duke.data.hospital.Hospital;
import seedu.duke.data.task.TaskList.TaskNotFoundException;

public class JsonUtilTest {

    private Hospital hospital;
    String hospitalJson;
    String hospitalJsonInvalid;

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


                 hospitalJsonInvalid = """
                    {
                        "name": "NUH",
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
        assertTrue(jsonSerialize.equals(hospitalJson.trim()));
    }

    @Test
    void deserialize_success() throws JsonMappingException, JsonProcessingException, TaskNotFoundException {
        Hospital hospitalDeserialized = JsonUtil.fromJson(hospitalJson);
        assertNotNull(hospitalDeserialized);
        assertEquals(hospital.getPatients().size(), hospitalDeserialized.getPatients().size());
        assertEquals(hospital.getHospitalName(), hospitalDeserialized.getHospitalName());
        assertEquals(hospital.getPatient(0).getName(), hospitalDeserialized.getPatient(0).getName());
        assertEquals(hospital.getPatient(1).getName(), hospitalDeserialized.getPatient(1).getName());


            assertEquals(hospital.getPatient(0).getTasks().getTask(0).getDescription(),
                        hospitalDeserialized.getPatient(0).getTasks().getTask(0).getDescription());
            assertEquals(hospital.getPatient(1).getTasks().getTask(0).getDescription(),
                        hospitalDeserialized.getPatient(1).getTasks().getTask(0).getDescription());


    }

    @Test
    void deserialize_invalidJsonFormat_exceptionThrown() {
        // Hospital hospitalDeserialized = JsonUtil.fromJson(hospitalJsonInvalid);
        // assertNotNull(hospitalDeserialized);
        assertThrows(JsonMappingException.class, () -> JsonUtil.fromJson(hospitalJsonInvalid));
    }

}
