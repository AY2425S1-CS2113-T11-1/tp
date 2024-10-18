package seedu.duke.storage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static Logger logger = Logger.getLogger("JsonUtil");

    static {
        logger.setLevel(Level.SEVERE); // Only show warnings and errors
    }

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT) // Readable format // print
            .findAndRegisterModules(); // Automatically register additional modules (future use);

    public static void saveToFile(Hospital hospital, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), hospital);
        } catch (IOException e) {
            // TODO: Update error handler (yes I am lazy)
            logger.log(Level.WARNING, "Failed to save data to file: " + e.getMessage());
            System.err.println("Failed to save data to file: " + e.getMessage());
        }
        logger.log(Level.INFO, "Data saved successfully at: " + filePath);
    }

    public static Hospital loadFromFile(String filePath) {
        try {
            Hospital hospital = objectMapper.readValue(new File(filePath), Hospital.class);

            assert hospital != null : "Hospital object cannot be null";
            logger.log(Level.INFO, "Data loaded successfully from: " + filePath);

            return hospital;
        } catch (JsonParseException e) {
            logger.log(Level.WARNING, "Corrupted JSON data: " + e.getMessage());
            System.err.println("Corrupted JSON data: " + e.getMessage());
        } catch (JsonMappingException e) {
            logger.log(Level.WARNING, "Error mapping JSON to object: " + e.getMessage());
            System.err.println("Error mapping JSON to object: " + e.getMessage());
        } catch (IOException e) {
            logger.log(Level.WARNING, "I/O error: " + e.getMessage());
            System.err.println("I/O error: " + e.getMessage());
        }

        logger.log(Level.INFO, "Failed to load data from: " + filePath);
        return new Hospital();
    }

    public static String toJson(Hospital hospital) {
        logger.log(Level.INFO, "Converting object to JSON");
        try {
            String hospitalJson = objectMapper.writeValueAsString(hospital);

            logger.log(Level.INFO, "Object converted to JSON successfully");
            assert hospitalJson != null : "JSON string cannot be null";

            return hospitalJson;
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to convert object to JSON: " + e.getMessage());
            System.err.println("Failed to convert object to JSON: " + e.getMessage());
        }

        logger.log(Level.INFO, "Object conversion to JSON failed");
        return "";
    }

    public static Hospital fromJson(String json) throws JsonMappingException, JsonProcessingException {
        logger.log(Level.INFO, "Converting JSON to object");
        Hospital hospital = objectMapper.readValue(json, Hospital.class);

        if (hospital == null) {
            logger.log(Level.WARNING, "Failed to convert JSON to object");
            return new Hospital();
        }

        logger.log(Level.INFO, "JSON converted to object successfully");
        return hospital;
    }

}
