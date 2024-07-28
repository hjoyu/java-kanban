package ru.practicum.sprint4.service;

import ru.practicum.sprint4.model.Task;
import java.util.List;

public interface HistoryManager {

    void add(Task task);

    void remove(int id);

    List<Task> getHistory();
}
