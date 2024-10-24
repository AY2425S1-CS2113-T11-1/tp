# Developer Guide

## Acknowledgements

- This project was inspired by [AddressBook-Level3 (AB3)](https://github.com/se-edu/addressbook-level3), and several code structures were adapted for the MediTask application.
- JSON serialization and deserialization functionality uses the [Jackson library](https://github.com/FasterXML/jackson).
- Logging setup was referenced from open-source Java projects using `Logger`.

---

## Setting up, getting started
Refer to the guide for 'Setting up and getting started.'

---

## Design

{This section can be used to discuss overall system architecture or any relevant design decisions. Since you're focusing on the implementation, this section can be omitted or kept brief.}

---

## Implementation

### **AddPatientCommand**

#### Implementation

The add patient feature allows users to add a new patient to the hospital system. This is facilitated by the `AddPatientCommand`, which handles the logic of adding a patient and updating the system’s state and storage.

1. **User Input**: The user enters the `addPatient` command followed by patient details (e.g., name).
2. **Command Parsing**: The `Parser` parses the input and creates an `AddPatientCommand` object.
3. **Execution**: The `AddPatientCommand` checks if the patient already exists in the `Hospital`. If not, it adds the patient.
4. **Storage Update**: The updated hospital data, now containing the new patient, is saved to storage.

#### Sequence Diagram

The following sequence diagram illustrates how the `AddPatientCommand` is executed:

![AddPatientCommand Sequence Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/AddPatientSequenceDiagram.png)

- **If Condition**: The command checks if the patient already exists before adding the patient.
- **Logging**: If a duplicate is detected, an error is logged.


### **State Switching Feature**
#### Implementation

The state switching feature allows the system to change its mode of operation between `MAIN_STATE` (for patient management) and `TASK_STATE` (for task management). This allows the software to handle commands differently based on the current state.

1. **User Input**: The user enters a command pertaining either to patients (in `MAIN_STATE`) or tasks (in `TASK_STATE`).
2. **Command Parsing**: The `Parser` checks the current state of the software by interacting with the `State` class. Depending on the state, it interprets  the command differently.
3. **State Checking**: The `State` class tracks the current state of the application. If the system is in `MAIN_STATE`, the `Parser` creates patient-related commands (e.g., `AddPatientCommand`). If it's in `TASK_STATE`, task-related commands (e.g., `AddTaskCommand`)
4. **Command Execution**: The appropriate command is then executed. For example, in `MAIN_STATE`, it adds a new patient, while in `TASK_STATE`, it adds a new task to a patient's task list.
5. **State Transitions**: Commands like `SelectPatientCommand` and `BackCommand`, trigger state transitions.

#### Sequence Diagram

The following sequence diagram illustrates how the state-switching mechanism works:

![State-Switching Sequence Diagram](https://github.com/AY2425S1-CS2113-T11-1/tp/raw/master/docs/images/StateSwitchingSequenceDiagram.png)

- **State Checking**: When user inputs a command, the `Parser` queries the `State` object to check whether the system is in `MAIN_STATE` or `TASK_STATE`.
- **State Transitions**: When a `SelectPatientCommand` is executed, the system transitions from `MAIN_STATE` to `TASK_STATE`. Vice versa for `BackCommand`.
- **Command Execution**: Commands are executed based on the current state. For example, in `MAIN_STATE`, patient-related commands are processed, while in `TASK_STATE`, task-related commands are handled.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

## User Stories

| Version | As a ... | I want to ... | So that I can ... |
|---------|----------|---------------|-------------------|
| v1.0    | nurse    | add tasks with specific details (e.g., patient illness, precautions) | ensure all safety and medical steps are followed for each patient |
| v1.0    | nurse    | delete tasks that are no longer relevant | keep my task list up to date and avoid unnecessary clutter |
| v1.0    | nurse    | mark tasks as completed | stay organized and ensure all tasks are done during my shift |
| v1.0    | nurse    | unmark tasks that were incorrectly marked as completed | quickly correct errors and keep an accurate account of ongoing tasks |
| v1.0    | nurse    | save my tasks | access and view them after closing and reopening the interface |
| v1.0    | nurse    | list my tasks | see all my tasks that I have currently |


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
