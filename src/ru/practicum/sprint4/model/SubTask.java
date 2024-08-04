package ru.practicum.sprint4.model;

public class SubTask extends Task {

    private final int epicId;

    public SubTask(String name, String description, int epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public SubTask(int id, String name, String description, TaskStatus status, int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public SubTask( String name, String description, TaskStatus status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public TasksType getType() {
        return TasksType.SUBTASK;
    }

    @Override
    public String toString() {
        return ("id = " + getId() +
                ", имя подзадачи: " + getTaskName() +
                ", описание: " + getDescription() +
                ", статус: " + getTaskStatus() +
                ", epicIds:" + getEpicId() + "// ");
    }
}
