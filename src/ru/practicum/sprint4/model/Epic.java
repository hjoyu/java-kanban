package ru.practicum.sprint4.model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private final List<Integer> taskIds = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public List<Integer> getTaskIds() {
        return taskIds;
    }

    public void addTaskIds(int id) {
        taskIds.add(id);
    }

    @Override
    public String toString() {
        return ("id = " + getId() +
                ", имя эпика: " + getTaskName() +
                ", описание: " + getDescription() +
                ", статус: " + getTaskStatus() + ", Id подзадач: " + getTaskIds() + "// ");
    }
}
