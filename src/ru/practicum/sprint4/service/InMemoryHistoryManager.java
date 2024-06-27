package ru.practicum.sprint4.service;

import ru.practicum.sprint4.model.Task;
import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {

    private final static int HISTORY_LIMIT=10;
    private final ArrayList<Task> history = new ArrayList<>();
    int check=0;

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
    public ArrayList<Task> getHistory() {
            return new ArrayList<>(history);
    }
}
