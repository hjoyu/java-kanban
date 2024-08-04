package ru.practicum.sprint4.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ru.practicum.sprint4.model.Task;
import ru.practicum.sprint4.model.Node;

public class InMemoryHistoryManager implements HistoryManager {
    private final HistoryList history = new HistoryList();

    @Override
    public void add(Task task) {
        int id = task.getId();
        remove(id);
        history.linkLast(task);
    }

    @Override
    public void remove(int id) {
        if (history.historyMap.containsKey(id)) {
            Node node = history.historyMap.get(id);
            history.removeNode(node);
        }
    }

    @Override
    public List<Task> getHistory() {
        return history.getTasks();
    }

    private static class HistoryList {
        private final Map<Integer, Node> historyMap = new HashMap<>();
        private Node head;
        private Node tail;

        private void linkLast(Task task) {
            Node newNode = new Node(tail, task, null);
            if (tail == null) {
                head = newNode;
            } else {
                tail.setNext(newNode);
            }
            tail = newNode;
            int id = task.getId();
            historyMap.put(id, newNode);
        }

        public List<Task> getTasks() { //получаем список задач
            List<Task> list = new LinkedList<>();
            Node listElement = head;
            while (listElement != null) {
                list.add(listElement.getTask());
                listElement = listElement.getNext();
            }
            return list;
        }

        private void removeNode(Node node) {
            Node next = node.getNext();
            Node prev = node.getPrev();
            historyMap.remove(node.getTask().getId());
            if (prev != null) {
                prev.setNext(next);
            }
            if (next != null) {
                next.setPrev(prev);
            }
            node.setPrev(null);
            node.setNext(null);
            if (node == head) {
                head = next;
            }
            if (node == tail) {
                tail = prev;
            }
        }
    }
}