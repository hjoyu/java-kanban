package ru.practicum.sprint4.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class SubTask extends Task {

    private final int epicId;
    private final TasksType type = TasksType.SUBTASK;

    public SubTask(String name, String description, int epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public SubTask(int id, String name, String description, TaskStatus status, int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public SubTask(int id, String name, TaskStatus status, String description, LocalDateTime startTime, Duration duration, int epicId) {
        super(id, name, status, description, startTime, duration);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public TasksType getType() {
        return type;
    }

    @Override
    public String toString() {
        return ("id = " + getId() +
                ", тип задачи: " + getType() +
                ", имя подзадачи: " + getTaskName() +
                ", описание: " + getDescription() +
                ", статус: " + getTaskStatus() +
                ", epicIds:" + getEpicId() +
                ", время начала: " + getStartTime() +
                ", длительность: " + getDuration() +
                ", время завершения задачи: " + getEndTime() + " // ");
    }
}
