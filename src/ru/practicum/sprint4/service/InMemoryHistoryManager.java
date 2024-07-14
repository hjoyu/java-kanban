package ru.practicum.sprint4.service;

import java.util.List;

import ru.practicum.sprint4.model.HistoryList;
import ru.practicum.sprint4.model.Task;
import ru.practicum.sprint4.model.Node;

public class InMemoryHistoryManager implements HistoryManager {
int count=0;
    private final HistoryList history = new HistoryList();

    @Override
    public void add(Task task) {
        int id = task.getId();
        if (history.getHistoryMap().containsKey(id)) {
            Node newNode = history.getHistoryMap().get(id);
            history.removeNode(newNode);
            }
            history.linkLast(task);
    }

    @Override
    public void remove(int id){
        if (!history.getHistoryMap().containsKey(id)) {
            return;
        }
        Node node = history.getHistoryMap().remove(id);
        history.removeNode(node);
    }

    @Override
    public List<Task> getHistory() {
            return history.getTasks();
    }
}

