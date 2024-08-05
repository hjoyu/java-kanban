package ru.practicum.sprint4.model;

import java.util.Objects;

public class Task {
    private int id;
    private String taskName;
    private String description;
    private TaskStatus status;
    private final TasksType type = TasksType.TASK;

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

    @Override
    public String toString() {
        return ("id = " + getId() +
                ", тип задачи: " + type +
                ", имя задачи: " + taskName +
                ", описание: " + description +
                ", статус: " + getTaskStatus() + "// ");
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

