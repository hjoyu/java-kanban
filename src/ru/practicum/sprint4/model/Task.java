package ru.practicum.sprint4.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private int id;
    private String taskName;
    private String description;
    private TaskStatus status;
    private final TasksType type = TasksType.TASK;
    private Duration duration;
    private LocalDateTime startTime;

    public Task(String name, String description) {
        taskName = name;
        this.description = description;
        status = TaskStatus.NEW;
    }

    public Task(int id, String name, String description) {
        taskName = name;
        this.description = description;
        status = TaskStatus.NEW;
        this.id = id;
    }

    public Task(String name, String description, TaskStatus status) {
        taskName = name;
        this.description = description;
        this.status = status;
    }

    public Task(int id, String name, String description, TaskStatus status) {
        taskName = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public Task(int id, String name, TaskStatus status, String description, LocalDateTime startTime, Duration duration) {
        this.id = id;
        this.taskName = name;
        this.status = status;
        this.description = description;
        this.startTime = startTime;
        this.duration = duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public TasksType getType() {
        return type;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTaskStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getTaskStatus() {
        return status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        if (startTime == null) {
            return null;
        }
        if (duration == null) {
            return startTime;
        }
        return startTime.plus(duration);
    }

    @Override
    public String toString() {
        return ("id = " + getId() +
                ", тип задачи: " + getType() +
                ", имя задачи: " + taskName +
                ", описание: " + description +
                ", статус: " + getTaskStatus() +
                ", время начала: " + getStartTime() +
                ", длительность: " + getDuration() +
                ", время завершения задачи: " + getEndTime() + " // ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

