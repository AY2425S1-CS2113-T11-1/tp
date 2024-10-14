package seedu.duke.data.task;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskList {
    private final ArrayList<Task> tasks;
    /**
     * Creates a TaskList object with an empty ArrayList of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList object with an existing ArrayList of tasks.
     *
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added.
     * @throws DuplicateTaskException If the task is already in the TaskList.
     */
    public void addTask(Task task) throws DuplicateTaskException {
        if(contains(task)) {
            throw new DuplicateTaskException();
        }
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index Index of the task to be deleted.
     * @throws TaskNotFoundException If the task is not in the TaskList.
     */
    public void deleteTask(int index) throws TaskNotFoundException {
        if(!contains(index)) {
            throw new TaskNotFoundException();
        }
        tasks.remove(index);
    }


    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    public boolean contains(int index) {
        return index >= 0 && index < getSize();
    }

    /**
     * Gets a task from the TaskList.
     * @param index Index of the task to be retrieved.
     * @return Task object.
     * @throws TaskNotFoundException If the task is not in the TaskList.
     */
    public Task getTask(int index) throws TaskNotFoundException {
        if(!contains(index)) {
            throw new TaskNotFoundException();
        }
        return tasks.get(index);
    }

    @JsonIgnore
    public int getSize() {
        return tasks.size();
    }

    public void markAsDone(int index) throws TaskNotFoundException {
        if(!contains(index)) {
            throw new TaskNotFoundException();
        }
        tasks.get(index).markAsDone();
    }

    public void markAsUndone(int index) throws TaskNotFoundException {
        if(!contains(index)) {
            throw new TaskNotFoundException();
        }
        tasks.get(index).markAsUndone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        /** Catch tasks is empty */
        if (tasks.isEmpty()) {
            return "";
        }

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public void printList() {
        System.out.println(this);
    }

    public static class TaskNotFoundException extends Exception {
        public TaskNotFoundException() {
            super("Input task is not found in the list.");
        }
    }

    public static class DuplicateTaskException extends Exception {
        public DuplicateTaskException() {
            super("Input task is already in the list.");
        }
    }
}
