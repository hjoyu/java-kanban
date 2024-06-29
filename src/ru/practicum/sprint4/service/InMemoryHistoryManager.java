package ru.practicum.sprint4.service;

import ru.practicum.sprint4.model.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final static int HISTORY_LIMIT=10;
    private final ArrayList<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (task != null) {
            if (history.size() >= HISTORY_LIMIT) {
                history.removeFirst();
            }
            history.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
            return new ArrayList<>(history);
    }
}
