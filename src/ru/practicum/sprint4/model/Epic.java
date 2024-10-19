package ru.practicum.sprint4.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private final List<Integer> subTaskIds = new ArrayList<>();
    private final TasksType type = TasksType.EPIC;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;

    public Epic(String name, String description) {
        super(name, description);
    }

    public Epic(int id, String name, TaskStatus status, String description, LocalDateTime startTime,
                LocalDateTime endTime, Duration duration) {
        super(id, name, status, description, startTime, duration);
        this.endTime = endTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<Integer> getTaskIds() {
        return subTaskIds;
    }

    public void addSubTaskIds(int id) {
        subTaskIds.add(id);
    }

    public TasksType getType() {
        return type;
    }

    @Override
    public String toString() {
        return ("id = " + getId() +
                ", тип задачи: " + getType() +
                ", имя эпика: " + getTaskName() +
                ", описание: " + getDescription() +
                ", статус: " + getTaskStatus() +
                ", Id подзадач: " + getTaskIds() +
                ", время начала: " + getStartTime() +
                ", длительность: " + getDuration() +
                ", время завершения задачи: " + getEndTime() + " // "
        );
    }
}
