package seedu.duke.storage;

import java.io.File;
import java.io.IOException;

import seedu.duke.data.hospital.Hospital;

/**
 * Represents a storage file.
 */
public class StorageFile {
    private static final String DEFAULT_STORAGE_FILEPATH = "data/hospital_data.json";
    private static System.Logger logger = System.getLogger("StorageFile");

    /** The file path of the storage file. */
    private final String filePath;

    public StorageFile() {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public StorageFile(String filePath) {
        this.filePath = filePath;
        assert filePath != null : "File path cannot be null";
        checkFileFound(filePath);
    }

    public String getFilePath() {
        assert filePath != null : "File path cannot be null";
        return filePath;
    }

    private void checkFileFound(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            logger.log(System.Logger.Level.INFO, "File not found, creating new file: " + filePath);
            try {
                // Create the file if it does not exist
                f.getParentFile().mkdirs();
                f.createNewFile();
                assert f.exists() : "File should exist after creation";
                logger.log(System.Logger.Level.INFO, "File created successfully: " + filePath);
            } catch (IOException e) {
                // TODO: Update error handler
                System.out.println("Error creating file: " + e.getMessage());
                logger.log(System.Logger.Level.ERROR, "Error creating file: " + e.getMessage());
                System.exit(0);
            }
        }
    }

    public void save(Hospital hospital) {
        assert hospital != null : "Hospital cannot be null";
        logger.log(System.Logger.Level.INFO, "Going to save data to file: " + filePath);
        JsonUtil.saveToFile(hospital, filePath);
    }

    public Hospital load() {
        logger.log(System.Logger.Level.INFO, "Going to load data from file: " + filePath);
        return JsonUtil.loadFromFile(getFilePath());
    }

    @Override
    public String toString() {
        return "File Path: " + filePath;
    }

}
