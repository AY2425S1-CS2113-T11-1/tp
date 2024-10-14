package seedu.duke.storage;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import seedu.duke.data.hospital.Hospital;

/**
 * Represents a utility class for JSON operations.
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT) // Readable format // print
            .findAndRegisterModules(); // Automatically register additional modules (future use);

    public static void saveToFile(Hospital hospital, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), hospital);
        } catch (IOException e) {
            // TODO: Update error handler (yes I am lazy)
            System.err.println("Failed to save data to file: " + e.getMessage());
        }

    }

    public static Hospital loadFromFile(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), Hospital.class);
        } catch (JsonParseException e) {
            System.err.println("Corrupted JSON data: " + e.getMessage());
        } catch (JsonMappingException e) {
            System.err.println("Error mapping JSON to object: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
        return new Hospital();
    }

    public static String toJson(Hospital hospital) {
        try {
            return objectMapper.writeValueAsString(hospital);
        } catch (IOException e) {
            System.err.println("Failed to convert object to JSON: " + e.getMessage());
        }
        return "";
    }

    public static Hospital fromJson(String json) throws JsonMappingException, JsonProcessingException  {
        // try {
            return objectMapper.readValue(json, Hospital.class);
        // } catch (JsonParseException e) {
        //     System.err.println("Corrupted JSON data: " + e.getMessage());
        // } catch (JsonMappingException e) {
        //     System.err.println("Error mapping JSON to object: " + e.getMessage());
        // } catch (IOException e) {
        //     System.err.println("I/O error: " + e.getMessage());
        // }
        // return new Hospital();
    }

}
