package seedu.duke.data.task;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
    private String description;
    private boolean isDone;

    public Task() {
        this("");
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /** Change the name of the getter method to match the JSON key */
    @JsonProperty("isDone")
    public boolean getDone() {
        return isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }
    public void markAsUndone() {
        isDone = false;
    }
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task other = (Task) obj;
            return description.equals(other.description);
        }
        return false;
    }
}
