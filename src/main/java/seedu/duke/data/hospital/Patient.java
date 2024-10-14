package seedu.duke.data.hospital;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.duke.data.task.TaskList;

/*
import java.util.ArrayList;
import java.util.List;
*/
public class Patient {
    private String name;

    // FIXME: Why? This is not used anywhere.
    @JsonIgnore
    private int index;

    private TaskList tasks;

    public Patient() {
        this("", 0);
    }

    public Patient(String name, int index, TaskList tasks) {
        this.name = name;
        this.index = index;
        this.tasks = tasks;
    }

    public Patient(String name, int index) {
        this.name = name;
        this.index = index;
        this.tasks = new TaskList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    /** Change the name of the getter method to match the JSON key */
    @JsonProperty("tasksList")
    public TaskList getTasks() {
        return tasks;
    }

    public void setTasks (TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return name + " " + tasks.toString();
    }
}
