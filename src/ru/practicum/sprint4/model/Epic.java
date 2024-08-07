package ru.practicum.sprint4.model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private final List<Integer> subTaskIds = new ArrayList<>();
    private final TasksType type = TasksType.EPIC;

    public Epic(String name, String description) {
        super(name, description);
    }

    public Epic(int id, String name, String description) {
        super(id, name, description);
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
                ", тип задачи: " + type +
                ", имя эпика: " + getTaskName() +
                ", описание: " + getDescription() +
                ", статус: " + getTaskStatus() +
                ", Id подзадач: " + getTaskIds() + "// ");
    }
}
