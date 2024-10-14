package seedu.duke.storage;

import java.io.File;
import java.io.IOException;

import seedu.duke.data.hospital.Hospital;

/**
 * Represents a storage file.
 */
public class StorageFile {
    /** The file path of the storage file. */
    private final String filePath;

    private static final String DEFAULT_STORAGE_FILEPATH = "data/hospital_data.json";

    public StorageFile() {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public StorageFile(String filePath) {
        this.filePath = filePath;
        checkFileFound(filePath);
    }

    public String getFilePath() {
        return filePath;
    }

    private void checkFileFound(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                /** Create the file if it does not exist */
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                // TODO: Update error handler
                System.out.println("Error creating file: " + e.getMessage());
                System.exit(0);
            }
        }
    }

    public void save(Hospital hospital) {
        JsonUtil.saveToFile(hospital, filePath);
    }

    public Hospital load() {
        return JsonUtil.loadFromFile(getFilePath());
    }


    @Override
    public String toString() {
        return "File Path: " + filePath;
    }

}
